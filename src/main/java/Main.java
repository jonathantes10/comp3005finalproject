import dao.HealthMetricDAO;
import dao.MemberDAO;
import dao.PersonalTrainingSessionDAO;
import menu.AdminMenu;
import menu.MemberMenu;
import menu.TrainerMenu;
import model.Member;
import model.PersonalTrainingSession;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("---- Health Club Management System ----");
            System.out.println("1) Member");
            System.out.println("2) Trainer");
            System.out.println("3) Admin");
            System.out.println("0) Exit");
            System.out.print("Choose role: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1" -> MemberMenu.show(scanner);
                case "2" -> TrainerMenu.show(scanner);
                case "3" -> AdminMenu.show(scanner);
                case "0" -> {
                    running = false;
                    System.out.println("Exiting");
                }
                default -> System.out.println("Invalid option.");
            }

            System.out.println();
        }

        scanner.close();
    }
}