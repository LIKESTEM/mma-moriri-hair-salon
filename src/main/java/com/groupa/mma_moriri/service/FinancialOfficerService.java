package com.groupa.mma_moriri.service;

import com.groupa.mma_moriri.model.Financial_Officer;
import com.groupa.mma_moriri.model.Stylist;
import com.groupa.mma_moriri.repo.FinancialOfficerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialOfficerService {
    @Autowired
    private FinancialOfficerRepo repo;

    // Financial_Officer financialOfficer

    public Financial_Officer getFinancialOfficerByUsername(String username) {
        return repo.findByUsername(username);
    }

    public Financial_Officer addFinancialOfficer(Financial_Officer financialOfficer) {
        return repo.save(financialOfficer);
    }

    public Financial_Officer setNewUsername(String username, String newUsername) {
        Financial_Officer financialOfficer = repo.findByUsername(username);

        financialOfficer.setUsername(newUsername);

        return repo.save(financialOfficer);
    }

    public String setNewPassword(String username, String currPassword, String newPassword) {
        Financial_Officer financialOfficer = repo.findByUsername(username);
        String msg = "Your details are insufficient or incorrect to " +
                "permit you to update/edit this account.";

        if(financialOfficer != null) {
            financialOfficer.setPassword(currPassword);

            List<Financial_Officer> financialOfficers = repo.findAll();

            if(financialOfficers.contains(financialOfficer)) {
                financialOfficer.setPassword(newPassword);
                repo.save(financialOfficer);
                msg = "Your account has been updated.";
            }
        }

        return msg;
    }

    public String removeFinancialOfficer(String username, String password) {
        Financial_Officer financialOfficer = repo.findByUsername(username);
        String msg = "Your details are insufficient or incorrect to " +
                "permit you to delete this account.";

        if(financialOfficer != null) {
            financialOfficer.setPassword(password);

            List<Financial_Officer> financialOfficers = repo.findAll();

            if(financialOfficers.contains(financialOfficer)) {
                repo.delete(financialOfficer);
                msg = "Your account has been deleted from mma_moriri system.";
            }
        }

        return msg;
    }

}
