package com.groupa.mma_moriri.service;

import com.groupa.mma_moriri.model.Stylist;
import com.groupa.mma_moriri.repo.StylistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StylistService {
    @Autowired
    private StylistRepo repo;

    public Stylist getStylistByUsername(String username) {
        return repo.findByUsername(username);
    }

    public Optional<Stylist> getStylistById(Integer stylistId) {
        return repo.findById(Long.valueOf(stylistId));
    }

    public List<Stylist> getAllStylists() {
        return repo.findAll();
    }

    public boolean addStylist(Stylist stylist) {
        Optional<Stylist> slt = Optional.ofNullable(repo.findByUsername(stylist.getUsername()));
        boolean isAdded = false;

        if(slt.isPresent()) {
            slt.get().setImage(stylist.getImage());
            repo.save(slt.get());
            isAdded = true;
        } else {
            repo.save(stylist);
        }

        return isAdded;
    }

    public Stylist setNewUsername(String username, String newUsername) {
        Stylist stylist = repo.findByUsername(username);

        stylist.setUsername(newUsername);

        return repo.save(stylist);
    }

    public String setNewPassword(String username, String currPassword, String newPassword) {
        Stylist stylist = repo.findByUsername(username);
        String msg = "Your details are insufficient or incorrect to " +
                "permit you to update/edit this account.";

        if(stylist != null) {
            stylist.setPassword(currPassword);

            List<Stylist> stylists = repo.findAll();

            if(stylists.contains(stylist)) {
                stylist.setPassword(newPassword);
                repo.save(stylist);
                msg = "Your account has been updated.";
            }
        }

        return msg;
    }

    public String removeStylist(String username, String password) {
        Stylist stylist = repo.findByUsername(username);
        String msg = "Your details are insufficient or incorrect to " +
                "permit you to delete this account.";

        if(stylist != null) {
            stylist.setPassword(password);

            List<Stylist> stylists = repo.findAll();

            if(stylists.contains(stylist)) {
                repo.delete(stylist);
                msg = "Your account has been deleted from mma_moriri system.";
            }
        }

        return msg;
    }

}
