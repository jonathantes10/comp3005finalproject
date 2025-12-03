package model;

import java.sql.Timestamp;


public class HealthMetric {
    private int MetricID;
    private int MemberID;
    private Timestamp Timestamp;
    private Double HeightInches;
    private Double WeightLbs;
    private Integer HeartRateBPM;

    public HealthMetric(int MetricID, int MemberID, Timestamp Timestamp, Double HeightInches, Double WeightLbs, Integer HeartRateBPM) {
        this.MetricID = MetricID;
        this.MemberID = MemberID;
        this.Timestamp = Timestamp;
        this.HeightInches = HeightInches;
        this.WeightLbs = WeightLbs;
        this.HeartRateBPM = HeartRateBPM;
    }

    public int getMetricID() {
        return MetricID;
    }

    public int getMemberID() {
        return MemberID;
    }

    public Timestamp getTimestamp() {
        return Timestamp;
    }

    public Double getHeightInches() {
        return HeightInches;
    }

    public Double getWeightLbs() {
        return WeightLbs;
    }

    public Integer getHeartRateBPM() {
        return HeartRateBPM;
    }

    @Override
    public String toString() {
        return "HealthMetric: MetricID=" + MetricID +
                ", MemberID=" + MemberID +
                ", Timestamp=" + Timestamp +
                ", HeightInches=" + HeightInches +
                ", WeightLbs=" + WeightLbs +
                ", HeartRateBPM=" + HeartRateBPM;
    }
}