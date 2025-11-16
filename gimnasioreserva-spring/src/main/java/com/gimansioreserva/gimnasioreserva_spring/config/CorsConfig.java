package com.gimansioreserva.gimnasioreserva_spring.config;

import org.springframework.context.annotation.Configuration;

/**
 * CORS configuration is now handled by SecurityConfig to avoid conflicts.
 * This class is kept for potential future use but the CorsFilter bean has been removed.
 */
@Configuration
public class CorsConfig {
    // CORS configuration moved to SecurityConfig.corsConfigurationSource()
    // to use setAllowedOriginPatterns which is required when allowCredentials is true
}
