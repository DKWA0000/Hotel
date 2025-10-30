package hilbert.dao;

import hilbert.model.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingDao {

    List<Booking> getAllBookings();
    int addBooking(Booking booking);

}
