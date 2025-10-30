package hilbert.controller;

import hilbert.model.Room;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface RoomController {
    void addRoom() throws SQLException;
    List<Room> listAvailableRooms();
}
