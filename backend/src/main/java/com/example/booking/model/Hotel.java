package com.example.booking.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Hotel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, updatable = false)
    protected Long id;

    private Long bookingId;
    private String name;
    private String address;
    private String imageUrl;
    private String maxImageUrl;
    private String description;
    private Integer roomsCount;

    public Hotel() {}

    public Hotel(String name, String address, String imageUrl, String maxImageUrl, String description, Integer roomsCount) {
        this.name = name;
        this.address = address;
        this.imageUrl = imageUrl;
        this.maxImageUrl = maxImageUrl;
        this.description = description;
        this.roomsCount = roomsCount;
    }
    public Long getId() {
        return id;
    }
    public Long getBookingId() {
        return bookingId;
    }
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getMaxImageUrl() {
        return maxImageUrl;
    }

    public String getDescription() {
        return description;
    }

    public Integer getRoomsCount() { return roomsCount; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setMaxImageUrl(String maxImageUrl) {
        this.maxImageUrl = maxImageUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRoomsCount(Integer roomsCount) {
        this.roomsCount = roomsCount;
    }
}
