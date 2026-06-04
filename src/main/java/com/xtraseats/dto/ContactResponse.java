package com.xtraseats.dto;

public class ContactResponse {

    private String sellerName;
    private String sellerContact;
    private String message;

    // Constructor
    public ContactResponse(String sellerName, String sellerContact, String message) {
        this.sellerName = sellerName;
        this.sellerContact = sellerContact;
        this.message = message;
    }

    // Getters
    public String getSellerName() { return sellerName; }
    public String getSellerContact() { return sellerContact; }
    public String getMessage() { return message; }

    // Setters
    public void setSellerName(String sellerName) { this.sellerName = sellerName; }
    public void setSellerContact(String sellerContact) { this.sellerContact = sellerContact; }
    public void setMessage(String message) { this.message = message; }
}