package com.groupa.mma_moriri.service;

import com.groupa.mma_moriri.model.Admin;
import com.groupa.mma_moriri.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepo repo;

    public Admin getAdminByUsername(String username) {
        return repo.findByUsername(username);
    }

//    public List<Admin> searchAdminsByNameOrSurnameKeyword(String keyword) {
//        return repo
//                .findByNameContainingOrSurnameContaining(keyword, keyword);
//    }

    public Optional<Admin> getAdminById(Long id) {
        return repo.findById(id);
    }

    public List<Admin> getAllAdmins() {
        return repo.findAll();
    }

    public Admin setAdmin(Admin admin) {
        return repo.save(admin);
    }

    public Admin setNewUsername(String username, String newUsername) {
        Admin admin = repo.findByUsername(username);

        admin.setUsername(newUsername);

        return repo.save(admin);
    }

    public String removeAdmin(String username) {
        Admin admin = repo.findByUsername(username);
        repo.deleteById(Long.valueOf(admin.getAdmin_id()));
        return "The admin has been removed";
    }

    public Admin addAmin(Admin admin) {
        return repo.save(admin);
    }
}
