package org.example.proyectospringboot.controller;

import org.example.proyectospringboot.model.Captura;
import org.example.proyectospringboot.model.Usuario;
import org.example.proyectospringboot.repository.CapturaRepository;
import org.example.proyectospringboot.repository.UsuarioRepository;
import org.example.proyectospringboot.repository.ZonaPescaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class CapturaController {

    @Autowired
    private CapturaRepository capturaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ZonaPescaRepository zonaPescaRepository;


    // Ver todas las capturas (Modo Lectura Público)
    @GetMapping("/capturas")
    public String listar(Model model) {
        model.addAttribute("capturas", capturaRepository.findAll());
        return "capturas";
    }

    // Ir al formulario de registro desde el perfil
    @GetMapping("/capturas/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        Captura captura = new Captura();
        captura.setFecha(LocalDate.now()); // Inicializa con la fecha actual por comodidad
        model.addAttribute("captura", captura);
        model.addAttribute("todasZonas", zonaPescaRepository.findAll());
        return "form-captura";
    }

    // Guardar captura asociándola al usuario de la sesión activa
    @PostMapping("/capturas/guardar")
    public String guardar(@ModelAttribute("captura") Captura captura, Authentication auth) {
        Usuario usuarioLogueado = usuarioRepository.findByEmail(auth.getName()).orElse(null);
        captura.setUsuario(usuarioLogueado); // Se le asigna el ID del pescador automáticamente

        capturaRepository.save(captura);
        return "redirect:/perfil"; // Redirige directo a su rincón personal
    }

    // Eliminar una captura propia
    @GetMapping("/capturas/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        capturaRepository.deleteById(id);
        return "redirect:/perfil";
    }
}