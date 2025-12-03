package dao;

import db.DatabaseConnection;
import model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberDAO {

    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();

        String sql = "SELECT member_id, first_name, last_name, email, date_of_birth, phone_number FROM Member";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("member_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                Date dob = rs.getDate("date_of_birth");
                String phone = rs.getString("phone_number");

                members.add(new Member(id, firstName, lastName, email, dob, phone));
            }

        } catch (SQLException e) {
            System.out.println("Error in getAllMembers: " + e.getMessage());
        }

        return members;
    }

    public void addMember(String firstName, String lastName, String email,
                          Date dateOfBirth, String phoneNumber) {

        String sql = "INSERT INTO Member (first_name, last_name, email, date_of_birth, phone_number) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setDate(4, (java.sql.Date) dateOfBirth);
            ps.setString(5, phoneNumber);

            int rows = ps.executeUpdate();
            System.out.println("addMember: " + rows + " row(s) inserted.");

        } catch (SQLException e) {
            System.out.println("Error in addMember: " + e.getMessage());
        }
    }

    public void updateMemberProfile(int memberId, String newEmail, String newPhoneNumber) {

        String sql = "UPDATE Member SET email = ?, phone_number = ? WHERE member_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newEmail);
            ps.setString(2, newPhoneNumber);
            ps.setInt(3, memberId);

            int rows = ps.executeUpdate();
            System.out.println("updateMemberProfile: " + rows + " row(s) updated.");

        } catch (SQLException e) {
            System.out.println("Error in updateMemberProfile: " + e.getMessage());
        }
    }
}
