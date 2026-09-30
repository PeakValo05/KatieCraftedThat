package com.craftedthat.organization.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Render the storefront landing page.
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    
}
