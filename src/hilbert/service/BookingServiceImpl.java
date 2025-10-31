package hilbert.service;

import hilbert.dao.BookingDaoImpl;
import hilbert.model.Booking;

import java.util.List;

public class BookingServiceImpl implements BookingService {

    BookingDaoImpl impl = new BookingDaoImpl();

    @Override
    public int addBooking(Booking booking){
        return impl.addBooking(booking);
    }

    @Override
    public List<Booking> showAllBookings() {
        return impl.getAllBookings();
    }

    @Override
    public List<Booking> getBookingByEmail(String email) {
        return impl.getBookingByEmail(email);
    }

    @Override
    public int removeBooking(int booking_id) {
        return impl.removeBooking(booking_id);
    }
}
