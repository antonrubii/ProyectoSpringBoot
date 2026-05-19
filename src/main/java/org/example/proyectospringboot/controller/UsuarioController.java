package org.example.proyectospringboot.controller;

import org.example.proyectospringboot.model.Usuario;
import org.example.proyectospringboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 1. Mostrar formulario de registro vacío
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    // 2. Procesar el registro e insertar el usuario en MySQL
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        // Le asignamos el rol de usuario estándar por defecto de forma obligatoria
        usuario.setRol("USER");

        // Guardamos el objeto directamente en la base de datos
        usuarioRepository.save(usuario);

        // Lo mandamos al login para que entre con su cuenta recién creada
        return "redirect:/login?registrado=true";
    }

    @GetMapping("/home-redirect")
    public String homeRedirect(Authentication auth) {
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin";
        }
        return "redirect:/perfil";
    }

    @GetMapping("/perfil")
    public String perfil(Authentication auth, Model model) {
        String email = auth.getName();
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        model.addAttribute("usuario", usuario);
        model.addAttribute("misCapturas", usuario.getCapturas());
        return "perfil";
    }

}