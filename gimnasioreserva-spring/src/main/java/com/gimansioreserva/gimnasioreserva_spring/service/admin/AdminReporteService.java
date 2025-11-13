package com.gimansioreserva.gimnasioreserva_spring.service.admin;

import com.gimansioreserva.gimnasioreserva_spring.dto.admin.ReporteDTO;
import com.gimansioreserva.gimnasioreserva_spring.repository.ClaseRepository;
import com.gimansioreserva.gimnasioreserva_spring.repository.EntrenadorRepository;
import com.gimansioreserva.gimnasioreserva_spring.repository.ReservaRepository;
import com.gimansioreserva.gimnasioreserva_spring.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminReporteService {

    private final UsuarioRepository usuarioRepository;
    private final EntrenadorRepository entrenadorRepository;
    private final ClaseRepository claseRepository;
    private final ReservaRepository reservaRepository;

    public AdminReporteService(UsuarioRepository usuarioRepository,
                               EntrenadorRepository entrenadorRepository,
                               ClaseRepository claseRepository,
                               ReservaRepository reservaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.entrenadorRepository = entrenadorRepository;
        this.claseRepository = claseRepository;
        this.reservaRepository = reservaRepository;
    }

    // Generar reporte general
    @Transactional(readOnly = true)
    public ReporteDTO generarReporteGeneral() {
        ReporteDTO reporte = new ReporteDTO("GENERAL");

        // Estadísticas de usuarios
        reporte.setTotalUsuarios(usuarioRepository.count());
        reporte.setTotalUsuariosActivos((long) usuarioRepository.findByActivo(true).size());

        // Estadísticas de entrenadores
        reporte.setTotalEntrenadores(entrenadorRepository.count());

        // Estadísticas de clases
        reporte.setTotalClases(claseRepository.count());
        reporte.setTotalClasesActivas((long) claseRepository.findByActivo(true).size());

        // Estadísticas de reservas
        reporte.setTotalReservas(reservaRepository.count());
        reporte.setReservasConfirmadas(reservaRepository.contarPorEstado("CONFIRMADA"));
        reporte.setReservasCanceladas(reservaRepository.contarPorEstado("CANCELADA"));
        reporte.setReservasCompletadas(reservaRepository.contarPorEstado("COMPLETADA"));

        // Clases más populares
        reporte.setClasesPopulares(obtenerClasesPopulares());

        // Tasa de ocupación promedio
        reporte.setTasaOcupacionPromedio(calcularTasaOcupacion());

        return reporte;
    }

    // Generar reporte por periodo
    @Transactional(readOnly = true)
    public ReporteDTO generarReportePorPeriodo(LocalDateTime inicio, LocalDateTime fin) {
        ReporteDTO reporte = new ReporteDTO("PERIODO");
        reporte.setPeriodoInicio(inicio);
        reporte.setPeriodoFin(fin);

        // Estadísticas generales
        reporte.setTotalUsuarios(usuarioRepository.count());
        reporte.setTotalEntrenadores(entrenadorRepository.count());
        reporte.setTotalClases(claseRepository.count());

        // Reservas en el periodo
        List<com.gimansioreserva.gimnasioreserva_spring.domain.Reserva> reservasPeriodo =
                reservaRepository.buscarPorRangoFechas(inicio, fin);
        reporte.setReservasEnPeriodo((long) reservasPeriodo.size());

        // Clases más populares del periodo
        reporte.setClasesPopulares(obtenerClasesPopulares());

        return reporte;
    }

    // Generar reporte mensual
    @Transactional(readOnly = true)
    public ReporteDTO generarReporteMensual(int mes, int anio) {
        LocalDateTime inicio = LocalDateTime.of(anio, mes, 1, 0, 0);
        LocalDateTime fin = inicio.plusMonths(1).minusSeconds(1);

        ReporteDTO reporte = generarReportePorPeriodo(inicio, fin);
        reporte.setTipoReporte("MENSUAL");

        return reporte;
    }

    // Generar reporte semanal
    @Transactional(readOnly = true)
    public ReporteDTO generarReporteSemanal() {
        LocalDateTime fin = LocalDateTime.now();
        LocalDateTime inicio = fin.minusWeeks(1);

        ReporteDTO reporte = generarReportePorPeriodo(inicio, fin);
        reporte.setTipoReporte("SEMANAL");

        return reporte;
    }

    // Generar reporte diario
    @Transactional(readOnly = true)
    public ReporteDTO generarReporteDiario() {
        LocalDateTime hoy = LocalDateTime.now();

        ReporteDTO reporte = new ReporteDTO("DIARIO");
        reporte.setPeriodoInicio(hoy.withHour(0).withMinute(0).withSecond(0));
        reporte.setPeriodoFin(hoy.withHour(23).withMinute(59).withSecond(59));

        // Reservas del día
        List<com.gimansioreserva.gimnasioreserva_spring.domain.Reserva> reservasHoy =
                reservaRepository.obtenerReservasDelDia(hoy);
        reporte.setReservasEnPeriodo((long) reservasHoy.size());

        return reporte;
    }

    // Obtener estadísticas de entrenadores
    @Transactional(readOnly = true)
    public Map<String, Integer> obtenerEstadisticasEntrenadores() {
        Map<String, Integer> estadisticas = new HashMap<>();

        List<com.gimansioreserva.gimnasioreserva_spring.domain.Entrenador> entrenadores =
                entrenadorRepository.obtenerEntrenadoresConClasesActivas();

        for (var entrenador : entrenadores) {
            int totalClases = (int) entrenador.getClases().stream()
                    .filter(clase -> clase.getActivo())
                    .count();
            estadisticas.put(entrenador.getNombre(), totalClases);
        }

        return estadisticas;
    }

    // Método privado: Obtener clases más populares
    private Map<String, Long> obtenerClasesPopulares() {
        Map<String, Long> clasesPopulares = new HashMap<>();

        List<Object[]> estadisticas = reservaRepository.obtenerEstadisticasPorClase();
        for (Object[] stat : estadisticas) {
            String nombreClase = (String) stat[0];
            Long totalReservas = (Long) stat[1];
            clasesPopulares.put(nombreClase, totalReservas);
        }

        return clasesPopulares;
    }

    // Método privado: Calcular tasa de ocupación promedio
    private Double calcularTasaOcupacion() {
        List<com.gimansioreserva.gimnasioreserva_spring.domain.Clase> clases =
                claseRepository.findByActivo(true);

        if (clases.isEmpty()) {
            return 0.0;
        }

        double totalOcupacion = 0.0;
        for (var clase : clases) {
            int reservas = clase.getReservas().size();
            int cupo = clase.getCupo();
            totalOcupacion += (double) reservas / cupo * 100;
        }

        return totalOcupacion / clases.size();
    }
}