package com.rescuepaw.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * ContactMessage — stores messages submitted via ContactPage.
 */
@Entity
@Table(name = "contact_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String phone;

    private String subject;                 // "adoption", "rescue", "volunteer", etc.

    @Column(nullable = false, length = 3000)
    private String message;

    @Builder.Default
    private Boolean replied = false;        // Track if admin has replied

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
