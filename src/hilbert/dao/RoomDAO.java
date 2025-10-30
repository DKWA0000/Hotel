package hilbert.dao;

import hilbert.model.Room;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface RoomDAO {
    void addRoom(Room room)  throws SQLException;
    List<Room> listAvailableRooms(LocalDate startDate, LocalDate endDate) throws SQLException;
}
