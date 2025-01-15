package com.groupa.mma_moriri.web;

import com.groupa.mma_moriri.model.Appointment;
import com.groupa.mma_moriri.model.Customer;
import com.groupa.mma_moriri.model.Rating;
import com.groupa.mma_moriri.model.Stylist;
import com.groupa.mma_moriri.repo.RatingRepo;
import com.groupa.mma_moriri.service.AppointmentService;
import com.groupa.mma_moriri.service.CustomerService;
import com.groupa.mma_moriri.service.RatingService;
import com.groupa.mma_moriri.service.StylistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class RatingController {
    @Autowired
    private RatingService service;
    @Autowired
    private CustomerService custService;
    @Autowired
    private StylistService stylistService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    RatingRepo repo;
    @GetMapping("startRatting")
    public String getRattingPage(@RequestParam("refNo") String refNo,
                                 HttpSession session) {
        Optional<Appointment> appointment = appointmentService.getByRefNo(refNo);
        if(appointment.isPresent()){
        Long customerId = appointment.get().getCustId();
        Long stylistId = appointment.get().getStylistId();

        session.setAttribute("customerId", customerId);
        session.setAttribute("stylistId", stylistId);
        return "rate";
        } else {
            session.setAttribute("msg", "Something wrong with the Appointment!");
            return "success";}


    }

    @PostMapping("AddRating")
    public String addRating(@RequestParam("customerId") Long customerId,
                            @RequestParam("stylistId") Long stylistId,
                            @RequestParam("rate") Integer rate,
                            HttpSession session) {
        if (customerId != null) {
            Rating rating = new Rating(customerId, stylistId, rate);
            if (service.addRating(rating)) {
                repo.save(rating);
                session.setAttribute("msg", "The rating was added successfully.");
            } else {
                session.setAttribute("msg", "The rating was not added because of missing customer details.");
            }
        } else {
            session.setAttribute("msg", "The rating was not added.");
        }
        return "success";
    }


    @GetMapping("GetRatingPage")
    public String getRatePage(){
        return"rate";
    }
    @GetMapping("GetAllRatings")
    public List<Rating> getAllRatings() {
        return service.getAllRatings();
    }

    @GetMapping("GetRatingByStylist/{username}")
    public List<Rating> getAllRatingByStylist(@PathVariable("username") String username) {
        Stylist stylist = stylistService.getStylistByUsername(username);
        return service.getRatingsByStylistId(stylist.getStylist_id());
    }

    @GetMapping("GetRatingByCustomer/{username}")
    public List<Rating> getAllRatingByCustomer(@PathVariable("username") String username) {
        Customer customer = custService.getCustomerByUsername(username);
        return service.getRatingsByCustomerId(customer.getCust_id());
    }
}
