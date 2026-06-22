package com.xtraseats.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    @NotNull(message = "User ID is required — please log in first")
    private Long userId;

    @NotNull(message = "Amount is required")
    private Double amount;

    @NotBlank(message = "UPI ID is required")
    private String upiId;
}