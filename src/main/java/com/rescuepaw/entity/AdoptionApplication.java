package com.rescuepaw.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * AdoptionApplication entity — stores adoption applications submitted via AnimalDetailPage.
 */
@Entity
@Table(name = "adoption_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdoptionApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ── Which animal is being applied for ─────────────────────────────────
    @Column(nullable = false)
    private Long animalId;

    private String animalName;             // Stored for easy reference

    // ── Applicant Info ────────────────────────────────────────────────────
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    // ── Home Info ─────────────────────────────────────────────────────────
    private String homeType;               // "house", "apartment", "condo", "other"

    private String hasYard;                // "yes-fenced", "yes-unfenced", "no"

    private String hasPets;                // "no", "dogs", "cats", "both", "other"

    // ── Background ────────────────────────────────────────────────────────
    @Column(length = 2000)
    private String experience;             // Pet ownership experience

    @Column(length = 2000)
    private String reason;                 // Why they want to adopt this animal

    // ── Status ────────────────────────────────────────────────────────────
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.PENDING;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }
}
