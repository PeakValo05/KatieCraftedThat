package com.craftedthat.organization.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.craftedthat.organization.models.RegistrationModel;
import com.craftedthat.organization.services.RegistrationService;

@Controller 
public class RegisterController {

    private final RegistrationService registrationService;

    public RegisterController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registrationModel", new RegistrationModel());
        return "register";
    }

    @PostMapping("/register")
    public String register(RegistrationModel registrationModel) {
        registrationService.register(registrationModel);
        return "redirect:/login";
    }
    
}
