package com.gimansioreserva.gimnasioreserva_spring.exception;

import java.time.LocalDateTime;

public class TiempoMinimoException extends BusinessException {
    
    public TiempoMinimoException(String message) {
        super(message);
    }
    
    public TiempoMinimoException(LocalDateTime horarioClase, long horasMinimas) {
        super("Debes realizar la reserva al menos " + horasMinimas + 
              " horas antes del inicio de la clase. La clase inicia: " + horarioClase);
    }
}

