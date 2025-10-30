package hilbert.controller;

import hilbert.model.Room;
import hilbert.service.RoomServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RoomControllerImpl implements RoomController {

    private Scanner sc = new Scanner(System.in);
    private RoomServiceImpl roomService = new RoomServiceImpl();

    @Override
    public void addRoom() {
        System.out.println("Enter room number: ");
        String roomNumber = sc.nextLine();
        System.out.println("Enter room type: ");
        String roomType = sc.nextLine();
        System.out.println("Enter room price: ");
        double roomPrice = sc.nextDouble();

        try {
            roomService.addRoom(roomNumber, roomType, roomPrice);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showAllRooms() {
        try {
            List<Room> allrooms  = roomService.allRooms();
            allrooms.forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
