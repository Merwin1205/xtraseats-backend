package com.xtraseats.dto;


import java.time.LocalDate;


public class MovieTicketRequest {

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

    // Extra info (separate box on the form)
    private String screenFormat;   // 2D / 3D / IMAX / 4DX / ICE / Dolby Atmos
    private String screenNumber;
    private String extraInfo;

    // Seller info
    private String sellerName;
    private String sellerContact;  // Stored in DB, never returned publicly
    private String sellerNote;
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
}