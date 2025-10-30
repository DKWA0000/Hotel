package hilbert.service;

import hilbert.model.Booking;

import java.util.List;

public interface BookingService {

    int addBooking(Booking booking);
    List<Booking> showAllBookings();
    List<Booking> getBookingByEmail(String email);
    int removeBooking(int booking_id);
}
