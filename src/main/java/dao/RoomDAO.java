package dao;

import db.DatabaseConnection;
import model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public void addRoom(String name, int capacity) {
        String sql = "INSERT INTO Room (name, capacity) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, capacity);

            int rows = ps.executeUpdate();
            System.out.println("addRoom: " + rows + " row(s) inserted.");

        } catch (SQLException e) {
            System.out.println("Error in addRoom: " + e.getMessage());
        }
    }

    public void updateRoomCapacity(int roomId, int newCap) {
        String sql = "UPDATE Room SET capacity = ? WHERE room_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newCap);
            ps.setInt(2, roomId);

            int rows = ps.executeUpdate();
            System.out.println("updateRoomCapacity: " + rows + " row(s) updated.");

        } catch (SQLException e) {
            System.out.println("Error in updateRoomCapacity: " + e.getMessage());
        }
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT room_id, name, capacity FROM Room ORDER BY room_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                rooms.add(new Room(
                        rs.getInt("room_id"),
                        rs.getString("name"),
                        rs.getInt("capacity")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error in getAllRooms: " + e.getMessage());
        }
        return rooms;
    }
}