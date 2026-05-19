package org.example.proyectospringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Evitamos errores 403 al enviar formularios
                .authorizeHttpRequests(auth -> auth
                        // El listado de capturas y la raíz son públicos para ver
                        .requestMatchers("/", "/capturas", "/zonas", "/login").permitAll()
                        // El panel de administración solo para ADMIN
                        .requestMatchers("/admin", "/admin/**").hasRole("ADMIN")
                        // El perfil y las acciones de crear/borrar requieren estar logueado
                        .requestMatchers("/perfil", "/capturas/nuevo", "/capturas/guardar", "/capturas/eliminar/**").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        // Redirección inteligente tras el login exitoso
                        .defaultSuccessUrl("/home-redirect", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Al usar NoOpPasswordEncoder, Spring aceptará el '1234' tal cual lo tienes en tu MySQL
        return NoOpPasswordEncoder.getInstance();
    }
}