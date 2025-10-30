package hilbert.dao;

import hilbert.model.Room;

import java.sql.SQLException;

public interface RoomDAO {
    void addRoom(Room room)  throws SQLException;
}
