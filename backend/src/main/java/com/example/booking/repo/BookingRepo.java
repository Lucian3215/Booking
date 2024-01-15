package com.example.booking.repo;

import com.example.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    void deleteBookingById(Long id);
    Booking findBookingById(Long id);
    List<Booking> findBookingByUserID(String token);
}
