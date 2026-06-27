package com.rescuepaw.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RescueRequestDTO {
    @NotBlank(message = "Your name is required")
    private String reporterName;

    @NotBlank(message = "Phone number is required")
    private String phone;

    private String email;

    @NotBlank(message = "Animal type is required")
    private String animalType;

    @NotBlank(message = "Location is required")
    private String location;

    private String description;

    private String urgency;   // "LOW", "MEDIUM", "HIGH", "CRITICAL"
}
