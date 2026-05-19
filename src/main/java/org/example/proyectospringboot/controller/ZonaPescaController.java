package org.example.proyectospringboot.controller;

import org.example.proyectospringboot.repository.ZonaPescaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/zonas")
public class ZonaPescaController {

    @Autowired
    ZonaPescaRepository zonaRepo;

    @GetMapping
    public String listar(Model model){

        model.addAttribute(
                "zonas",
                zonaRepo.findAll());

        return "zonas";
    }
}