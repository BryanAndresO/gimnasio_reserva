package com.gimansioreserva.gimnasioreserva_spring.mapper;

import com.gimansioreserva.gimnasioreserva_spring.domain.Reserva;
import com.gimansioreserva.gimnasioreserva_spring.dto.core.ReservaDTO;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {

    public ReservaDTO toDTO(Reserva reserva) {
        if (reserva == null) return null;

        ReservaDTO dto = new ReservaDTO();
        dto.setIdReserva(reserva.getIdReserva());
        dto.setFechaReserva(reserva.getFechaReserva());
        dto.setEstado(reserva.getEstado());

        if (reserva.getUsuario() != null) {
            dto.setIdUsuario(reserva.getUsuario().getIdUsuario());
            dto.setNombreUsuario(reserva.getUsuario().getNombre());
            dto.setCorreoUsuario(reserva.getUsuario().getCorreo());
        }

        if (reserva.getClase() != null) {
            dto.setIdClase(reserva.getClase().getIdClase());
            dto.setNombreClase(reserva.getClase().getNombre());
            dto.setHorarioClase(reserva.getClase().getHorario());
            dto.setDuracionMinutos(reserva.getClase().getDuracionMinutos());
            
            // Incluir información del entrenador
            if (reserva.getClase().getEntrenador() != null) {
                dto.setIdEntrenador(reserva.getClase().getEntrenador().getIdEntrenador());
                dto.setNombreEntrenador(reserva.getClase().getEntrenador().getNombre());
                dto.setEspecialidadEntrenador(reserva.getClase().getEntrenador().getEspecialidad());
            }
        }

        return dto;
    }
}

