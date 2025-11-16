package com.gimansioreserva.gimnasioreserva_spring.validator;

import com.gimansioreserva.gimnasioreserva_spring.domain.Clase;
import com.gimansioreserva.gimnasioreserva_spring.domain.Reserva;
import com.gimansioreserva.gimnasioreserva_spring.exception.BusinessException;
import com.gimansioreserva.gimnasioreserva_spring.exception.CupoAgotadoException;
import com.gimansioreserva.gimnasioreserva_spring.exception.TiempoMinimoException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReservaValidator {

    private static final long HORAS_MINIMAS_ANTICIPACION = 2; // 2 horas antes

    public void validarCrearReserva(Reserva reserva, Clase clase) {
        validarClaseDisponible(clase);
        validarCuposDisponibles(clase);
        validarTiempoMinimo(clase.getHorario());
        validarHorarioFuturo(clase.getHorario());
    }

    public void validarCancelarReserva(Reserva reserva, Clase clase) {
        if (reserva.getEstado().equals("CANCELADA")) {
            throw new BusinessException("La reserva ya está cancelada");
        }
        if (reserva.getEstado().equals("COMPLETADA")) {
            throw new BusinessException("No se puede cancelar una reserva completada");
        }
        validarTiempoMinimoCancelacion(clase.getHorario());
    }

    private void validarClaseDisponible(Clase clase) {
        if (!clase.getActivo()) {
            throw new BusinessException("La clase no está disponible");
        }
    }

    private void validarCuposDisponibles(Clase clase) {
        int cuposDisponibles = clase.getCuposDisponibles();
        if (cuposDisponibles <= 0) {
            throw new CupoAgotadoException(clase.getIdClase());
        }
    }

    private void validarTiempoMinimo(LocalDateTime horarioClase) {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime fechaMinima = horarioClase.minusHours(HORAS_MINIMAS_ANTICIPACION);
        
        if (ahora.isAfter(fechaMinima)) {
            throw new TiempoMinimoException(horarioClase, HORAS_MINIMAS_ANTICIPACION);
        }
    }

    private void validarTiempoMinimoCancelacion(LocalDateTime horarioClase) {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime fechaLimiteCancelacion = horarioClase.minusHours(1); // 1 hora antes
        
        if (ahora.isAfter(fechaLimiteCancelacion)) {
            throw new BusinessException("No se puede cancelar la reserva. La clase inicia en menos de 1 hora");
        }
    }

    private void validarHorarioFuturo(LocalDateTime horario) {
        if (horario.isBefore(LocalDateTime.now())) {
            throw new BusinessException("No se puede reservar una clase que ya ha pasado");
        }
    }
}

