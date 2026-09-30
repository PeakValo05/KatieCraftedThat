package com.craftedthat.organization.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.craftedthat.organization.repository.ConnectRepository;

@Controller
public class AdminController {


    private final ConnectRepository connectRepository;

    public AdminController(ConnectRepository connectRepository) {
        this.connectRepository = connectRepository;
    }
    @GetMapping("/admin/subscribers")
    public String adminDashboard(Model model) {
        model.addAttribute("subscribers", connectRepository.findAll());


        return "admin_dashboard";
    }
}
