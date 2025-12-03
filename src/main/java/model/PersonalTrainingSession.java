package model;

import java.sql.Timestamp;

public class PersonalTrainingSession {
    private int ptSessionId;
    private int memberId;
    private int trainerId;
    private int roomId;
    private Integer adminId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String status;

    public PersonalTrainingSession(int ptSessionId, int memberId, int trainerId, int roomId, Integer adminId, Timestamp startTime, Timestamp endTime, String status) {
        this.ptSessionId = ptSessionId;
        this.memberId = memberId;
        this.trainerId = trainerId;
        this.roomId = roomId;
        this.adminId = adminId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public int getPtSessionId() {
        return ptSessionId;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "PersonalTrainingSession: ptSessionId=" + ptSessionId +
                ", memberId=" + memberId +
                ", trainerId=" + trainerId +
                ", roomId=" + roomId +
                ", adminId=" + adminId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status='" + status + '\'';
    }
}
