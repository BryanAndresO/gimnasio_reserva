package com.gimansioreserva.gimnasioreserva_spring.dto.admin;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ReporteDTO {

    private LocalDateTime fechaGeneracion;
    private String tipoReporte; // "GENERAL", "MENSUAL", "SEMANAL", "DIARIO"

    // Estadísticas generales
    private Long totalUsuarios;
    private Long totalUsuariosActivos;
    private Long totalEntrenadores;
    private Long totalClases;
    private Long totalClasesActivas;
    private Long totalReservas;
    private Long reservasConfirmadas;
    private Long reservasCanceladas;
    private Long reservasCompletadas;

    // Estadísticas por periodo
    private LocalDateTime periodoInicio;
    private LocalDateTime periodoFin;
    private Long reservasEnPeriodo;
    private Long nuevosUsuariosEnPeriodo;

    // Top clases más populares
    private Map<String, Long> clasesPopulares = new HashMap<>();

    // Tasa de ocupación
    private Double tasaOcupacionPromedio;

    // Entrenadores más activos
    private Map<String, Integer> entrenadoresActivos = new HashMap<>();

    // Constructores
    public ReporteDTO() {
        this.fechaGeneracion = LocalDateTime.now();
    }

    public ReporteDTO(String tipoReporte) {
        this.fechaGeneracion = LocalDateTime.now();
        this.tipoReporte = tipoReporte;
    }

    // Getters y Setters
    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public Long getTotalUsuarios() {
        return totalUsuarios;
    }

    public void setTotalUsuarios(Long totalUsuarios) {
        this.totalUsuarios = totalUsuarios;
    }

    public Long getTotalUsuariosActivos() {
        return totalUsuariosActivos;
    }

    public void setTotalUsuariosActivos(Long totalUsuariosActivos) {
        this.totalUsuariosActivos = totalUsuariosActivos;
    }

    public Long getTotalEntrenadores() {
        return totalEntrenadores;
    }

    public void setTotalEntrenadores(Long totalEntrenadores) {
        this.totalEntrenadores = totalEntrenadores;
    }

    public Long getTotalClases() {
        return totalClases;
    }

    public void setTotalClases(Long totalClases) {
        this.totalClases = totalClases;
    }

    public Long getTotalClasesActivas() {
        return totalClasesActivas;
    }

    public void setTotalClasesActivas(Long totalClasesActivas) {
        this.totalClasesActivas = totalClasesActivas;
    }

    public Long getTotalReservas() {
        return totalReservas;
    }

    public void setTotalReservas(Long totalReservas) {
        this.totalReservas = totalReservas;
    }

    public Long getReservasConfirmadas() {
        return reservasConfirmadas;
    }

    public void setReservasConfirmadas(Long reservasConfirmadas) {
        this.reservasConfirmadas = reservasConfirmadas;
    }

    public Long getReservasCanceladas() {
        return reservasCanceladas;
    }

    public void setReservasCanceladas(Long reservasCanceladas) {
        this.reservasCanceladas = reservasCanceladas;
    }

    public Long getReservasCompletadas() {
        return reservasCompletadas;
    }

    public void setReservasCompletadas(Long reservasCompletadas) {
        this.reservasCompletadas = reservasCompletadas;
    }

    public LocalDateTime getPeriodoInicio() {
        return periodoInicio;
    }

    public void setPeriodoInicio(LocalDateTime periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    public LocalDateTime getPeriodoFin() {
        return periodoFin;
    }

    public void setPeriodoFin(LocalDateTime periodoFin) {
        this.periodoFin = periodoFin;
    }

    public Long getReservasEnPeriodo() {
        return reservasEnPeriodo;
    }

    public void setReservasEnPeriodo(Long reservasEnPeriodo) {
        this.reservasEnPeriodo = reservasEnPeriodo;
    }

    public Long getNuevosUsuariosEnPeriodo() {
        return nuevosUsuariosEnPeriodo;
    }

    public void setNuevosUsuariosEnPeriodo(Long nuevosUsuariosEnPeriodo) {
        this.nuevosUsuariosEnPeriodo = nuevosUsuariosEnPeriodo;
    }

    public Map<String, Long> getClasesPopulares() {
        return clasesPopulares;
    }

    public void setClasesPopulares(Map<String, Long> clasesPopulares) {
        this.clasesPopulares = clasesPopulares;
    }

    public Double getTasaOcupacionPromedio() {
        return tasaOcupacionPromedio;
    }

    public void setTasaOcupacionPromedio(Double tasaOcupacionPromedio) {
        this.tasaOcupacionPromedio = tasaOcupacionPromedio;
    }

    public Map<String, Integer> getEntrenadoresActivos() {
        return entrenadoresActivos;
    }

    public void setEntrenadoresActivos(Map<String, Integer> entrenadoresActivos) {
        this.entrenadoresActivos = entrenadoresActivos;
    }
}