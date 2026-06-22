package com.xtraseats.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieTicketResponse {
    private Long id;
    private String movieName;
    private String theatreName;
    private String city;          // ★ NEW
    private String language;
    private LocalDate showDate;
    private String showTime;
    private String seatNumbers;
    private Integer ticketCount;
    private Double pricePerSeat;
    private String screenFormat;
    private String screenNumber;
    private String extraInfo;
    private String sellerName;
    private String sellerNote;
    private LocalDateTime postedAt;
}