package hilbert.service;

import hilbert.dao.BookingDaoImpl;
import hilbert.dao.CustomerDaoImpl;
import hilbert.dao.RoomDAOImpl;
import hilbert.model.Booking;
import hilbert.model.Customer;

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

    @Override
    public void getNumberBookingPerCustomer(String email) {
        List<Customer> customer = new CustomerDaoImpl().findCustomerByEmail(email);
        if(customer.isEmpty()) {
            System.out.println("No customer found by given email.");
        } else {
            int result = impl.getNumberBookingPerCustomer(customer.getFirst().getId());
            if(result > 0){
                System.out.println(result + " bookings has  " + customer.getFirst().getName() );
            } else {
                System.out.println(customer.getFirst().getName() + " does not have yet bookings.");
            }
        }
    }
}
