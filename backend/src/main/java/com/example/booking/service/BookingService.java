package com.example.booking.service;

import com.example.booking.model.Booking;
import com.example.booking.repo.BookingRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BookingService {
    private final BookingRepo bookingRepo;


    @Autowired
    public BookingService(BookingRepo bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    public List<Booking> getBookingsByUserToken(String token) {
        return this.bookingRepo.findBookingByUserID(token);
    }

    public Booking addBooking(Booking booking) {
        return this.bookingRepo.save(booking);
    }

    public void deleteBooking(Long id){
        this.bookingRepo.deleteBookingById(id);
    }
}
