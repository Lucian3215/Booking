package com.example.booking.model;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, updatable = false)
    protected Long id;
    private String token;

    public User(String token) {
        this.token = token;
    }

    public User() {}

    public Long getId() {
        return id;
    }
    public String getToken() {
        return token;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
