package com.gimansioreserva.gimnasioreserva_spring.repository;

import com.gimansioreserva.gimnasioreserva_spring.domain.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {

    // Buscar entrenadores por especialidad
    List<Entrenador> findByEspecialidad(String especialidad);

    // Buscar entrenadores activos
    List<Entrenador> findByActivo(Boolean activo);

    // Buscar por especialidad y activo
    List<Entrenador> findByEspecialidadAndActivo(String especialidad, Boolean activo);

    // Buscar por nombre (búsqueda parcial)
    @Query("SELECT e FROM Entrenador e WHERE LOWER(e.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Entrenador> buscarPorNombre(@Param("nombre") String nombre);

    // Obtener entrenadores con clases activas
    @Query("SELECT DISTINCT e FROM Entrenador e JOIN e.clases c WHERE c.activo = true")
    List<Entrenador> obtenerEntrenadoresConClasesActivas();

    // Contar clases por entrenador
    @Query("SELECT COUNT(c) FROM Clase c WHERE c.entrenador.idEntrenador = :idEntrenador AND c.activo = true")
    Long contarClasesPorEntrenador(@Param("idEntrenador") Long idEntrenador);

    // Obtener especialidades únicas
    @Query("SELECT DISTINCT e.especialidad FROM Entrenador e WHERE e.activo = true")
    List<String> obtenerEspecialidadesDisponibles();
}