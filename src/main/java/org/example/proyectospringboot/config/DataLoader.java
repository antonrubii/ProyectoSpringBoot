package org.example.proyectospringboot.config;

import org.example.proyectospringboot.model.Usuario;
import org.example.proyectospringboot.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    public DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Comprobamos si ya existe el administrador para no duplicarlo
        if (usuarioRepository.findByEmail("admin@gmail.com").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("Administrador Jefe");
            admin.setEmail("admin@gmail.com");
            admin.setPassword("1234"); // Tu contraseña en texto plano aceptada por NoOpPasswordEncoder
            admin.setRol("ADMIN"); // Rol de administrador

            usuarioRepository.save(admin);
            System.out.println("⚓ [PescaApp] Usuario administrador creado con éxito: admin@gmail.com / 1234");
        }
    }
}