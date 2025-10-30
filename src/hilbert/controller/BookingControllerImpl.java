package hilbert.controller;

import hilbert.model.Booking;
import hilbert.service.BookingServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class BookingControllerImpl implements BookingController{

    BookingServiceImpl impl = new BookingServiceImpl();

    @Override
    public void bookRoom(BufferedReader br) throws IOException {
        System.out.println("Input the customer_id");
        int customerTmp = Integer.parseInt(br.readLine());
        System.out.println("Input the roomNr");
        int roomTmp = Integer.parseInt(br.readLine());
        System.out.println("Input the startDate(YYYY-MM-DD)");
        String startDateTmp = br.readLine();
        System.out.println("Input the endDate(YYYY-MM-DD)");
        String endDateTmp = br.readLine();
        int result = impl.addBooking(new Booking(0, customerTmp, roomTmp,
                LocalDate.parse(startDateTmp), LocalDate.parse(endDateTmp), "active"));
        if(result == 1){
            System.out.println("Room: "  + roomTmp + " is now booked");
            return;
        }
        System.out.println("Room: " + roomTmp + " is already booked");
    }

    @Override
    public void showAllBookings() {
        List<Booking> bookingsTmp = impl.showAllBookings();
        if(bookingsTmp.isEmpty()){
            System.out.println("There are no bookings at the moment");
            return;
        }
        for(int i = 0; i < bookingsTmp.size(); i++){
            System.out.println(bookingsTmp.get(i).toString());
        }
    }

    @Override
    public void getBookingByEmail(BufferedReader br) throws IOException {
        System.out.println("Enter email");
        List<Booking> bookingsTmp = impl.getBookingByEmail(br.readLine());
        if(bookingsTmp.isEmpty()){
            System.out.println("There are no bookings for this email");
            return;
        }
        for(int i = 0; i < bookingsTmp.size(); i++){
            System.out.println(bookingsTmp.get(i).toString());
        }
    }

    @Override
    public void removeBooking(BufferedReader br) throws IOException {
        System.out.println("Enter booking id of the booking to remove");
        int result = impl.removeBooking(Integer.parseInt(br.readLine()));
        if(result == 1){
            System.out.println("Bokking was removed");
            return;
        }
        System.out.println("Booking was not removed");

    }
}
