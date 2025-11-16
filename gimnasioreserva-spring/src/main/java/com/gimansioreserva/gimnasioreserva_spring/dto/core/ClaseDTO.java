package com.gimansioreserva.gimnasioreserva_spring.dto.core;

import java.time.LocalDateTime;

public class ClaseDTO {
    
    private Long idClase;
    private String nombre;
    private String descripcion;
    private LocalDateTime horario;
    private Integer cupo;
    private Integer cuposDisponibles;
    private Integer duracionMinutos;
    private Boolean activo;
    private Long idEntrenador;
    private String nombreEntrenador;
    private String especialidadEntrenador;

    // Constructores
    public ClaseDTO() {}

    // Getters y Setters
    public Long getIdClase() {
        return idClase;
    }

    public void setIdClase(Long idClase) {
        this.idClase = idClase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public Integer getCupo() {
        return cupo;
    }

    public void setCupo(Integer cupo) {
        this.cupo = cupo;
    }

    public Integer getCuposDisponibles() {
        return cuposDisponibles;
    }

    public void setCuposDisponibles(Integer cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
    }

    public Integer getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(Integer duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Long getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(Long idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public String getNombreEntrenador() {
        return nombreEntrenador;
    }

    public void setNombreEntrenador(String nombreEntrenador) {
        this.nombreEntrenador = nombreEntrenador;
    }

    public String getEspecialidadEntrenador() {
        return especialidadEntrenador;
    }

    public void setEspecialidadEntrenador(String especialidadEntrenador) {
        this.especialidadEntrenador = especialidadEntrenador;
    }
}

