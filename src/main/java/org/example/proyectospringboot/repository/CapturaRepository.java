package org.example.proyectospringboot.repository;

import org.example.proyectospringboot.model.Captura;
import org.example.proyectospringboot.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CapturaRepository extends JpaRepository<Captura,Long> {
    List<Captura> findByUsuarioId(Long usuarioId);
    List<Captura> findByUsuarioEmail(String email);
}
