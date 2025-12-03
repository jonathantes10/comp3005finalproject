package dao;

import db.DatabaseConnection;
import model.PersonalTrainingSession;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonalTrainingSessionDAO {

    public void bookSession(int memberId, int trainerId, int roomId, Integer adminId,
                            Timestamp startTime, Timestamp endTime) {

        String sql = "INSERT INTO PersonalTrainingSession " +
                "(member_id, trainer_id, room_id, admin_id, start_time, end_time, status) VALUES (?, ?, ?, ?, ?, ?, 'Scheduled')";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, memberId);
            ps.setInt(2, trainerId);
            ps.setInt(3, roomId);

            if (adminId != null) {
                ps.setInt(4, adminId);
            } else {
                ps.setNull(4, Types.INTEGER);
            }

            ps.setTimestamp(5, startTime);
            ps.setTimestamp(6, endTime);

            int rows = ps.executeUpdate();
            System.out.println("bookSession: " + rows + " row(s) inserted.");

        } catch (SQLException e) {
            System.out.println("Error in bookSession: " + e.getMessage());
        }
    }

    public void updateSessionStatus(int ptSessionId, String newStatus) {
        String sql = "UPDATE PersonalTrainingSession SET status = ? WHERE pt_session_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newStatus);
            ps.setInt(2, ptSessionId);

            int rows = ps.executeUpdate();
            System.out.println("updateSessionStatus: " + rows + " row(s) updated.");

        } catch (SQLException e) {
            System.out.println("Error in updateSessionStatus: " + e.getMessage());
        }
    }

    public List<PersonalTrainingSession> getUpcomingSessionsForTrainer(int trainerId) {
        List<PersonalTrainingSession> sessions = new ArrayList<>();

        String sql = "SELECT pt_session_id, member_id, trainer_id, room_id, admin_id, " +
                "start_time, end_time, status " +
                "FROM PersonalTrainingSession " +
                "WHERE trainer_id = ? " +
                "ORDER BY start_time ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, trainerId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int sessionId = rs.getInt("pt_session_id");
                    int memberId = rs.getInt("member_id");
                    int tId = rs.getInt("trainer_id");
                    int roomId = rs.getInt("room_id");
                    Integer adminId = (Integer) rs.getObject("admin_id");
                    Timestamp startTime = rs.getTimestamp("start_time");
                    Timestamp endTime = rs.getTimestamp("end_time");
                    String status = rs.getString("status");

                    sessions.add(new PersonalTrainingSession(
                            sessionId, memberId, tId, roomId, adminId, startTime, endTime, status
                    ));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error in getUpcomingSessionsForTrainer: " + e.getMessage());
        }

        return sessions;
    }
}
