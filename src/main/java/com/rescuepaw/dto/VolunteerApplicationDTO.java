package com.rescuepaw.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
public class VolunteerApplicationDTO {
    @NotBlank private String firstName;
    @NotBlank private String lastName;
    @NotBlank @Email private String email;
    @NotBlank private String phone;
    private List<String> interests;   // ["Animal Care", "Foster Care"]
    private String availability;
    private String experience;
    private String message;
}
