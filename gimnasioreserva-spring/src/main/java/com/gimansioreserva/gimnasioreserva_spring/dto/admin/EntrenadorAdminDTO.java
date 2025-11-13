package com.gimansioreserva.gimnasioreserva_spring.dto.admin;

public class EntrenadorAdminDTO {

    private Long idEntrenador;
    private String nombre;
    private String especialidad;
    private String certificaciones;
    private Boolean activo;
    private Integer totalClases;
    private Integer clasesActivas;

    // Constructores
    public EntrenadorAdminDTO() {}

    public EntrenadorAdminDTO(Long idEntrenador, String nombre, String especialidad,
                              String certificaciones, Boolean activo) {
        this.idEntrenador = idEntrenador;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.certificaciones = certificaciones;
        this.activo = activo;
    }

    // Getters y Setters
    public Long getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(Long idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCertificaciones() {
        return certificaciones;
    }

    public void setCertificaciones(String certificaciones) {
        this.certificaciones = certificaciones;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getTotalClases() {
        return totalClases;
    }

    public void setTotalClases(Integer totalClases) {
        this.totalClases = totalClases;
    }

    public Integer getClasesActivas() {
        return clasesActivas;
    }

    public void setClasesActivas(Integer clasesActivas) {
        this.clasesActivas = clasesActivas;
    }
}