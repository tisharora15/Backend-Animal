package com.rescuepaw.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * VolunteerApplication — stores volunteer sign-up forms from VolunteerPage.
 */
@Entity
@Table(name = "volunteer_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VolunteerApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    private String phone;

    // Interests stored as comma-separated string: "Animal Care,Foster Care"
    private String interests;

    private String availability;            // "weekdays", "weekends", "both", "flexible"

    @Column(length = 2000)
    private String experience;

    @Column(length = 2000)
    private String message;                 // Why they want to volunteer

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.PENDING;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public enum Status {
        PENDING, CONTACTED, ACTIVE, INACTIVE
    }
}
