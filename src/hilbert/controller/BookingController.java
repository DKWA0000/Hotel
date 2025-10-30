package hilbert.controller;

import java.io.BufferedReader;
import java.io.IOException;

public interface BookingController {

    void bookRoom(BufferedReader br) throws IOException;
    void showAllBookings();
    void getBookingByEmail(BufferedReader br) throws IOException;
    void removeBooking(BufferedReader br) throws IOException;
}
