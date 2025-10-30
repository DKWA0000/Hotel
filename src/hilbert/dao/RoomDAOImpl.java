package hilbert.dao;

import hilbert.connector.MysqlConnector;
import hilbert.model.Room;

import java.sql.*;

public class RoomDAOImpl implements RoomDAO {

    @Override
    public void addRoom(Room room)  throws SQLException {
        try (Connection conn = MysqlConnector.getConnection()) {

            String insertRoom = "INSERT INTO room (roomnumber, type, price) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertRoom, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, room.getRoomNumber());
                ps.setString(2, room.getType());
                ps.setDouble(3, room.getPrice());

                ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                while(keys.next()){
                    System.out.println("Room has been created successfully with id: " + keys.getInt(1));
                }
            } catch (SQLIntegrityConstraintViolationException ex){
                System.out.println("Room number already in use, create a new one.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
