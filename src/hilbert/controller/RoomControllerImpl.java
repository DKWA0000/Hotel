package hilbert.controller;

import hilbert.model.Room;
import hilbert.service.RoomServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    @Override
    public List<Room> listAvailableRooms() {
        System.out.println("Enter start date: (use pattern 'yyyy-mm-dd') ");
        String startDate = sc.nextLine();
        System.out.println("Enter end date: (use pattern 'yyyy-mm-dd') ");
        String endDate = sc.nextLine();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate stDate = LocalDate.parse(startDate, formatter);
            LocalDate enDate = LocalDate.parse(endDate, formatter);

            List<Room> rooms = roomService.listAvailableRooms(stDate, enDate);
            rooms.forEach(System.out::println);
        } catch (DateTimeParseException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
