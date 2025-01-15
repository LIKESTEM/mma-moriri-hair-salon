package com.groupa.mma_moriri.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
    @GetMapping({"/", "home"})
    public String home() {
        return "index";
    }

    @GetMapping("Login")
    public String getLoginPage() {
        return "login_page";
    }

}

