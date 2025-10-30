package hilbert.dao;

import hilbert.connector.MysqlConnector;
import hilbert.model.Booking;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoImpl implements BookingDao{

    public List<Booking> getAllBookings() {
        List<Booking> tmp = new ArrayList<>();
        try (Connection connection = MysqlConnector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("""
                    SELECT * FROM booking;
                    """);
            while (rs.next()) {
                tmp.add(new Booking(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getInt("room_id"),
                        LocalDate.parse(rs.getString("checkin_date")),
                        LocalDate.parse(rs.getString("checkout_date")),
                        rs.getString("status")));
            }
        }
            catch (SQLException e){
                System.out.println("Failed to excecute query " + e.getMessage());
            }
            return tmp;
        }

        public int addBooking(Booking booking){
        if(checkIfBookingValid(booking.getCheckInDate(), booking.getCheckOutDate(), booking.getRoomId())) {
            List<Booking> tmp = new ArrayList<>();
            try (Connection connection = MysqlConnector.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "INSERT INTO booking(" +
                                 "id, customer_id, room_id, checkin_date, checkout_date, status)" +
                                 "VALUES(?, ?, ?, ?, ?, ?)")) {
                statement.setInt(1, booking.getId());
                statement.setInt(2, booking.getCustomerId());
                statement.setInt(3, booking.getRoomId());
                statement.setString(4, booking.getCheckInDate().toString());
                statement.setString(5, booking.getCheckOutDate().toString());
                statement.setString(6, booking.getStatus());
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Failed to excecute query " + e.getMessage());
            }
        }
            return 0;
        }

        public boolean checkIfBookingValid(LocalDate startDate, LocalDate endDate,
                                           int room_id){
            try (Connection connection = MysqlConnector.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "SELECT NOT EXISTS(SELECT * FROM booking WHERE (" +
                                 "((? BETWEEN checkin_date AND checkout_date) AND " +
                                 "(? BETWEEN checkin_date AND checkout_date)" +
                                 ") OR" +
                                 "((checkin_date BETWEEN ? AND ?) AND " +
                                 "(checkout_date BETWEEN ? AND ?))" +
                                 ") AND (room_id = ?))")) {
                statement.setString(1, startDate.toString());
                statement.setString(2, endDate.toString());
                statement.setString(3, startDate.toString());
                statement.setString(4, endDate.toString());
                statement.setString(5, startDate.toString());
                statement.setString(6, endDate.toString());
                statement.setInt(7, room_id);
                ResultSet rs = statement.executeQuery();
                rs.next();
                if((rs.getInt(1) == 0) || (startDate.toString().equals(endDate.toString()))){
                    return false;
                }
            }
            catch (SQLException e){
                System.out.println("Failed to excecute query " + e.getMessage());
            }
            return true;
        }
    }
