package menu;

import dao.PersonalTrainingSessionDAO;
import model.PersonalTrainingSession;

import java.util.List;
import java.util.Scanner;

public class TrainerMenu {

    public static void show(Scanner scanner) {
        PersonalTrainingSessionDAO sessionDAO = new PersonalTrainingSessionDAO();
        boolean back = false;

        while (!back) {
            System.out.println("\n---- Trainer Menu ---");
            System.out.println("1) View upcoming sessions");
            System.out.println("0) Back");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> viewSchedule(scanner, sessionDAO);
                case "0" -> back = true;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void viewSchedule(Scanner scanner, PersonalTrainingSessionDAO sessionDAO) {
        try {
            System.out.println("\n--- View Trainer Schedule ---");
            System.out.print("Trainer ID: ");
            int trainerId = Integer.parseInt(scanner.nextLine());

            List<PersonalTrainingSession> sessions = sessionDAO.getUpcomingSessionsForTrainer(trainerId);
            if (sessions.isEmpty()) {
                System.out.println("No sessions found for this trainer.");
            } else {
                sessions.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error in viewSchedule: " + e.getMessage());
        }
    }
}
