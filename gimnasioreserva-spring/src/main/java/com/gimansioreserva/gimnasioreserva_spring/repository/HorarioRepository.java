package com.gimansioreserva.gimnasioreserva_spring.repository;

import com.gimansioreserva.gimnasioreserva_spring.domain.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    // Buscar horarios activos
    List<Horario> findByActivo(Boolean activo);

    // Buscar horarios por día de la semana
    List<Horario> findByDiaSemana(DayOfWeek diaSemana);

    // Buscar horarios por día y activo
    List<Horario> findByDiaSemanaAndActivo(DayOfWeek diaSemana, Boolean activo);

    // Buscar horarios por clase
    List<Horario> findByClase_IdClase(Long idClase);

    // Buscar horarios por rango de horas
    @Query("SELECT h FROM Horario h WHERE h.horaInicio >= :inicio AND h.horaFin <= :fin AND h.activo = true")
    List<Horario> buscarPorRangoHoras(@Param("inicio") LocalTime inicio,
                                      @Param("fin") LocalTime fin);

    // Obtener horarios disponibles del día
    @Query("SELECT h FROM Horario h WHERE h.diaSemana = :dia AND h.activo = true ORDER BY h.horaInicio ASC")
    List<Horario> obtenerHorariosDelDia(@Param("dia") DayOfWeek dia);

    // Verificar conflictos de horario
    @Query("SELECT h FROM Horario h WHERE h.diaSemana = :dia AND h.activo = true AND " +
            "((h.horaInicio <= :inicio AND h.horaFin > :inicio) OR " +
            "(h.horaInicio < :fin AND h.horaFin >= :fin) OR " +
            "(h.horaInicio >= :inicio AND h.horaFin <= :fin))")
    List<Horario> verificarConflictos(@Param("dia") DayOfWeek dia,
                                      @Param("inicio") LocalTime inicio,
                                      @Param("fin") LocalTime fin);
}