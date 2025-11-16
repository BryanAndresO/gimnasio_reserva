package com.gimansioreserva.gimnasioreserva_spring.validator;

import com.gimansioreserva.gimnasioreserva_spring.domain.Clase;
import com.gimansioreserva.gimnasioreserva_spring.exception.BusinessException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ClaseValidator {

    private static final int CUPO_MINIMO = 1;
    private static final int CUPO_MAXIMO = 100;
    private static final int DURACION_MINIMA = 15;
    private static final int DURACION_MAXIMA = 180;

    public void validar(Clase clase) {
        validarNombre(clase.getNombre());
        validarCupo(clase.getCupo());
        validarDuracion(clase.getDuracionMinutos());
        validarHorario(clase.getHorario());
    }

    public void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new BusinessException("El nombre de la clase es obligatorio");
        }
        if (nombre.length() > 120) {
            throw new BusinessException("El nombre de la clase no puede exceder 120 caracteres");
        }
    }

    public void validarCupo(Integer cupo) {
        if (cupo == null || cupo < CUPO_MINIMO) {
            throw new BusinessException("El cupo debe ser al menos " + CUPO_MINIMO);
        }
        if (cupo > CUPO_MAXIMO) {
            throw new BusinessException("El cupo no puede exceder " + CUPO_MAXIMO);
        }
    }

    public void validarDuracion(Integer duracion) {
        if (duracion == null || duracion < DURACION_MINIMA) {
            throw new BusinessException("La duración debe ser al menos " + DURACION_MINIMA + " minutos");
        }
        if (duracion > DURACION_MAXIMA) {
            throw new BusinessException("La duración no puede exceder " + DURACION_MAXIMA + " minutos");
        }
    }

    public void validarHorario(LocalDateTime horario) {
        if (horario == null) {
            throw new BusinessException("El horario de la clase es obligatorio");
        }
        if (horario.isBefore(LocalDateTime.now())) {
            throw new BusinessException("El horario de la clase no puede ser en el pasado");
        }
    }
}

