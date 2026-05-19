package org.example.proyectospringboot.controller;

import org.example.proyectospringboot.repository.CapturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CapturaRepository capturaRepository;

    @GetMapping
    public String panel(Model model){

        model.addAttribute(
                "capturas",
                capturaRepository.findAll());

        return "admin";
    }

}