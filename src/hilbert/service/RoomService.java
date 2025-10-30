package hilbert.service;

import hilbert.model.Room;

import java.sql.SQLException;
import java.util.List;

public interface RoomService {
    void addRoom(String roomNumber, String type, double price) throws SQLException;
    List<Room> allRooms() throws SQLException;
}
