package com.gimansioreserva.gimnasioreserva_spring.dto.core;

import java.util.Map;

public class EstadisticaDTO {
    
    private Long totalClases;
    private Long totalReservas;
    private Long reservasConfirmadas;
    private Long reservasCanceladas;
    private Long reservasCompletadas;
    private Long usuariosActivos;
    private Map<String, Long> reservasPorClase;
    private Map<String, Long> reservasPorMes;

    // Constructores
    public EstadisticaDTO() {}

    // Getters y Setters
    public Long getTotalClases() {
        return totalClases;
    }

    public void setTotalClases(Long totalClases) {
        this.totalClases = totalClases;
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

    public Long getUsuariosActivos() {
        return usuariosActivos;
    }

    public void setUsuariosActivos(Long usuariosActivos) {
        this.usuariosActivos = usuariosActivos;
    }

    public Map<String, Long> getReservasPorClase() {
        return reservasPorClase;
    }

    public void setReservasPorClase(Map<String, Long> reservasPorClase) {
        this.reservasPorClase = reservasPorClase;
    }

    public Map<String, Long> getReservasPorMes() {
        return reservasPorMes;
    }

    public void setReservasPorMes(Map<String, Long> reservasPorMes) {
        this.reservasPorMes = reservasPorMes;
    }
}

