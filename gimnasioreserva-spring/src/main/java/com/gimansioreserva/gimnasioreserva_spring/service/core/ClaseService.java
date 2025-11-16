package com.gimansioreserva.gimnasioreserva_spring.service.core;

import com.gimansioreserva.gimnasioreserva_spring.domain.Clase;
import com.gimansioreserva.gimnasioreserva_spring.dto.core.ClaseDTO;
import com.gimansioreserva.gimnasioreserva_spring.exception.ClaseNoDisponibleException;
import com.gimansioreserva.gimnasioreserva_spring.mapper.ClaseMapper;
import com.gimansioreserva.gimnasioreserva_spring.repository.ClaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClaseService {

    private final ClaseRepository claseRepository;
    private final ClaseMapper claseMapper;

    public ClaseService(ClaseRepository claseRepository, ClaseMapper claseMapper) {
        this.claseRepository = claseRepository;
        this.claseMapper = claseMapper;
    }

    @Transactional(readOnly = true)
    public List<ClaseDTO> obtenerClasesDisponibles() {
        return claseRepository.obtenerClasesDisponibles().stream()
                .map(claseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ClaseDTO> obtenerClasesProximas() {
        return claseRepository.obtenerClasesProximas(LocalDateTime.now()).stream()
                .map(claseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ClaseDTO> obtenerClasesActivas() {
        return claseRepository.findByActivo(true).stream()
                .map(claseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ClaseDTO> obtenerPorId(Long id) {
        return claseRepository.findById(id)
                .map(claseMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public ClaseDTO obtenerPorIdConValidacion(Long id) {
        Clase clase = claseRepository.findById(id)
                .orElseThrow(() -> new ClaseNoDisponibleException(id));
        
        if (!clase.getActivo()) {
            throw new ClaseNoDisponibleException(id);
        }
        
        return claseMapper.toDTO(clase);
    }

    @Transactional(readOnly = true)
    public List<ClaseDTO> buscarPorNombre(String nombre) {
        return claseRepository.buscarPorNombre(nombre).stream()
                .map(claseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ClaseDTO> obtenerPorEntrenador(Long idEntrenador) {
        return claseRepository.findByEntrenador_IdEntrenadorAndActivo(idEntrenador, true).stream()
                .map(claseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ClaseDTO> obtenerPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
        return claseRepository.buscarPorRangoFechas(inicio, fin).stream()
                .map(claseMapper::toDTO)
                .collect(Collectors.toList());
    }
}

