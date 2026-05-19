package org.example.proyectospringboot.exception;

public class TallaMinimaException extends RuntimeException {
    public TallaMinimaException(String mensaje) {
        super(mensaje);
    }
}