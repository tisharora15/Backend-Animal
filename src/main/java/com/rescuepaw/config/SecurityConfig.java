package com.rescuepaw.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Value("${app.cors.allowed-origins:http://localhost:5173}")
    private String allowedOrigins;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth

                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                .requestMatchers(HttpMethod.POST, "/auth/register", "/auth/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/animals", "/animals/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/rescue").permitAll()
                .requestMatchers(HttpMethod.POST, "/contact").permitAll()
                .requestMatchers(HttpMethod.POST, "/donate").permitAll()
                .requestMatchers(HttpMethod.POST, "/adoption/apply").permitAll()
                .requestMatchers(HttpMethod.POST, "/volunteer/apply").permitAll()

                .requestMatchers(HttpMethod.POST, "/animals").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/animals/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/animals/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/animals/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/adoption").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/adoption/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/adoption/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/rescue").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/rescue/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/volunteer").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/contact").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/donate").hasRole("ADMIN")

                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        List<String> origins = Arrays.stream(allowedOrigins.split(","))
                .map(String::trim)
                .toList();

        config.setAllowedOrigins(origins);
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("Authorization"));
        config.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
