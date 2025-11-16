package com.gimansioreserva.gimnasioreserva_spring.exception;

public class ClaseNoDisponibleException extends BusinessException {
    
    public ClaseNoDisponibleException(String message) {
        super(message);
    }
    
    public ClaseNoDisponibleException(Long idClase) {
        super("La clase con ID " + idClase + " no está disponible");
    }
}

