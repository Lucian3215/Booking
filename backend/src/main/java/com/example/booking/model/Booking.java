package com.example.booking.model;

import java.io.Serializable;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, updatable = false)
    protected Long id;

    private String userID;
    private String sourceLink;
    @OneToOne
    @JoinColumn(name = "hotelId")
    private Hotel hotel;

    private String price;
    private Date startDate;
    private Date endDate;
    private String description;

    public Booking() {}
    public Booking(String userID, String sourceLink, String price, Date startDate, Date endDate) {
        this.userID = userID;
        this.sourceLink = sourceLink;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getEndDate() {
        return endDate;
    }

    public Long getBookingID() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getDescription() {
        return description;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public String getPrice() {
        return price;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

}
