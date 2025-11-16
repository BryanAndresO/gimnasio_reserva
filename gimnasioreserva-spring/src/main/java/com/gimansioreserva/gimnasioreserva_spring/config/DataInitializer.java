package com.gimansioreserva.gimnasioreserva_spring.config;

import com.gimansioreserva.gimnasioreserva_spring.domain.Usuario;
import com.gimansioreserva.gimnasioreserva_spring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Crear usuario admin por defecto si no existe
        if (!usuarioRepository.findByCorreo("admin@hotmail.com").isPresent()) {
            Usuario admin = new Usuario();
            admin.setNombre("admin");
            admin.setCorreo("admin@hotmail.com");
            admin.setContrasena(passwordEncoder.encode("admin"));
            admin.setRol("ADMIN");
            admin.setActivo(true);
            usuarioRepository.save(admin);
            System.out.println("Usuario administrador creado: admin@hotmail.com / admin");
        } else {
            // Asegurar que el admin existente tenga el rol correcto
            usuarioRepository.findByCorreo("admin@hotmail.com").ifPresent(usuario -> {
                if (!"ADMIN".equals(usuario.getRol())) {
                    usuario.setRol("ADMIN");
                    usuarioRepository.save(usuario);
                    System.out.println("Rol de administrador actualizado para: admin@hotmail.com");
                }
            });
        }
    }
}

