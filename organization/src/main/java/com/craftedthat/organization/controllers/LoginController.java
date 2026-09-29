package com.craftedthat.organization.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // Mapping for the home page
    @GetMapping ("/login")
    public String login() {
        return "login";
    }
    
}
