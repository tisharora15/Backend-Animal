package com.rescuepaw.service;

import com.rescuepaw.config.JwtUtil;
import com.rescuepaw.dto.AuthResponse;
import com.rescuepaw.dto.LoginRequest;
import com.rescuepaw.dto.RegisterRequest;
import com.rescuepaw.entity.User;
import com.rescuepaw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;              // ✅ injected for token generation

    /**
     * Register a new user and return a JWT token immediately.
     */
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already registered");
        }

        User user = User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .phone(request.getPhone())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(User.Role.USER)
            .build();

        User saved = userRepository.save(user);

        // Generate token after registration
        String token = jwtUtil.generateToken(saved.getEmail(), saved.getRole().name());

        return AuthResponse.builder()
            .id(saved.getId())
            .firstName(saved.getFirstName())
            .lastName(saved.getLastName())
            .email(saved.getEmail())
            .role(saved.getRole().name())
            .token(token)                       // ✅ token returned
            .message("Account created successfully")
            .build();
    }

    /**
     * Login and return a JWT token.
     */
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // Generate token with role embedded
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        return AuthResponse.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .role(user.getRole().name())
            .token(token)                       // ✅ token returned
            .message("Login successful")
            .build();
    }
}
