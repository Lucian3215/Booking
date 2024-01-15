package com.example.booking.controller;

import com.example.booking.model.Booking;
import com.example.booking.service.BookingService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/get/{userToken}")
    public ResponseEntity<List<Booking>> getBookingsByUserToken(@PathVariable String userToken) {
        return new ResponseEntity<>(bookingService.getBookingsByUserToken(userToken), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking) {
        return new ResponseEntity<>(bookingService.addBooking(booking), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{bookingID}")
    @Transactional
    public ResponseEntity<Booking> deleteBooking(@PathVariable Long bookingID) {
        bookingService.deleteBooking(bookingID);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
