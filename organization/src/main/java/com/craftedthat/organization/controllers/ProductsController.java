package com.craftedthat.organization.controllers;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.craftedthat.organization.models.KitsModel;


@Controller
public class ProductsController {
    
    @GetMapping("/kits")
    public String getKits(Model model) {
        List<KitsModel> kits = List.of(
            new KitsModel("Star Spangled", "Riley Blake July Tabletop Banner", "kit1.png"),
            new KitsModel("Great Joy", "Riley Blake December Tabletop Banner", "kit2.png"),
            new KitsModel("In Session", "Riley Blake September Tabletop Banner", "kit3.png"),
            new KitsModel("Twinkle & Dim", "Riley Blake August Tabletop Banner", "kit4.png")

        );
        List<KitsModel> moreKits = List.of(
            new KitsModel("Riley Blake Pattern", "Roll Up Pillowcase Kit", "kit5.png"),
            new KitsModel("Riley Blake Pattern", "Strippy, Strappy Bag Kit", "kit6.png")
        );
        
        model.addAttribute("kits", kits);
        model.addAttribute("moreKits", moreKits);
        return "kits";
    }
    
}
