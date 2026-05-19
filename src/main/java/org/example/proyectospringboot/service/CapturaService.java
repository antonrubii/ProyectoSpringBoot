package org.example.proyectospringboot.service;

import org.example.proyectospringboot.exception.TallaMinimaException;
import org.example.proyectospringboot.model.Captura;
import org.example.proyectospringboot.repository.CapturaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CapturaService {

    private final CapturaRepository capturaRepository;

    public CapturaService(CapturaRepository capturaRepository) {
        this.capturaRepository = capturaRepository;
    }

    public List<Captura> obtenerTodas() {
        return capturaRepository.findAll();
    }

    public Optional<Captura> obtenerPorId(Long id) {
        return capturaRepository.findById(id);
    }

    // Guardar con Lógica de Negocio obligatoria
    public Captura guardarCaptura(Captura captura) {
        if (captura.getTamano() != null && captura.getTamano().compareTo(new BigDecimal("20.00")) < 0) {
            throw new TallaMinimaException("¡Error de Sostenibilidad! El ejemplar no alcanza la talla mínima legal (20.00 cm). Debe ser devuelto al mar.");
        }
        return capturaRepository.save(captura);
    }

    public void eliminarCaptura(Long id) {
        capturaRepository.deleteById(id);
    }
}