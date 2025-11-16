package com.gimansioreserva.gimnasioreserva_spring.exception;

public class ReservaNoEncontradaException extends BusinessException {
    
    public ReservaNoEncontradaException(String message) {
        super(message);
    }
    
    public ReservaNoEncontradaException(Long idReserva) {
        super("No se encontró la reserva con ID: " + idReserva);
    }
}

