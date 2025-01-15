package com.groupa.mma_moriri.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManageServicesController {
    @GetMapping("ViewServices")
    public String viewServices() {
        return "view_services";
    }

    @GetMapping("GoToStylistPage")
    public String getStylistPage() {
        return "stylist_page";
    }

    @GetMapping("SelectStylist")
    public String getAllSelectedServices() {
        return "appointment_page";
    }

}
