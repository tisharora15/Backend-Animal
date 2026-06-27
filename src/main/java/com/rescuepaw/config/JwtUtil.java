package com.rescuepaw.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * JwtUtil — generates and validates JWT tokens.
 * Tokens contain the user's email and role as claims.
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;   // milliseconds — 86400000 = 24 hours

    private Key getSigningKey() {
        // Secret must be at least 32 characters for HS256
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    /** Generate a JWT token for the given email and role */
    public String generateToken(String email, String role) {
        return Jwts.builder()
            .setSubject(email)
            .claim("role", role)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    /** Extract email (subject) from token */
    public String extractEmail(String token) {
        return parseClaims(token).getSubject();
    }

    /** Extract role claim from token */
    public String extractRole(String token) {
        return (String) parseClaims(token).get("role");
    }

    /** Returns true if token is valid and not expired */
    public boolean isValid(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
