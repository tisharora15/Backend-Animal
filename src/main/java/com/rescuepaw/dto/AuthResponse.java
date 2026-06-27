package com.rescuepaw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AuthResponse — returned after login or register.
 * Now includes a JWT token that the frontend stores and
 * sends with every admin API request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;       // "USER" or "ADMIN"
    private String token;      // ✅ JWT token — NEW
    private String message;
}
