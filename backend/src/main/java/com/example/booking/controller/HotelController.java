package com.example.booking.controller;

import com.example.booking.model.Hotel;
import com.example.booking.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/add/{bookingID}")
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel, @PathVariable Long bookingID) {
        return new ResponseEntity<>(hotelService.addHotel(hotel, bookingID), HttpStatus.CREATED);
    }
}
