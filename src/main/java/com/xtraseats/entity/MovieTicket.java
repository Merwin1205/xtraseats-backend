package com.xtraseats.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "movie_tickets")
public class MovieTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ── Core Movie Info ───────────────────────────────────
    @Column(name = "movie_name", nullable = false)
    private String movieName;

    @Column(name = "theatre_name", nullable = false)
    private String theatreName;

    @Column(nullable = false)
    private String language;          // Tamil / Hindi / English / Telugu etc.

    @Column(name = "show_date", nullable = false)
    private LocalDate showDate;       // e.g. 2026-06-10

    @Column(name = "show_time", nullable = false)
    private String showTime;          // e.g. "7:30 PM"

    // ── Seat Info ─────────────────────────────────────────
    @Column(name = "seat_numbers", nullable = false)
    private String seatNumbers;       // Seller types: "F5, F6"

    @Column(name = "ticket_count", nullable = false)
    private Integer ticketCount;

    @Column(name = "price_per_seat", nullable = false)
    private Double pricePerSeat;

    // ── Extra Info Box (separate section on form) ─────────
    @Column(name = "screen_format", nullable = false)
    private String screenFormat;      // 2D / 3D / IMAX / 4DX / ICE / Dolby Atmos

    @Column(name = "screen_number")
    private String screenNumber;      // e.g. "Screen 3"

    @Column(name = "extra_info", length = 500)
    private String extraInfo;         // Recliner seats, food included, etc.

    // ── Seller Info ───────────────────────────────────────
    @Column(name = "seller_name", nullable = false)
    private String sellerName;

    // ⚠ NEVER returned in public API responses
    @Column(name = "seller_contact", nullable = false)
    private String sellerContact;

    @Column(name = "seller_note", length = 300)
    private String sellerNote;

    // ── Metadata ──────────────────────────────────────────
    @Column(name = "posted_at")
    private LocalDateTime postedAt;

    @Column(name = "active")
    private boolean active = true;

    @PrePersist
    public void prePersist() {
        this.postedAt = LocalDateTime.now();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public LocalDate getShowDate() {
		return showDate;
	}

	public void setShowDate(LocalDate showDate) {
		this.showDate = showDate;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getSeatNumbers() {
		return seatNumbers;
	}

	public void setSeatNumbers(String seatNumbers) {
		this.seatNumbers = seatNumbers;
	}

	public Integer getTicketCount() {
		return ticketCount;
	}

	public void setTicketCount(Integer ticketCount) {
		this.ticketCount = ticketCount;
	}

	public Double getPricePerSeat() {
		return pricePerSeat;
	}

	public void setPricePerSeat(Double pricePerSeat) {
		this.pricePerSeat = pricePerSeat;
	}

	public String getScreenFormat() {
		return screenFormat;
	}

	public void setScreenFormat(String screenFormat) {
		this.screenFormat = screenFormat;
	}

	public String getScreenNumber() {
		return screenNumber;
	}

	public void setScreenNumber(String screenNumber) {
		this.screenNumber = screenNumber;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerContact() {
		return sellerContact;
	}

	public void setSellerContact(String sellerContact) {
		this.sellerContact = sellerContact;
	}

	public String getSellerNote() {
		return sellerNote;
	}

	public void setSellerNote(String sellerNote) {
		this.sellerNote = sellerNote;
	}

	public LocalDateTime getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(LocalDateTime postedAt) {
		this.postedAt = postedAt;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}