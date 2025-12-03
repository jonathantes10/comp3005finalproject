package model;

public class Room {
    private int roomId;
    private String name;
    private int capacity;

    public Room(int roomId, String name, int capacity) {
        this.roomId = roomId;
        this.name = name;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Room: roomId=" + roomId +
                ", name='" + name + '\'' +
                ", capacity=" + capacity;
    }
}