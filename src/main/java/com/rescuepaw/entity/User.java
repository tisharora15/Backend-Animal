package com.rescuepaw.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * User entity — represents a registered user.
 * Maps to the "users" table in PostgreSQL.
 */
@Entity
@Table(name = "users")
@Data                   // Lombok: generates getters, setters, toString, equals, hashCode
@NoArgsConstructor      // Lombok: no-args constructor (required by JPA)
@AllArgsConstructor     // Lombok: all-args constructor
@Builder                // Lombok: builder pattern
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment PK
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)            // Email must be unique
    private String email;

    private String phone;

    @Column(nullable = false)
    private String password;                            // Stored as BCrypt hash

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Role role = Role.USER;                      // Default role is USER

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    /** User roles */
    public enum Role {
        USER,
        ADMIN
    }
}
