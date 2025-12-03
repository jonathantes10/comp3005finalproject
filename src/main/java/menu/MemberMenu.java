package menu;

import dao.MemberDAO;
import dao.HealthMetricDAO;
import dao.PersonalTrainingSessionDAO;
import model.HealthMetric;
import model.PersonalTrainingSession;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MemberMenu {

    public static void show(Scanner scanner) {
        MemberDAO memberDAO = new MemberDAO();
        HealthMetricDAO metricDAO = new HealthMetricDAO();
        PersonalTrainingSessionDAO sessionDAO = new PersonalTrainingSessionDAO();

        boolean back = false;

        while (!back) {
            System.out.println("\n---- Member Menu ----");
            System.out.println("1) Register new member");
            System.out.println("2) Update member profile");
            System.out.println("3) Log health metric");
            System.out.println("4) View health history");
            System.out.println("5) Book PT session");
            System.out.println("0) Back");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> registerMember(scanner, memberDAO);
                case "2" -> updateProfile(scanner, memberDAO);
                case "3" -> logHealthMetric(scanner, metricDAO);
                case "4" -> viewHealthHistory(scanner, metricDAO);
                case "5" -> bookPtSession(scanner, sessionDAO);
                case "0" -> back = true;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void registerMember(Scanner scanner, MemberDAO memberDAO) {
        try {
            System.out.println("\n--- Register New Member ---");
            System.out.print("First name: ");
            String firstName = scanner.nextLine();

            System.out.print("Last name: ");
            String lastName = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Date of birth (YYYY-MM-DD): ");
            String dobStr = scanner.nextLine();
            Date dob = Date.valueOf(LocalDate.parse(dobStr));

            System.out.print("Phone number: ");
            String phone = scanner.nextLine();

            memberDAO.addMember(firstName, lastName, email, dob, phone);
        } catch (Exception e) {
            System.out.println("Error in registerMember: " + e.getMessage());
        }
    }

    private static void updateProfile(Scanner scanner, MemberDAO memberDAO) {
        try {
            System.out.println("\n--- Update Member Profile ---");
            System.out.print("Member ID: ");
            int memberId = Integer.parseInt(scanner.nextLine());

            System.out.print("New email: ");
            String newEmail = scanner.nextLine();

            System.out.print("New phone number: ");
            String newPhone = scanner.nextLine();

            memberDAO.updateMemberProfile(memberId, newEmail, newPhone);
        } catch (Exception e) {
            System.out.println("Error in updateProfile: " + e.getMessage());
        }
    }

    private static void logHealthMetric(Scanner scanner, HealthMetricDAO metricDAO) {
        try {
            System.out.println("\n--- Log Health Metric ---");
            System.out.print("Member ID: ");
            int memberId = Integer.parseInt(scanner.nextLine());

            System.out.print("Height (inches): ");
            String hStr = scanner.nextLine();
            Double height = hStr.isBlank() ? null : Double.parseDouble(hStr);

            System.out.print("Weight (lbs): ");
            String wStr = scanner.nextLine();
            Double weight = wStr.isBlank() ? null : Double.parseDouble(wStr);

            System.out.print("Heart rate (bpm): ");
            String hrStr = scanner.nextLine();
            Integer heartRate = hrStr.isBlank() ? null : Integer.parseInt(hrStr);

            metricDAO.addHealthMetric(memberId, height, weight, heartRate);
        } catch (Exception e) {
            System.out.println("Error in logHealthMetric: " + e.getMessage());
        }
    }

    private static void viewHealthHistory(Scanner scanner, HealthMetricDAO metricDAO) {
        try {
            System.out.println("\n--- View Health History ---");
            System.out.print("Member ID: ");
            int memberId = Integer.parseInt(scanner.nextLine());

            List<HealthMetric> metrics = metricDAO.getMetricsForMember(memberId);
            if (metrics.isEmpty()) {
                System.out.println("No metrics found for this member.");
            } else {
                metrics.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error in viewHealthHistory: " + e.getMessage());
        }
    }

    private static void bookPtSession(Scanner scanner, PersonalTrainingSessionDAO sessionDAO) {
        try {
            System.out.println("\n--- Book PT Session ---");
            System.out.print("Member ID: ");
            int memberId = Integer.parseInt(scanner.nextLine());

            System.out.print("Trainer ID: ");
            int trainerId = Integer.parseInt(scanner.nextLine());

            System.out.print("Room ID: ");
            int roomId = Integer.parseInt(scanner.nextLine());

            System.out.print("Admin ID (blank for none): ");
            String adminStr = scanner.nextLine();
            Integer adminId = adminStr.isBlank() ? null : Integer.parseInt(adminStr);

            System.out.print("Start time (YYYY-MM-DD HH:MM): ");
            String startStr = scanner.nextLine();
            System.out.print("End time   (YYYY-MM-DD HH:MM): ");
            String endStr = scanner.nextLine();

            Timestamp startTime = Timestamp.valueOf(startStr + ":00");
            Timestamp endTime = Timestamp.valueOf(endStr + ":00");

            sessionDAO.bookSession(memberId, trainerId, roomId, adminId, startTime, endTime);
        } catch (Exception e) {
            System.out.println("Error in bookPtSession: " + e.getMessage());
        }
    }
}
