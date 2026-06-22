package com.xtraseats.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "movie_tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_name", nullable = false)
    private String movieName;

    @Column(name = "theatre_name", nullable = false)
    private String theatreName;

    // ★ NEW
    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String language;

    @Column(name = "show_date", nullable = false)
    private LocalDate showDate;

    @Column(name = "show_time", nullable = false)
    private String showTime;

    @Column(name = "seat_numbers", nullable = false)
    private String seatNumbers;

    @Column(name = "ticket_count", nullable = false)
    private Integer ticketCount;

    @Column(name = "price_per_seat", nullable = false)
    private Double pricePerSeat;

    @Column(name = "screen_format", nullable = false)
    private String screenFormat;

    @Column(name = "screen_number")
    private String screenNumber;

    @Column(name = "extra_info", length = 500)
    private String extraInfo;

    @Column(name = "seller_name", nullable = false)
    private String sellerName;

    @Column(name = "seller_contact", nullable = false)
    private String sellerContact;

    @Column(name = "seller_note", length = 300)
    private String sellerNote;

    @Column(name = "posted_at")
    private LocalDateTime postedAt;

    @Column(name = "active")
    private boolean active = true;

    @PrePersist
    public void prePersist() {
        this.postedAt = LocalDateTime.now();
    }
}