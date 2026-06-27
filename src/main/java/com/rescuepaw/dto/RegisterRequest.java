package com.rescuepaw.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank @Email(message = "Valid email is required")
    private String email;

    private String phone;

    @NotBlank @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
