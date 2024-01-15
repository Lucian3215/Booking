package com.example.booking.service;

import com.example.booking.model.Booking;
import com.example.booking.model.Hotel;
import com.example.booking.repo.BookingRepo;
import com.example.booking.repo.HotelRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HotelService {
    private final HotelRepo hotelRepo;
    private final BookingRepo bookingRepo;

    @Autowired
    public HotelService(HotelRepo hotelRepo, BookingRepo bookingRepo) {
        this.hotelRepo = hotelRepo;
        this.bookingRepo = bookingRepo;
    }

    public Hotel addHotel(Hotel hotel, Long bookingID) {
        Hotel newHotel =  this.hotelRepo.save(hotel);
        Booking booking = this.bookingRepo.findBookingById(bookingID);
        booking.setHotel(newHotel);
        bookingRepo.save(booking);
        return newHotel;
    }
}
