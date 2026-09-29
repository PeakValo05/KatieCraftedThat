package com.craftedthat.organization.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.craftedthat.organization.models.ConnectModel;
import com.craftedthat.organization.repository.ConnectRepository;

@Controller
public class SubscribeController {

    private final ConnectRepository connectRepository;

    public SubscribeController(ConnectRepository connectRepository) {
        this.connectRepository = connectRepository;
    }

 

    @PostMapping("/subscribe")
    public String subscribe(@RequestParam(value = "email", required = false) String email) {
        if (email == null || email.isBlank()) {
            return "redirect:/home?connectError";
        }

        ConnectModel connectModel = new ConnectModel();
        connectModel.setEmail(email.trim());
        connectRepository.save(connectModel);

        return "redirect:/home?connected";
    }
}
