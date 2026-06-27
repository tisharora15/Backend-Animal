package com.rescuepaw.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * SecurityConfig — JWT-based route protection.
 *
 * PUBLIC  (no token needed):
 *   POST /auth/register, POST /auth/login
 *   GET  /animals, GET /animals/{id}
 *   POST /rescue, POST /contact, POST /donate, POST /adoption/apply
 *   POST /volunteer/apply
 *
 * ADMIN only (token with role=ADMIN required):
 *   POST/PUT/DELETE/PATCH /animals/**
 *   GET /adoption, GET /adoption/**, PATCH /adoption/**
 *   GET /rescue
 *   GET /volunteer
 *   GET /contact
 *   GET /donate
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            // Stateless — no sessions, JWT handles auth
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth

                // ── Fully Public ────────────────────────────────────────
                .requestMatchers(HttpMethod.POST, "/auth/register", "/auth/login").permitAll()
                .requestMatchers(HttpMethod.GET,  "/animals", "/animals/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/rescue").permitAll()
                .requestMatchers(HttpMethod.POST, "/contact").permitAll()
                .requestMatchers(HttpMethod.POST, "/donate").permitAll()
                .requestMatchers(HttpMethod.POST, "/adoption/apply").permitAll()
                .requestMatchers(HttpMethod.POST, "/volunteer/apply").permitAll()

                // ── Admin Only ──────────────────────────────────────────
                // Animals — write operations
                .requestMatchers(HttpMethod.POST,   "/animals").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,    "/animals/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/animals/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH,  "/animals/**").hasRole("ADMIN")

                // Adoption applications — read + status update
                .requestMatchers(HttpMethod.GET,   "/adoption").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,   "/adoption/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/adoption/**").hasRole("ADMIN")

                // Rescue requests — read all
                .requestMatchers(HttpMethod.GET, "/rescue").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/rescue/**").hasRole("ADMIN")

                // Volunteer applications — read all
                .requestMatchers(HttpMethod.GET, "/volunteer").hasRole("ADMIN")

                // Contact messages — read all
                .requestMatchers(HttpMethod.GET, "/contact").hasRole("ADMIN")

                // Donations — read all
                .requestMatchers(HttpMethod.GET, "/donate").hasRole("ADMIN")

                // Everything else requires authentication
                .anyRequest().authenticated()
            )
            // Add JWT filter before Spring's username/password filter
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(
            "http://localhost:3000",
            "http://localhost:5173"
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
