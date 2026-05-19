package org.example.proyectospringboot.model;

import jakarta.persistence.*;

@Entity
public class Embarcacion {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String puerto;

    private String matricula;

    @OneToOne
    @JoinColumn(name="usuario_id")
    private Usuario propietario;

}