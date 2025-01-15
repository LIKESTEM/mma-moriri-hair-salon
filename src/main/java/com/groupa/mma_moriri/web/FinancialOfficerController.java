package com.groupa.mma_moriri.web;

import com.groupa.mma_moriri.model.Financial_Officer;
import com.groupa.mma_moriri.service.FinancialOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FinancialOfficerController {
    @Autowired
    private FinancialOfficerService service;

    // Financial_Officer FinancialOfficer financialOfficer

    @PostMapping("CreateFinancialOfficerAccount/{username}/{password}/{phone_number}/{firstname}/{lastname}")
    public Financial_Officer createFinancialOfficerAccount(@PathVariable("username") String username,
                                         @PathVariable("password") String password,
                                         @PathVariable("phone_number") long phone_number,
                                         @PathVariable("firstname") String firstname,
                                         @PathVariable("lastname") String lastname) {

        Financial_Officer financialOfficer = new Financial_Officer(firstname,
                lastname, phone_number, username, password);

        return service.addFinancialOfficer(financialOfficer);
    }

    @GetMapping("GetFinancialOfficerByUsername/{username}")
    public Financial_Officer getFinancialOfficerByUsername(@PathVariable("username") String username) {
        return service.getFinancialOfficerByUsername(username);
    }

    @PutMapping("UpdateFinancialOfficer/{username}/{password}/{newPassword}")
    public String changePassword(@PathVariable("username") String username,
                                 @PathVariable("password") String password,
                                 @PathVariable("newPassword") String newPassword) {
        return service.setNewPassword(username, password, newPassword);
    }

    @DeleteMapping("RemoveFinancialOfficer/{username}/{password}")
    public String removeFinancialOfficer(@PathVariable("username") String username,
                                @PathVariable("password") String password) {
        return service.removeFinancialOfficer(username, password);
    }

}
