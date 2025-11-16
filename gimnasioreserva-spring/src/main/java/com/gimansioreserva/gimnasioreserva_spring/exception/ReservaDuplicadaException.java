package com.gimansioreserva.gimnasioreserva_spring.exception;

public class ReservaDuplicadaException extends BusinessException {
    
    public ReservaDuplicadaException(String message) {
        super(message);
    }
    
    public ReservaDuplicadaException(Long idUsuario, Long idClase) {
        super("Ya existe una reserva confirmada para el usuario " + idUsuario + 
              " en la clase " + idClase);
    }
}

