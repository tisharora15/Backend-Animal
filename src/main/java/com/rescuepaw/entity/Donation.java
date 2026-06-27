package com.rescuepaw.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Donation — stores donation records from DonatePage.
 */
@Entity
@Table(name = "donations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String donorEmail;             // Optional — donor may not be logged in

    private String donorName;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DonationType donationType;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.COMPLETED; // In real app: PENDING until payment confirmed

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public enum DonationType {
        ONE_TIME,
        MONTHLY
    }

    public enum Status {
        PENDING,
        COMPLETED,
        FAILED,
        REFUNDED
    }
}
