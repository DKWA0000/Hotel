package hilbert.controller;

import hilbert.service.RoomServiceImpl;

import java.sql.SQLException;
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
}
