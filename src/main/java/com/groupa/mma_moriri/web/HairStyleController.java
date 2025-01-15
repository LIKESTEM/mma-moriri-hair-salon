package com.groupa.mma_moriri.web;

import com.groupa.mma_moriri.model.HairStyle;
import com.groupa.mma_moriri.service.HairStyleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
public class HairStyleController {
    @Autowired
    private HairStyleService service;

    @GetMapping("GetUploadStyleDetailPage")
    public String getUploadStyleDetailPage() {
        return "upload_style_detail";
    }

    @PostMapping("AddHairStyle")
    public String addHairStyle(@RequestParam("image") MultipartFile imageFile,
                               @RequestParam("name") String name,
                               @RequestParam("description") String description,
                               @RequestParam("price") double price,
                               HttpSession session) {
        String msg = "The hair style was not added.";

        if (!imageFile.isEmpty()) {
            try {
                byte[] imageBytes = imageFile.getBytes();

                HairStyle hairStyle = new HairStyle();
                hairStyle.setImage(imageBytes);
                hairStyle.setName(name);
                hairStyle.setDescription(description);
                hairStyle.setPrice(price);

                if (service.addHairStyle(hairStyle)) {
                    msg = "The hair style was added successfully.";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        session.setAttribute("msg", msg);

        return "success";
    }

    @GetMapping("GetAllHairStyles")
    public String getHairStyles(HttpSession session) {
        List<HairStyle> hairStyleList = service.getAllHairStyles();

        session.setAttribute("hairStyleList", hairStyleList);

        return "choose_hairstyle";
    }

    @GetMapping("GetAllStylesToMakeAnAppointment")
    public String getAllStylesToMakeAnAppointment(HttpSession session) {
        List<HairStyle> hairStyleList = service.getAllHairStyles();

        session.setAttribute("hairStyleList", hairStyleList);

        return "hairstyle_list";
    }



//    @GetMapping("GetHairStyleByName/{name}")
//    public HairStyle getHairStyleByName(@PathVariable("name") String name) {
//        return service.getByName(name);
//    }

//    @DeleteMapping("DeleteHairStyle/{name}")
//    public String deleteHairStyleByName(@PathVariable("name") String name) {
//        String msg = "The hairstyle was not deleted";
//
//        if(service.deleteHairStyle(name))
//        {
//            msg = "The hairstyle was deleted successfully.";
//        }
//
//        return msg;
//    }

    @GetMapping("GetUpdateImagePage")
    public String getUploadImagePage() {
        return "upload_style";
    }

    @PostMapping("UpdateHairStyleImage")
    public String updateHairStyleImage(@RequestParam("image") MultipartFile imageFile,
                                       @RequestParam("name") String name,
                                       HttpSession session) {

        String msg = "The hairstyle image was not updated.";

        if (!imageFile.isEmpty()) {
            try {
                byte[] imageBytes = imageFile.getBytes();

                HairStyle hairStyle = service.getByName(name);

                if(hairStyle != null) {
                    hairStyle.setImage(imageBytes);

                    if (service.addHairStyle(hairStyle)) {
                        msg = "The hairstyle image was successfully updated.";
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        session.setAttribute("msg", msg);

        return "success";
    }

//    @PutMapping("UpdateHairStylePrice/{name}/{price}")
//    public String updateHairStylePrice(@PathVariable("name") String name,
//                                       @PathVariable("price") double price) {
//        String msg = "The hairstyle price was not updated.";
//        HairStyle hairStyle = service.getByName(name);
//
//        if(hairStyle != null) {
//            hairStyle.setPrice(price);
//            service.addHairStyle(hairStyle);
//            msg = "The hairstyle price was successfully updated.";
//        }
//
//        return msg;
//    }

}
