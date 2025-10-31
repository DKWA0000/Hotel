package hilbert.service;

import hilbert.model.Room;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    void addRoom(String roomNumber, String type, double price) throws SQLException, ClassNotFoundException;
    List<Room> allRooms() throws SQLException, ClassNotFoundException;
    List<Room> listAvailableRooms(LocalDate startDate, LocalDate endDate) throws SQLException, ClassNotFoundException;
    void updateRoomPrice(int id, double price);
    void updateRoomType(int id, String type) throws SQLException;
}
