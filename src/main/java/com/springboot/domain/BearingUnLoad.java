package com.springboot.domain;

import java.io.Serializable;

public class BearingUnLoad implements Serializable {
    private Integer id;
    private Integer wheelId;
    private String  bearingPosition;
    private String  bearingMadeIn;
    private String  repairProgress;
    private String  level;
    private String  bearingAssembleDate;
    private String  unloadReason;
    private String  unloadDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWheelId() {
        return wheelId;
    }

    public void setWheelId(Integer wheelId) {
        this.wheelId = wheelId;
    }

    public String getBearingPosition() {
        return bearingPosition;
    }

    public void setBearingPosition(String bearingPosition) {
        this.bearingPosition = bearingPosition;
    }

    public String getBearingMadeIn() {
        return bearingMadeIn;
    }

    public void setBearingMadeIn(String bearingMadeIn) {
        this.bearingMadeIn = bearingMadeIn;
    }

    public String getRepairProgress() {
        return repairProgress;
    }

    public void setRepairProgress(String repairProgress) {
        this.repairProgress = repairProgress;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBearingAssembleDate() {
        return bearingAssembleDate;
    }

    public void setBearingAssembleDate(String bearingAssembleDate) {
        this.bearingAssembleDate = bearingAssembleDate;
    }

    public String getUnloadReason() {
        return unloadReason;
    }

    public void setUnloadReason(String unloadReason) {
        this.unloadReason = unloadReason;
    }

    public String getUnloadDate() {
        return unloadDate;
    }

    public void setUnloadDate(String unloadDate) {
        this.unloadDate = unloadDate;
    }

    @Override
    public String toString() {
        return "BearingUnLoad{" +
                "id=" + id +
                ", wheelId=" + wheelId +
                ", bearingPosition='" + bearingPosition + '\'' +
                ", bearingMadeIn='" + bearingMadeIn + '\'' +
                ", repairProgress='" + repairProgress + '\'' +
                ", level='" + level + '\'' +
                ", bearingAssembleDate='" + bearingAssembleDate + '\'' +
                ", unloadReason='" + unloadReason + '\'' +
                ", unloadDate='" + unloadDate + '\'' +
                '}';
    }
}
