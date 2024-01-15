package com.example.booking.repo;

import com.example.booking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepo extends JpaRepository<Hotel, Long> {
    void deleteHotelById(Long id);

    Hotel findHotelByBookingId(Long id);
}
