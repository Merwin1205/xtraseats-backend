package com.xtraseats.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MovieTicketResponse {

    private Long id;

    // Core movie info
    private String movieName;
    private String theatreName;
    private String language;
    private LocalDate showDate;
    private String showTime;

    // Seat info
    private String seatNumbers;
    private Integer ticketCount;
    private Double pricePerSeat;

    // Extra info
    private String screenFormat;
    private String screenNumber;
    private String extraInfo;

    // Seller info — contact intentionally absent
    private String sellerName;
    private String sellerNote;

    // Metadata
    private LocalDateTime postedAt;

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

    // ⚠ sellerContact is NOT a field in this class
}