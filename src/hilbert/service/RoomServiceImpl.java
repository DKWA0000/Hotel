package hilbert.service;

import hilbert.dao.RoomDAOImpl;
import hilbert.model.Room;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RoomServiceImpl implements RoomService{

    private RoomDAOImpl roomDAO = new RoomDAOImpl();

    @Override
    public void addRoom(String roomNumber, String type, double price) throws SQLException, ClassNotFoundException {
        if(price <= 0){
            System.out.println("Price can not be less then zero.");
            return;
        }

        roomDAO.addRoom(new Room(roomNumber,type, price));
    }

    @Override
    public List<Room> allRooms() throws SQLException, ClassNotFoundException {
        return roomDAO.allRooms();
    }



    @Override
    public List<Room> listAvailableRooms(LocalDate startDate, LocalDate endDate) throws SQLException, ClassNotFoundException {
        return roomDAO.listAvailableRooms(startDate, endDate);
    }

    @Override
    public void updateRoomPrice(int id, double price) {
        Optional<Room> room = roomDAO.getById(id);
        if(room.isPresent()){
            roomDAO.updateRoomPrice(id, price);
            System.out.println("Room price updated successfully.");
        } else {
            System.out.println("Room with id " + id + " does not exist.");
        }
    }

    @Override
    public void updateRoomType(int id, String type) {
        Optional<Room> room = roomDAO.getById(id);
        if(room.isPresent()){
            roomDAO.updateRoomType(id, type);
            System.out.println("Room updated successfully.");
        } else {
            System.out.println("Room with id " + id + " does not exist.");
        }

    }


}
