package com.gimansioreserva.gimnasioreserva_spring.dto.core;

import java.time.LocalDateTime;

public class DisponibilidadDTO {
    
    private Long idClase;
    private String nombreClase;
    private LocalDateTime horario;
    private Integer cupoTotal;
    private Integer cuposOcupados;
    private Integer cuposDisponibles;
    private Boolean disponible;
    private Boolean puedeReservar;

    // Constructores
    public DisponibilidadDTO() {}

    // Getters y Setters
    public Long getIdClase() {
        return idClase;
    }

    public void setIdClase(Long idClase) {
        this.idClase = idClase;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public Integer getCupoTotal() {
        return cupoTotal;
    }

    public void setCupoTotal(Integer cupoTotal) {
        this.cupoTotal = cupoTotal;
    }

    public Integer getCuposOcupados() {
        return cuposOcupados;
    }

    public void setCuposOcupados(Integer cuposOcupados) {
        this.cuposOcupados = cuposOcupados;
    }

    public Integer getCuposDisponibles() {
        return cuposDisponibles;
    }

    public void setCuposDisponibles(Integer cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Boolean getPuedeReservar() {
        return puedeReservar;
    }

    public void setPuedeReservar(Boolean puedeReservar) {
        this.puedeReservar = puedeReservar;
    }
}

