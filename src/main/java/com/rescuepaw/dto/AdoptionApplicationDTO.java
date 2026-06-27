package com.rescuepaw.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdoptionApplicationDTO {
    @NotNull(message = "Animal ID is required")
    private Long animalId;

    private String animalName;

    @NotBlank private String name;
    @NotBlank @Email private String email;
    @NotBlank private String phone;
    @NotBlank private String address;
    private String homeType;
    private String hasYard;
    private String hasPets;
    private String experience;
    private String reason;
}
