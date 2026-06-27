package com.rescuepaw.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Animal entity — represents an animal available for adoption.
 * Fields match exactly what AdoptPage.jsx and AnimalDetailPage.jsx expect.
 */
@Entity
@Table(name = "animals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ── Basic Info (used in AdoptPage card grid) ──────────────────────────
    @Column(nullable = false)
    private String name;                    // e.g. "Max"

    @Column(nullable = false)
    private String type;                    // "Dog" or "Cat"

    @Column(nullable = false)
    private String breed;                   // e.g. "Golden Retriever"

    private String age;                     // e.g. "2 years"

    private String gender;                  // "Male" or "Female"

    private String location;                // e.g. "Los Angeles, CA"

    @Column(length = 1000)
    private String image;                   // Unsplash URL

    @Column(length = 500)
    private String description;             // Short description for card

    // ── Detail Info (used in AnimalDetailPage) ────────────────────────────
    @Column(length = 2000)
    private String fullDescription;         // Long bio paragraph

    private String personality;             // e.g. "Friendly, Energetic, Loyal"

    private String weight;                  // e.g. "65 lbs"

    @Builder.Default
    private Boolean vaccinated = false;

    @Builder.Default
    private Boolean spayedNeutered = false;

    private String goodWithKids;            // "Yes", "No", or nuanced string

    private String goodWithPets;            // "Yes", "No", or nuanced string

    private String trained;                 // e.g. "House trained, knows basic commands"

    private String medicalNeeds;            // e.g. "None" or description

    private String adoptionFee;             // e.g. "$350"

    // ── Status ────────────────────────────────────────────────────────────
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.AVAILABLE;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public enum Status {
        AVAILABLE,
        PENDING,
        ADOPTED
    }
}
