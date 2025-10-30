package hilbert.service;

import hilbert.model.Room;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    void addRoom(String roomNumber, String type, double price) throws SQLException;
    List<Room> allRooms() throws SQLException;
    List<Room> listAvailableRooms(LocalDate startDate, LocalDate endDate) throws SQLException;

}
