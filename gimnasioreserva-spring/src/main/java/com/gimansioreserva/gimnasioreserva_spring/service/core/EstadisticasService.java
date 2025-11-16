package com.gimansioreserva.gimnasioreserva_spring.service.core;

import com.gimansioreserva.gimnasioreserva_spring.dto.core.EstadisticaDTO;
import com.gimansioreserva.gimnasioreserva_spring.repository.ClaseRepository;
import com.gimansioreserva.gimnasioreserva_spring.repository.ReservaRepository;
import com.gimansioreserva.gimnasioreserva_spring.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EstadisticasService {

    private final ClaseRepository claseRepository;
    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;

    public EstadisticasService(ClaseRepository claseRepository,
                              ReservaRepository reservaRepository,
                              UsuarioRepository usuarioRepository) {
        this.claseRepository = claseRepository;
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public EstadisticaDTO obtenerEstadisticasGenerales() {
        EstadisticaDTO estadisticas = new EstadisticaDTO();
        
        estadisticas.setTotalClases(claseRepository.count());
        estadisticas.setTotalReservas(reservaRepository.count());
        estadisticas.setReservasConfirmadas(reservaRepository.contarPorEstado("CONFIRMADA"));
        estadisticas.setReservasCanceladas(reservaRepository.contarPorEstado("CANCELADA"));
        estadisticas.setReservasCompletadas(reservaRepository.contarPorEstado("COMPLETADA"));
        estadisticas.setUsuariosActivos((long) usuarioRepository.findByActivo(true).size());

        // Estadísticas por clase
        Map<String, Long> reservasPorClase = new HashMap<>();
        List<Object[]> estadisticasPorClase = reservaRepository.obtenerEstadisticasPorClase();
        for (Object[] stat : estadisticasPorClase) {
            reservasPorClase.put((String) stat[0], (Long) stat[1]);
        }
        estadisticas.setReservasPorClase(reservasPorClase);

        // Estadísticas por mes
        Map<String, Long> reservasPorMes = new HashMap<>();
        LocalDateTime inicioMes = LocalDateTime.now().minusMonths(6);
        List<com.gimansioreserva.gimnasioreserva_spring.domain.Reserva> reservas = 
                reservaRepository.buscarPorRangoFechas(inicioMes, LocalDateTime.now());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        reservas.forEach(r -> {
            String mes = r.getFechaReserva().format(formatter);
            reservasPorMes.put(mes, reservasPorMes.getOrDefault(mes, 0L) + 1);
        });
        estadisticas.setReservasPorMes(reservasPorMes);

        return estadisticas;
    }

    @Transactional(readOnly = true)
    public EstadisticaDTO obtenerEstadisticasPorPeriodo(LocalDateTime inicio, LocalDateTime fin) {
        EstadisticaDTO estadisticas = new EstadisticaDTO();
        
        List<com.gimansioreserva.gimnasioreserva_spring.domain.Reserva> reservas = 
                reservaRepository.buscarPorRangoFechas(inicio, fin);
        
        estadisticas.setTotalReservas((long) reservas.size());
        estadisticas.setReservasConfirmadas(
                reservas.stream().filter(r -> r.getEstado().equals("CONFIRMADA")).count());
        estadisticas.setReservasCanceladas(
                reservas.stream().filter(r -> r.getEstado().equals("CANCELADA")).count());
        estadisticas.setReservasCompletadas(
                reservas.stream().filter(r -> r.getEstado().equals("COMPLETADA")).count());

        return estadisticas;
    }
}

