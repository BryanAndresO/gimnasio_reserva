package com.gimansioreserva.gimnasioreserva_spring.repository;

import com.gimansioreserva.gimnasioreserva_spring.domain.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void testFindByCorreo_Success() {
        // Given
        Usuario usuario = new Usuario();
        usuario.setNombre("Test User");
        usuario.setCorreo("test@example.com");
        usuario.setContrasena("hashedPassword");
        usuario.setRol("USER");
        usuario.setActivo(true);
        entityManager.persistAndFlush(usuario);

        // When
        Optional<Usuario> found = usuarioRepository.findByCorreo("test@example.com");

        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getNombre()).isEqualTo("Test User");
        assertThat(found.get().getCorreo()).isEqualTo("test@example.com");
    }

    @Test
    void testFindByCorreo_NotFound() {
        // When
        Optional<Usuario> found = usuarioRepository.findByCorreo("nonexistent@example.com");

        // Then
        assertThat(found).isEmpty();
    }

    @Test
    void testExistsByCorreo_True() {
        // Given
        Usuario usuario = new Usuario();
        usuario.setNombre("Test User");
        usuario.setCorreo("exists@example.com");
        usuario.setContrasena("hashedPassword");
        usuario.setRol("USER");
        usuario.setActivo(true);
        entityManager.persistAndFlush(usuario);

        // When
        boolean exists = usuarioRepository.existsByCorreo("exists@example.com");

        // Then
        assertThat(exists).isTrue();
    }

    @Test
    void testExistsByCorreo_False() {
        // When
        boolean exists = usuarioRepository.existsByCorreo("notexists@example.com");

        // Then
        assertThat(exists).isFalse();
    }

    @Test
    void testFindByActivo() {
        // Given
        Usuario activeUser = new Usuario();
        activeUser.setNombre("Active User");
        activeUser.setCorreo("active@example.com");
        activeUser.setContrasena("hashedPassword");
        activeUser.setRol("USER");
        activeUser.setActivo(true);
        entityManager.persistAndFlush(activeUser);

        Usuario inactiveUser = new Usuario();
        inactiveUser.setNombre("Inactive User");
        inactiveUser.setCorreo("inactive@example.com");
        inactiveUser.setContrasena("hashedPassword");
        inactiveUser.setRol("USER");
        inactiveUser.setActivo(false);
        entityManager.persistAndFlush(inactiveUser);

        // When
        List<Usuario> activeUsers = usuarioRepository.findByActivo(true);

        // Then
        assertThat(activeUsers).hasSize(1);
        assertThat(activeUsers.get(0).getNombre()).isEqualTo("Active User");
    }

    @Test
    void testFindByRol() {
        // Given
        Usuario adminUser = new Usuario();
        adminUser.setNombre("Admin User");
        adminUser.setCorreo("admin@example.com");
        adminUser.setContrasena("hashedPassword");
        adminUser.setRol("ADMIN");
        adminUser.setActivo(true);
        entityManager.persistAndFlush(adminUser);

        Usuario regularUser = new Usuario();
        regularUser.setNombre("Regular User");
        regularUser.setCorreo("user@example.com");
        regularUser.setContrasena("hashedPassword");
        regularUser.setRol("USER");
        regularUser.setActivo(true);
        entityManager.persistAndFlush(regularUser);

        // When
        List<Usuario> admins = usuarioRepository.findByRol("ADMIN");

        // Then
        assertThat(admins).hasSize(1);
        assertThat(admins.get(0).getNombre()).isEqualTo("Admin User");
    }
}
