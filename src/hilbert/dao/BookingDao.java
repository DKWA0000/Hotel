package hilbert.dao;

import hilbert.model.Booking;

import java.util.List;

public interface BookingDao {

    List<Booking> getAllBookings();
    int addBooking(Booking booking);
    List<Booking> getBookingByEmail(String email);
    int removeBooking(int booking_id);
    double getAveragePriceBooked();

    int getNumberBookingPerCustomer(int customer_id);
}
