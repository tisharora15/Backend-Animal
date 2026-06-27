package com.rescuepaw.dto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class DonationDTO {
    private String donorEmail;
    private String donorName;

    @NotNull @DecimalMin(value = "1.0", message = "Donation must be at least $1")
    private BigDecimal amount;

    @NotNull
    private String donationType;  // "ONE_TIME" or "MONTHLY"
}
