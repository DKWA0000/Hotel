package hilbert.dao;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import hilbert.connector.MysqlConnector;
import hilbert.model.Room;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    @Override
    public void addRoom(Room room) throws SQLException {
        try (Connection conn = MysqlConnector.getConnection()) {

            String insertRoom = "INSERT INTO room (roomnumber, type, price) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertRoom, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, room.getRoomNumber());
                ps.setString(2, room.getType());
                ps.setDouble(3, room.getPrice());

                ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                while (keys.next()) {
                    System.out.println("Room has been created successfully with id: " + keys.getInt(1));
                }
            } catch (SQLIntegrityConstraintViolationException ex) {
                System.out.println("Room number already in use, create a new one.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Room> allRooms() throws SQLException {
        try (Connection conn = MysqlConnector.getConnection()) {
            List<Room> rooms = new ArrayList<>();
            String showAllRooms = "SELECT * FROM room";
            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery(showAllRooms)) {
                while (rs.next()) {
                    Room room = new Room(
                            rs.getInt("id"),
                            rs.getString("roomNumber"),
                            rs.getString("type"),
                            rs.getDouble("price"));
                    rooms.add(room);
                }
                return rooms;
            }
        }
    }

    @Override
    public List<Room> listAvailableRooms(LocalDate startDate, LocalDate endDate) throws SQLException {
        try (Connection conn = MysqlConnector.getConnection()) {

            String sql = """
                     SELECT DISTINCT r.id, r.roomnumber, r.type, r.price
                          FROM room r
                          LEFT JOIN booking b 
                              ON r.id = b.room_id
                              AND b.status = 'ACTIVE'
                              AND (b.checkin_date <= ? AND b.checkout_date >= ?)
                          WHERE b.id IS NULL
                    """;

            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, startDate.toString()); // endDate
                ps.setString(2, endDate.toString()); // startDate

                ResultSet rs = ps.executeQuery();
                List<Room> rooms = new ArrayList<>();
                while (rs.next()) {
                    Room r = new Room(
                            rs.getInt("id"),
                            rs.getString("roomnumber"),
                            rs.getString("type"),
                            rs.getDouble("price")
                    );
                    rooms.add(r);
                }
                return rooms;
            } catch (SQLIntegrityConstraintViolationException ex) {
                System.out.println("Room number already in use, create a new one.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return List.of();
        }
    }

    @Override
    public int updateRoomPrice(int id, double price) {
        String sql = "UPDATE room SET price = ? WHERE id = ?";
        try (Connection conn = MysqlConnector.getConnection();
             PreparedStatement statment = conn.prepareStatement(sql)) {
            statment.setDouble(1, price);
            statment.setInt(2, id);

            return statment.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
