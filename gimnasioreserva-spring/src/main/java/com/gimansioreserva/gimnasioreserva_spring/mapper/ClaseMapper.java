package com.gimansioreserva.gimnasioreserva_spring.mapper;

import com.gimansioreserva.gimnasioreserva_spring.domain.Clase;
import com.gimansioreserva.gimnasioreserva_spring.dto.core.ClaseDTO;
import org.springframework.stereotype.Component;

@Component
public class ClaseMapper {

    public ClaseDTO toDTO(Clase clase) {
        if (clase == null) return null;

        ClaseDTO dto = new ClaseDTO();
        dto.setIdClase(clase.getIdClase());
        dto.setNombre(clase.getNombre());
        dto.setDescripcion(clase.getDescripcion());
        dto.setHorario(clase.getHorario());
        dto.setCupo(clase.getCupo());
        dto.setCuposDisponibles(clase.getCuposDisponibles());
        dto.setDuracionMinutos(clase.getDuracionMinutos());
        dto.setActivo(clase.getActivo());

        if (clase.getEntrenador() != null) {
            dto.setIdEntrenador(clase.getEntrenador().getIdEntrenador());
            dto.setNombreEntrenador(clase.getEntrenador().getNombre());
            dto.setEspecialidadEntrenador(clase.getEntrenador().getEspecialidad());
        }

        return dto;
    }
}

