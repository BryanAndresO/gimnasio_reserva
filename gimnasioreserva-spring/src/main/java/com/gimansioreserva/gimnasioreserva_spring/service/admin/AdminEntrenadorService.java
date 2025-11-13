package com.gimansioreserva.gimnasioreserva_spring.service.admin;

import com.gimansioreserva.gimnasioreserva_spring.domain.Entrenador;
import com.gimansioreserva.gimnasioreserva_spring.dto.admin.EntrenadorAdminDTO;
import com.gimansioreserva.gimnasioreserva_spring.repository.EntrenadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminEntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    public AdminEntrenadorService(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    // Listar todos los entrenadores
    @Transactional(readOnly = true)
    public List<EntrenadorAdminDTO> listarTodos() {
        return entrenadorRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Listar entrenadores activos
    @Transactional(readOnly = true)
    public List<EntrenadorAdminDTO> listarActivos() {
        return entrenadorRepository.findByActivo(true).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Buscar entrenador por ID
    @Transactional(readOnly = true)
    public Optional<EntrenadorAdminDTO> buscarPorId(Long id) {
        return entrenadorRepository.findById(id)
                .map(this::convertirADTO);
    }

    // Buscar entrenadores por especialidad
    @Transactional(readOnly = true)
    public List<EntrenadorAdminDTO> buscarPorEspecialidad(String especialidad) {
        return entrenadorRepository.findByEspecialidad(especialidad).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Buscar entrenadores por nombre
    @Transactional(readOnly = true)
    public List<EntrenadorAdminDTO> buscarPorNombre(String nombre) {
        return entrenadorRepository.buscarPorNombre(nombre).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Crear nuevo entrenador
    @Transactional
    public EntrenadorAdminDTO crear(EntrenadorAdminDTO dto) {
        Entrenador entrenador = new Entrenador();
        entrenador.setNombre(dto.getNombre());
        entrenador.setEspecialidad(dto.getEspecialidad());
        entrenador.setCertificaciones(dto.getCertificaciones());
        entrenador.setActivo(dto.getActivo() != null ? dto.getActivo() : true);

        Entrenador guardado = entrenadorRepository.save(entrenador);
        return convertirADTO(guardado);
    }

    // Actualizar entrenador
    @Transactional
    public Optional<EntrenadorAdminDTO> actualizar(Long id, EntrenadorAdminDTO dto) {
        return entrenadorRepository.findById(id)
                .map(entrenador -> {
                    entrenador.setNombre(dto.getNombre());
                    entrenador.setEspecialidad(dto.getEspecialidad());
                    entrenador.setCertificaciones(dto.getCertificaciones());
                    entrenador.setActivo(dto.getActivo());

                    Entrenador actualizado = entrenadorRepository.save(entrenador);
                    return convertirADTO(actualizado);
                });
    }

    // Eliminar entrenador (soft delete)
    @Transactional
    public boolean desactivar(Long id) {
        return entrenadorRepository.findById(id)
                .map(entrenador -> {
                    entrenador.setActivo(false);
                    entrenadorRepository.save(entrenador);
                    return true;
                })
                .orElse(false);
    }

    // Activar entrenador
    @Transactional
    public boolean activar(Long id) {
        return entrenadorRepository.findById(id)
                .map(entrenador -> {
                    entrenador.setActivo(true);
                    entrenadorRepository.save(entrenador);
                    return true;
                })
                .orElse(false);
    }

    // Eliminar entrenador permanentemente
    @Transactional
    public boolean eliminar(Long id) {
        if (entrenadorRepository.existsById(id)) {
            entrenadorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Obtener especialidades disponibles
    @Transactional(readOnly = true)
    public List<String> obtenerEspecialidades() {
        return entrenadorRepository.obtenerEspecialidadesDisponibles();
    }

    // Contar clases por entrenador
    @Transactional(readOnly = true)
    public Long contarClases(Long idEntrenador) {
        return entrenadorRepository.contarClasesPorEntrenador(idEntrenador);
    }

    // Método privado para convertir Entidad a DTO
    private EntrenadorAdminDTO convertirADTO(Entrenador entrenador) {
        EntrenadorAdminDTO dto = new EntrenadorAdminDTO();
        dto.setIdEntrenador(entrenador.getIdEntrenador());
        dto.setNombre(entrenador.getNombre());
        dto.setEspecialidad(entrenador.getEspecialidad());
        dto.setCertificaciones(entrenador.getCertificaciones());
        dto.setActivo(entrenador.getActivo());
        dto.setTotalClases(entrenador.getClases().size());
        dto.setClasesActivas((int) entrenador.getClases().stream()
                .filter(clase -> clase.getActivo())
                .count());
        return dto;
    }
}