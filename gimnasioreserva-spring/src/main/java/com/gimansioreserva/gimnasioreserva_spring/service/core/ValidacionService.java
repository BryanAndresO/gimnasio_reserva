package com.gimansioreserva.gimnasioreserva_spring.service.core;

import com.gimansioreserva.gimnasioreserva_spring.domain.Clase;
import com.gimansioreserva.gimnasioreserva_spring.domain.Reserva;
import com.gimansioreserva.gimnasioreserva_spring.exception.CupoAgotadoException;
import com.gimansioreserva.gimnasioreserva_spring.exception.ReservaDuplicadaException;
import com.gimansioreserva.gimnasioreserva_spring.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ValidacionService {

    private final ReservaRepository reservaRepository;

    public ValidacionService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public boolean validarCupoDisponible(Clase clase) {
        int cuposDisponibles = clase.getCuposDisponibles();
        return cuposDisponibles > 0;
    }

    public void validarCupoDisponibleConExcepcion(Clase clase) {
        if (!validarCupoDisponible(clase)) {
            throw new CupoAgotadoException(clase.getIdClase());
        }
    }

    public boolean validarReservaDuplicada(Long idUsuario, Long idClase) {
        return reservaRepository.buscarReservaDuplicada(idUsuario, idClase).isPresent();
    }

    public void validarReservaDuplicadaConExcepcion(Long idUsuario, Long idClase) {
        if (validarReservaDuplicada(idUsuario, idClase)) {
            throw new ReservaDuplicadaException(idUsuario, idClase);
        }
    }

    public boolean validarHorarioFuturo(Clase clase) {
        return clase.getHorario().isAfter(java.time.LocalDateTime.now());
    }

    public boolean validarTiempoMinimo(LocalDateTime horario) {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime fechaMinima = horario.minusHours(2); // 2 horas antes
        return ahora.isBefore(fechaMinima);
    }

    public boolean validarClaseActiva(Clase clase) {
        return clase.getActivo();
    }

    public boolean validarReservaConfirmada(Reserva reserva) {
        return reserva != null && reserva.getEstado().equals("CONFIRMADA");
    }
}

