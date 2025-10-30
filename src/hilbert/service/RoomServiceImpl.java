package hilbert.service;

import hilbert.dao.RoomDAOImpl;
import hilbert.model.Room;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class RoomServiceImpl implements RoomService{

    private RoomDAOImpl roomDAO = new RoomDAOImpl();

    @Override
    public void addRoom(String roomNumber, String type, double price) throws SQLException {
        if(price <= 0){
            System.out.println("Price can not be less then zero.");
            return;
        }

        roomDAO.addRoom(new Room(roomNumber,type, price));
    }

    @Override
    public List<Room> allRooms() throws SQLException {
        return roomDAO.allRooms();
    }



    @Override
    public List<Room> listAvailableRooms(LocalDate startDate, LocalDate endDate) throws SQLException {
        return roomDAO.listAvailableRooms(startDate, endDate);
    }

}
