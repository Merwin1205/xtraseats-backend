package com.xtraseats.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieTicketRequest {

    @NotBlank(message = "Movie name is required")
    private String movieName;

    @NotBlank(message = "Theatre name is required")
    private String theatreName;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Language is required")
    private String language;

    @NotNull(message = "Show date is required")
    @FutureOrPresent(message = "Show date cannot be in the past")
    private LocalDate showDate;

    @NotBlank(message = "Show time is required")
    private String showTime;

    @NotBlank(message = "Seat numbers are required")
    private String seatNumbers;

    @NotNull(message = "Ticket count is required")
    @Min(value = 1, message = "At least 1 ticket is required")
    @Max(value = 10, message = "Maximum 10 tickets per listing")
    private Integer ticketCount;

    @NotNull(message = "Price per seat is required")
    @Positive(message = "Price must be greater than 0")
    private Double pricePerSeat;

    @NotBlank(message = "Screen format is required")
    private String screenFormat;

    private String screenNumber; // optional — no validation needed

    @Size(max = 500, message = "Extra info cannot exceed 500 characters")
    private String extraInfo;

    @NotBlank(message = "Seller name is required")
    private String sellerName;

    @NotBlank(message = "Seller contact is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Enter a valid 10-digit Indian mobile number")
    private String sellerContact;

    @Size(max = 300, message = "Seller note cannot exceed 300 characters")
    private String sellerNote;
}