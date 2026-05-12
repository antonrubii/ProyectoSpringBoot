package org.example.proyectospringboot.repository;

import org.example.proyectospringboot.model.ZonaPesca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ZonaPescaRepository extends JpaRepository<ZonaPesca,Integer> {
    List<ZonaPesca> findByComunidad(String comunidad);
}
