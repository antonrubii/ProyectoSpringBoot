package org.example.proyectospringboot.service;

import org.example.proyectospringboot.model.Captura;
import org.example.proyectospringboot.repository.CapturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapturaService {

    private final CapturaRepository capturaRepository;

    public CapturaService(CapturaRepository capturaRepository) {
        this.capturaRepository = capturaRepository;
    }

    public List<Captura> obtenerTodas() {
        return capturaRepository.findAll();
    }
}