package com.gimansioreserva.gimnasioreserva_spring.validator;

import com.gimansioreserva.gimnasioreserva_spring.domain.Horario;
import com.gimansioreserva.gimnasioreserva_spring.exception.BusinessException;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class HorarioValidator {

    public void validar(Horario horario) {
        validarHoraInicio(horario.getHoraInicio());
        validarHoraFin(horario.getHoraFin());
        validarRangoHoras(horario.getHoraInicio(), horario.getHoraFin());
    }

    private void validarHoraInicio(LocalTime horaInicio) {
        if (horaInicio == null) {
            throw new BusinessException("La hora de inicio es obligatoria");
        }
    }

    private void validarHoraFin(LocalTime horaFin) {
        if (horaFin == null) {
            throw new BusinessException("La hora de fin es obligatoria");
        }
    }

    private void validarRangoHoras(LocalTime horaInicio, LocalTime horaFin) {
        if (horaFin.isBefore(horaInicio) || horaFin.equals(horaInicio)) {
            throw new BusinessException("La hora de fin debe ser posterior a la hora de inicio");
        }
        
        // Validar que el rango sea razonable (mínimo 30 minutos, máximo 4 horas)
        long minutos = java.time.temporal.ChronoUnit.MINUTES.between(horaInicio, horaFin);
        if (minutos < 30) {
            throw new BusinessException("El horario debe tener al menos 30 minutos de duración");
        }
        if (minutos > 240) {
            throw new BusinessException("El horario no puede exceder 4 horas de duración");
        }
    }
}

