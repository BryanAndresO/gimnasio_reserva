package com.gimansioreserva.gimnasioreserva_spring.repository;

import com.gimansioreserva.gimnasioreserva_spring.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por correo
    Optional<Usuario> findByCorreo(String correo);

    // Verificar si existe un correo
    boolean existsByCorreo(String correo);

    // Buscar usuarios por rol
    List<Usuario> findByRol(String rol);

    // Buscar usuarios activos
    List<Usuario> findByActivo(Boolean activo);

    // Buscar usuarios por rol y activo
    List<Usuario> findByRolAndActivo(String rol, Boolean activo);

    // Buscar usuarios por nombre (búsqueda parcial)
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Usuario> buscarPorNombre(@Param("nombre") String nombre);

    // Contar usuarios por rol
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.rol = :rol")
    Long contarPorRol(@Param("rol") String rol);

    // Obtener usuarios con reservas activas
    @Query("SELECT DISTINCT u FROM Usuario u JOIN u.reservas r WHERE r.estado = 'CONFIRMADA'")
    List<Usuario> obtenerUsuariosConReservasActivas();
}