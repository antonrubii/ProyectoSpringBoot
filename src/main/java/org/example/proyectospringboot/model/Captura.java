package org.example.proyectospringboot.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "captura")
public class Captura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "especie", nullable = false, length = 100)
    private String especie;

    @Column(name = "peso", precision = 5, scale = 2)
    private BigDecimal peso;

    @Column(name = "tamano", precision = 5, scale = 2)
    private BigDecimal tamano;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "ubicacion", length = 150)
    private String ubicacion;

    @ManyToMany
    @JoinTable
            (
                    name = "captura_zona",
                    joinColumns = @JoinColumn(name = "captura_id"),
                    inverseJoinColumns = @JoinColumn(name = "zona_id")
            )
    private List<ZonaPesca> zonas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getTamano() {
        return tamano;
    }

    public void setTamano(BigDecimal tamano) {
        this.tamano = tamano;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}