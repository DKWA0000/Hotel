package hilbert.dao;

import hilbert.model.Room;

import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomDAO {
    void addRoom(Room room) throws SQLException, ClassNotFoundException;
    List<Room> allRooms() throws SQLException, ClassNotFoundException;
    List<Room> listAvailableRooms(LocalDate startDate, LocalDate endDate) throws SQLException, ClassNotFoundException;
    void updateRoomPrice(int id, double price);
    void updateRoomType(int id, String type) throws SQLException, ClassNotFoundException;
    Optional<Room> getById(int id) throws SQLException, ClassNotFoundException;
}
