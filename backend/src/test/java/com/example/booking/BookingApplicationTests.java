package com.example.booking;

import com.example.booking.controller.BookingController;
import com.example.booking.controller.UserController;
import com.example.booking.model.Booking;
import com.example.booking.model.Hotel;
import com.example.booking.model.User;
import com.example.booking.service.BookingService;
import com.example.booking.service.HotelService;
import com.example.booking.service.UserService;
import com.example.booking.repo.BookingRepo;
import com.example.booking.repo.HotelRepo;
import com.example.booking.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BookingApplicationTests {

	@Autowired
	private BookingController bookingController;

	@Autowired
	private UserController userController;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private UserService userService;

	@Mock
	private BookingRepo bookingRepo;

	@Mock
	private HotelRepo hotelRepo;

	@Mock
	private UserRepo userRepo;

	@Test
	public void testBookingController() {
		assertNotNull(bookingController, "Booking Controller should not be null");
	}

	@Test
	public void testUserController() {
		assertNotNull(userController, "User Controller should not be null");
	}

	@Test
	public void testBookingService() {
		assertNotNull(bookingService, "Booking Service should not be null");
	}

	@Test
	public void testHotelService() {
		assertNotNull(hotelService, "Hotel Service should not be null");
	}

	@Test
	public void testUserService() {
		assertNotNull(userService, "User Service should not be null");
	}

	@Test
	public void testGetBookingsByUserToken() {
		List<Booking> bookings = bookingController.getBookingsByUserToken("someToken").getBody();
		assertNotNull(bookings, "Bookings should not be null");
	}

	@Test
	public void testAddHotel() {
		Hotel hotel = new Hotel("HotelName", "Address", "ImageURL", "MaxImageURL", "Description", 10);
		Long bookingId = 1L;
		when(hotelRepo.save(any())).thenReturn(hotel);

		Booking booking = new Booking("userId", "sourceLink", "100", new Date(), new Date());
		when(bookingRepo.findBookingById(bookingId)).thenReturn(booking);

		Hotel result = hotelService.addHotel(hotel, bookingId);

		assertNotNull(result);
		assertEquals("HotelName", result.getName());
	}

	@Test
	public void testAddBookingWithHotel() {
		Booking booking = new Booking("userId", "sourceLink", "100", new Date(), new Date());
		Hotel hotel = new Hotel("HotelName", "Address", "ImageURL", "MaxImageURL", "Description", 10);
		hotel.setId(1L);
		booking.setHotel(hotel);

		when(bookingRepo.save(any())).thenReturn(booking);

		Booking result = bookingService.addBooking(booking);

		assertNotNull(result);
		assertNotNull(result.getHotel());
		assertEquals("HotelName", result.getHotel().getName());
	}

	@Test
	public void testAddUser() {
		// Arrange
		String token = "userToken";
		when(userRepo.save(any())).thenReturn(new User(token));

		// Act
		User result = userService.addUser(token);

		// Assert
		assertNotNull(result);
		assertEquals(token, result.getToken());
		// Add more assertions based on your business logic
	}

	@Test
	public void testDeleteBooking() {
		Long bookingId = 1L;

		bookingService.deleteBooking(bookingId);

		verify(bookingRepo, times(1)).deleteBookingById(bookingId);
	}

	@Test
	public void testAddHotelWithBooking() {
		Hotel hotel = new Hotel("HotelName", "Address", "ImageURL", "MaxImageURL", "Description", 10);
		Long bookingId = 1L;
		Booking booking = new Booking("userId", "sourceLink", "100", new Date(), new Date());
		booking.setId(bookingId);

		when(hotelRepo.save(any())).thenReturn(hotel);
		when(bookingRepo.findBookingById(bookingId)).thenReturn(booking);

		Hotel result = hotelService.addHotel(hotel, bookingId);

		assertNotNull(result);
	}
}
