package org.example.proyectospringboot.controller;

import org.example.proyectospringboot.model.Captura;
import org.example.proyectospringboot.service.CapturaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CapturaController {

    private final CapturaService capturaService;

    public CapturaController(CapturaService capturaService) {
        this.capturaService = capturaService;
    }

    @GetMapping("/capturas")
    public List<Captura> obtenerCapturas() {
        return capturaService.obtenerTodas();
    }
}