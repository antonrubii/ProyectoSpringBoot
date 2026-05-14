package org.example.proyectospringboot.controller;

import org.example.proyectospringboot.model.Captura;
import org.example.proyectospringboot.repository.CapturaRepository;
import org.example.proyectospringboot.service.CapturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class CapturaController {

    @Autowired
    private CapturaRepository capturaRepository;

    @GetMapping("/capturas")
    public String listar(Model model){

        model.addAttribute(
                "capturas",
                capturaRepository.findAll()
        );

        return "capturas";
    }
}