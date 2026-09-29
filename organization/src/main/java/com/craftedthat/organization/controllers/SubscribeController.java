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
    public String subscribe(@RequestParam("email") String email) {
        // Handle the subscription logic here (e.g., save the email to the database)
        ConnectModel connectModel = new ConnectModel();

        connectModel.setEmail(email);
        
        connectRepository.save(connectModel);

        System.out.println("Subscribed email: " + email);

        return "redirect:/home"; // Redirect back to the home page after subscribing
    }
}
