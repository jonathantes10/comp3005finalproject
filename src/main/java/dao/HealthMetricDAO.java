package dao;

import db.DatabaseConnection;
import model.HealthMetric;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HealthMetricDAO {

    public void addHealthMetric(int memberId, Double heightInches, Double weightLbs, Integer heartRateBpm) {

        String sql = "INSERT INTO HealthMetric (member_id, height_inches, weight_lbs, heart_rate_bpm) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, memberId);

            if (heightInches != null) {
                ps.setDouble(2, heightInches);
            } else {
                ps.setNull(2, Types.NUMERIC);
            }

            if (weightLbs != null) {
                ps.setDouble(3, weightLbs);
            } else {
                ps.setNull(3, Types.NUMERIC);
            }

            if (heartRateBpm != null) {
                ps.setInt(4, heartRateBpm);
            } else {
                ps.setNull(4, Types.INTEGER);
            }

            int rows = ps.executeUpdate();
            System.out.println("addHealthMetric: " + rows + " row(s) inserted.");

        } catch (SQLException e) {
            System.out.println("Error in addHealthMetric: " + e.getMessage());
        }
    }

    public List<HealthMetric> getMetricsForMember(int memberId) {
        List<HealthMetric> list = new ArrayList<>();

        String sql = "SELECT metric_id, member_id, recorded_at, height_inches, weight_lbs, heart_rate_bpm " +
                "FROM HealthMetric " +
                "WHERE member_id = ? " +
                "ORDER BY recorded_at DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, memberId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int metricId = rs.getInt("metric_id");
                    int mId = rs.getInt("member_id");
                    Timestamp recordedAt = rs.getTimestamp("recorded_at");

                    java.math.BigDecimal heightBD = rs.getBigDecimal("height_inches");
                    java.math.BigDecimal weightBD = rs.getBigDecimal("weight_lbs");

                    Double heightInches = heightBD != null ? heightBD.doubleValue() : null;
                    Double weightLbs    = weightBD != null ? weightBD.doubleValue() : null;

                    Integer heartRate = (Integer) rs.getObject("heart_rate_bpm");

                    list.add(new HealthMetric(
                            metricId,
                            mId,
                            recordedAt,
                            heightInches,
                            weightLbs,
                            heartRate
                    ));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error in getMetricsForMember: " + e.getMessage());
        }

        return list;
    }
}