package hilbert.service;

import hilbert.dao.RoomDAOImpl;
import hilbert.model.Room;

import java.sql.SQLException;

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
}
