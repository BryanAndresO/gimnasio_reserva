package com.gimansioreserva.gimnasioreserva_spring.unit.service;

import com.gimansioreserva.gimnasioreserva_spring.domain.Usuario;
import com.gimansioreserva.gimnasioreserva_spring.dto.auth.LoginRequest;
import com.gimansioreserva.gimnasioreserva_spring.dto.auth.LoginResponse;
import com.gimansioreserva.gimnasioreserva_spring.dto.auth.RegistroRequest;
import com.gimansioreserva.gimnasioreserva_spring.repository.UsuarioRepository;
import com.gimansioreserva.gimnasioreserva_spring.security.jwt.JwtTokenBlacklist;
import com.gimansioreserva.gimnasioreserva_spring.security.jwt.JwtTokenProvider;
import com.gimansioreserva.gimnasioreserva_spring.service.auth.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private JwtTokenBlacklist jwtTokenBlacklist;

    @InjectMocks
    private AuthenticationService authenticationService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNombre("Test User");
        usuario.setCorreo("test@test.com");
        usuario.setContrasena("encodedPassword");
        usuario.setRol("USER");
        usuario.setActivo(true);
    }

    @Test
    void testLogin_Success() {
        LoginRequest request = new LoginRequest();
        request.setCorreo("test@test.com");
        request.setContrasena("password123");

        when(usuarioRepository.findByCorreo("test@test.com")).thenReturn(Optional.of(usuario));
        when(jwtTokenProvider.generarToken("test@test.com", "USER")).thenReturn("accessToken");
        when(jwtTokenProvider.generarRefreshToken("test@test.com")).thenReturn("refreshToken");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);

        LoginResponse response = authenticationService.login(request);

        assertThat(response).isNotNull();
        assertThat(response.getAccessToken()).isEqualTo("accessToken");
        assertThat(response.getRefreshToken()).isEqualTo("refreshToken");
        assertThat(response.getCorreo()).isEqualTo("test@test.com");
        assertThat(response.getNombre()).isEqualTo("Test User");
        assertThat(response.getRol()).isEqualTo("USER");
    }

    @Test
    void testRegistrar_Success() {
        RegistroRequest request = new RegistroRequest();
        request.setNombre("New User");
        request.setCorreo("new@test.com");
        request.setContrasena("password123");
        request.setRol("USER");

        when(usuarioRepository.findByCorreo("new@test.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario result = authenticationService.registrar(request);

        assertThat(result).isNotNull();
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testRegistrar_EmailAlreadyExists() {
        RegistroRequest request = new RegistroRequest();
        request.setCorreo("existing@test.com");

        when(usuarioRepository.findByCorreo("existing@test.com")).thenReturn(Optional.of(usuario));

        assertThatThrownBy(() -> authenticationService.registrar(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("ya está registrado");
    }
}

