package com.gimansioreserva.gimnasioreserva_spring.repository;

import com.gimansioreserva.gimnasioreserva_spring.domain.Clase;
import com.gimansioreserva.gimnasioreserva_spring.domain.Entrenador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ClaseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    private Entrenador entrenador;

    @BeforeEach
    void setUp() {
        entrenador = new Entrenador();
        entrenador.setNombre("Entrenador Test");
        entrenador.setEspecialidad("Fuerza");
        entrenador.setActivo(true);
        entrenador = entityManager.persistAndFlush(entrenador);
    }

    @Test
    void testFindByActivo() {
        Clase claseActiva = new Clase();
        claseActiva.setNombre("Clase Activa");
        claseActiva.setHorario(LocalDateTime.now().plusDays(1));
        claseActiva.setCupo(20);
        claseActiva.setActivo(true);
        claseActiva.setEntrenador(entrenador);
        entityManager.persistAndFlush(claseActiva);

        Clase claseInactiva = new Clase();
        claseInactiva.setNombre("Clase Inactiva");
        claseInactiva.setHorario(LocalDateTime.now().plusDays(2));
        claseInactiva.setCupo(15);
        claseInactiva.setActivo(false);
        claseInactiva.setEntrenador(entrenador);
        entityManager.persistAndFlush(claseInactiva);

        List<Clase> clasesActivas = claseRepository.findByActivo(true);

        assertThat(clasesActivas).hasSize(1);
        assertThat(clasesActivas.get(0).getNombre()).isEqualTo("Clase Activa");
    }

    @Test
    void testFindByEntrenador_IdEntrenador() {
        Clase clase = new Clase();
        clase.setNombre("Clase Test");
        clase.setHorario(LocalDateTime.now().plusDays(1));
        clase.setCupo(20);
        clase.setActivo(true);
        clase.setEntrenador(entrenador);
        entityManager.persistAndFlush(clase);

        List<Clase> clases = claseRepository.findByEntrenador_IdEntrenador(entrenador.getIdEntrenador());

        assertThat(clases).hasSize(1);
        assertThat(clases.get(0).getNombre()).isEqualTo("Clase Test");
    }
}

