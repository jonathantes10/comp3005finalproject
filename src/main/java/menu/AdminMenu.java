package menu;

import dao.RoomDAO;
import java.util.Scanner;

public class AdminMenu {

    public static void show(Scanner scanner) {
        RoomDAO roomDAO = new RoomDAO();
        boolean back = false;

        while (!back) {
            System.out.println("\n---- Admin Menu ----");
            System.out.println("1) Add Room");
            System.out.println("2) Update Room Capacity");
            System.out.println("3) View All Rooms");
            System.out.println("0) Back");
            System.out.print("Choose an option: ");

            switch (scanner.nextLine()) {
                case "1" -> addRoom(scanner, roomDAO);
                case "2" -> updateRoom(scanner, roomDAO);
                case "3" -> viewRooms(roomDAO);
                case "0" -> back = true;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void addRoom(Scanner scanner, RoomDAO dao) {
        System.out.println("\n--- Add Room ---");
        System.out.print("Room Name: ");
        String name = scanner.nextLine();
        System.out.print("Capacity: ");
        int cap = Integer.parseInt(scanner.nextLine());

        dao.addRoom(name, cap);
    }

    private static void updateRoom(Scanner scanner, RoomDAO dao) {
        System.out.println("\n--- Update Room Capacity ---");
        System.out.print("Room ID: ");
        int roomId = Integer.parseInt(scanner.nextLine());
        System.out.print("New Capacity: ");
        int cap = Integer.parseInt(scanner.nextLine());

        dao.updateRoomCapacity(roomId, cap);
    }

    private static void viewRooms(RoomDAO dao) {
        System.out.println("\n--- All Rooms ---");
        dao.getAllRooms().forEach(System.out::println);
    }
}