package com.gimansioreserva.gimnasioreserva_spring.repository;

import com.gimansioreserva.gimnasioreserva_spring.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // Buscar reservas por usuario
    List<Reserva> findByUsuario_IdUsuario(Long idUsuario);

    // Buscar reservas por clase
    List<Reserva> findByClase_IdClase(Long idClase);

    // Buscar reservas por estado
    List<Reserva> findByEstado(String estado);

    // Buscar reservas por usuario y estado
    List<Reserva> findByUsuario_IdUsuarioAndEstado(Long idUsuario, String estado);

    // Verificar si existe reserva duplicada
    @Query("SELECT r FROM Reserva r WHERE r.usuario.idUsuario = :idUsuario AND r.clase.idClase = :idClase AND r.estado = 'CONFIRMADA'")
    Optional<Reserva> buscarReservaDuplicada(@Param("idUsuario") Long idUsuario,
                                             @Param("idClase") Long idClase);

    // Obtener reservas por rango de fechas
    @Query("SELECT r FROM Reserva r WHERE r.fechaReserva BETWEEN :fechaInicio AND :fechaFin")
    List<Reserva> buscarPorRangoFechas(@Param("fechaInicio") LocalDateTime fechaInicio,
                                       @Param("fechaFin") LocalDateTime fechaFin);

    // Contar reservas por estado
    @Query("SELECT COUNT(r) FROM Reserva r WHERE r.estado = :estado")
    Long contarPorEstado(@Param("estado") String estado);

    // Obtener reservas confirmadas de un usuario
    @Query("SELECT r FROM Reserva r WHERE r.usuario.idUsuario = :idUsuario AND r.estado = 'CONFIRMADA' ORDER BY r.fechaReserva DESC")
    List<Reserva> obtenerReservasConfirmadasPorUsuario(@Param("idUsuario") Long idUsuario);

    // Estadísticas: Total de reservas por clase
    @Query("SELECT r.clase.nombre, COUNT(r) FROM Reserva r GROUP BY r.clase.nombre")
    List<Object[]> obtenerEstadisticasPorClase();

    // Reservas del día
    @Query("SELECT r FROM Reserva r WHERE DATE(r.fechaReserva) = DATE(:fecha) AND r.estado = 'CONFIRMADA'")
    List<Reserva> obtenerReservasDelDia(@Param("fecha") LocalDateTime fecha);
}