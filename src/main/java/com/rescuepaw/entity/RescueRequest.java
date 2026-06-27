package com.rescuepaw.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * RescueRequest entity — stores animal rescue reports submitted via the Rescue form.
 * The referenceNumber is generated and returned to the user (shown in RescueConfirmationPage).
 */
@Entity
@Table(name = "rescue_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RescueRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ── Reporter Info ─────────────────────────────────────────────────────
    @Column(nullable = false)
    private String reporterName;

    @Column(nullable = false)
    private String phone;

    private String email;

    // ── Animal Info ───────────────────────────────────────────────────────
    @Column(nullable = false)
    private String animalType;              // "Dog", "Cat", "Bird", "Other"

    @Column(nullable = false, length = 1000)
    private String location;               // Where the animal was found

    @Column(length = 2000)
    private String description;            // Description of the animal / situation

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Urgency urgency = Urgency.MEDIUM;

    // ── Tracking ──────────────────────────────────────────────────────────
    @Column(unique = true)
    private String referenceNumber;        // e.g. "RP-89621624" — sent back to user

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.PENDING;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public enum Urgency {
        LOW, MEDIUM, HIGH, CRITICAL
    }

    public enum Status {
        PENDING,
        IN_PROGRESS,
        RESOLVED,
        CLOSED
    }
}
