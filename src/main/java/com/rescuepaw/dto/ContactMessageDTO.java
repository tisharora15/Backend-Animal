package com.rescuepaw.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactMessageDTO {
    @NotBlank private String name;
    @NotBlank @Email private String email;
    private String phone;
    private String subject;
    @NotBlank private String message;
}
