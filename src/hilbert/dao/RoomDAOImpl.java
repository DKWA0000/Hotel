package hilbert.dao;

import hilbert.connector.MysqlConnector;
import hilbert.model.Room;

import java.sql.*;
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
    public List<Room> allRooms()throws SQLException {
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

}

