package com.gimansioreserva.gimnasioreserva_spring.exception;

public class CupoAgotadoException extends BusinessException {
    
    public CupoAgotadoException(String message) {
        super(message);
    }
    
    public CupoAgotadoException(Long idClase) {
        super("No hay cupos disponibles para la clase con ID: " + idClase);
    }
}

