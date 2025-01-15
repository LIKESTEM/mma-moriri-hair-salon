package com.groupa.mma_moriri.web;

import com.groupa.mma_moriri.model.Rating;
import com.groupa.mma_moriri.model.Stylist;
import com.groupa.mma_moriri.service.RatingService;
import com.groupa.mma_moriri.service.StylistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class StylistController {
    @Autowired
    private StylistService service;
    @Autowired
    private RatingService rateService;

    // Stylist stylist

    @PostMapping("CreateStylistAccount")
    public String createStylistAccount(@RequestParam("username") String username,
                                       @RequestParam("password") String password,
                                       @RequestParam("phone_number") long phone_number,
                                       @RequestParam("firstname") String firstname,
                                       @RequestParam("lastname") String lastname,
//                                         @RequestParam("image") byte[] image
                                       HttpSession session
    ) {

        Stylist stylist = new Stylist(firstname, lastname, phone_number,
                username, password, null);
        String msg = "The stylist was not created.";

        if(service.addStylist(stylist)) {
            msg = "The stylist was created successfully.";
        }

        session.setAttribute("msg", msg);

        return "success";
    }

    @GetMapping("GetStylistByUsername/{username}")
    public Stylist getStylistByUsername(@PathVariable("username") String username) {
        return service.getStylistByUsername(username);
    }

    @PutMapping("UpdateStylist/{username}/{password}/{newPassword}")
    public String changePassword(@PathVariable("username") String username,
                                 @PathVariable("password") String password,
                                 @PathVariable("newPassword") String newPassword) {
        return service.setNewPassword(username, password, newPassword);
    }

    @DeleteMapping("RemoveStylist/{username}/{password}")
    public String removeStylist(@PathVariable("username") String username,
                                 @PathVariable("password") String password) {
        return service.removeStylist(username, password);
    }

    @PostMapping("GetStylistsPage")
    public String getStylistPage(@RequestParam("hairstyleName") String hairstyleName,
                                 HttpSession session) {
        session.setAttribute("hairstyleName", hairstyleName);
        Map<Stylist, Integer> mapStylistRate = new HashMap<>();
        List<Stylist> stylistList = service.getAllStylists();

        for(Stylist obj: stylistList) {
            Integer stylistId = Math.toIntExact(obj.getStylist_id());
            Optional<List<Rating>> testRates = Optional.ofNullable(rateService
                    .getRatingsByStylistId(Long.valueOf(stylistId)));
            Integer rateCount = 0, max = 1, totRate = 0;

            if(testRates.isPresent()) {
                List<Rating> rates = testRates.get();
                for(Rating rate: rates) {
                    rateCount += rate.getRate();
                    max += 5;
                }
            }

            if((max - 1) == 0) {
                totRate = 1;
            } else {
                totRate = (rateCount / (max - 1)) * 5;
            }

            mapStylistRate.put(obj, totRate);

        }

        session.setAttribute("mapStylistRate", mapStylistRate);

        return "choose_hairdresser";
    }

    @GetMapping("GetAllStylists")
    public List<Stylist> getAllStylist() {
        return service.getAllStylists();
    }

    @GetMapping("GetUpdateStylistImagePage")
    public String getUpdateStylistImagePage() {
        return "upload_stylist_image";
    }

    @PostMapping("UpdateStylistImage")
    public String updateStylistImage(@RequestParam("username") String username,
                                       @RequestParam("image") MultipartFile imageFile,
                                     HttpSession session) {
        String msg = "The stylist image was not updated.";

        if (!imageFile.isEmpty()) {
            try {
                byte[] imageBytes = imageFile.getBytes();

                Stylist stylist = service.getStylistByUsername(username);

                if(stylist != null) {
                    stylist.setImage(imageBytes);

                    if (service.addStylist(stylist)) {
                        msg = "The stylist image was successfully updated.";
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        session.setAttribute("msg", msg);

        return "success";
    }

}

