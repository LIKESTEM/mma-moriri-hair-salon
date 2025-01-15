package com.groupa.mma_moriri.web;

import com.groupa.mma_moriri.model.Admin;
import com.groupa.mma_moriri.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {

    @Autowired
    private AdminService service;

    @PostMapping("CreateAdminAccount")
    public Admin createAdminAccount(@RequestBody Admin admin) {
        return service.setAdmin(admin);
    }

    @GetMapping("GetAdmin/{username}")
    public Admin getAdminByUsername(@PathVariable("username") String username) {
        return service.getAdminByUsername(username);
    }

    @GetMapping("getAdminById/{admin_id}")
    public Optional<Admin> getAdminById(@PathVariable("admin_id") Long admin_id) {
        return service.getAdminById(admin_id);
    }

    @GetMapping("GetAllAdmins")
    public List<Admin> getAllAdmins() {
        return service.getAllAdmins();
    }

    @PutMapping("UpdateAdminAccount")
    public Admin updateAdminAccount(@RequestParam("admin_id") Long admin_id,
                                    @RequestParam("password") String password,
                                    @RequestParam("username") String username,
                                    @RequestParam("phone_number") long phone_number,
                                    @RequestParam("firstname") String firstname,
                                    @RequestParam("lastname") String lastname) {

        Admin admin = new Admin(admin_id, firstname, lastname, phone_number, username, password);
        return service.setAdmin(admin);
    }

    @PutMapping("UpdateUsername/{username}/{newUsername}")
    public Admin updateUsername(@PathVariable("username") String username,
                                @PathVariable("newUsername") String newUsername) {
        return service.setNewUsername(username, newUsername);
    }

    @DeleteMapping("RemoveAdmin/{username}")
    public String removeAdmin(@PathVariable("username") String username) {
        String msg = service.removeAdmin(username);
        return msg;
    }

}

