package hilbert.service;

import java.sql.SQLException;

public interface RoomService {
    void addRoom(String roomNumber, String type, double price) throws SQLException;
}
