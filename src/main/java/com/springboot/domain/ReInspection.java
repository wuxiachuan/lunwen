package com.springboot.domain;

import java.io.Serializable;

public class ReInspection implements Serializable {
    private Integer	wheelId	;
    private String	reultAxleLeft	;
    private String	reultAxleRight	;
    private String	reultAxleNeckLeft	;
    private String	reultAxleNeckRight	;
    private String	reultisFinish	;
    private String	reultfinishTime	;
    private String	reultInspector	;

    public ReInspection() {
    }

    public Integer getWheelId() {
        return wheelId;
    }

    public void setWheelId(Integer wheelId) {
        this.wheelId = wheelId;
    }

    public String getReultAxleLeft() {
        return reultAxleLeft;
    }

    public void setReultAxleLeft(String reultAxleLeft) {
        this.reultAxleLeft = reultAxleLeft;
    }

    public String getReultAxleRight() {
        return reultAxleRight;
    }

    public void setReultAxleRight(String reultAxleRight) {
        this.reultAxleRight = reultAxleRight;
    }

    public String getReultAxleNeckLeft() {
        return reultAxleNeckLeft;
    }

    public void setReultAxleNeckLeft(String reultAxleNeckLeft) {
        this.reultAxleNeckLeft = reultAxleNeckLeft;
    }

    public String getReultAxleNeckRight() {
        return reultAxleNeckRight;
    }

    public void setReultAxleNeckRight(String reultAxleNeckRight) {
        this.reultAxleNeckRight = reultAxleNeckRight;
    }

    public String getReultisFinish() {
        return reultisFinish;
    }

    public void setReultisFinish(String reultisFinish) {
        this.reultisFinish = reultisFinish;
    }

    public String getReultfinishTime() {
        return reultfinishTime;
    }

    public void setReultfinishTime(String reultfinishTime) {
        this.reultfinishTime = reultfinishTime;
    }

    public String getReultInspector() {
        return reultInspector;
    }

    public void setReultInspector(String reultInspector) {
        this.reultInspector = reultInspector;
    }

    @Override
    public String toString() {
        return "ReInspection{" +
                "wheelId=" + wheelId +
                ", reultAxleLeft='" + reultAxleLeft + '\'' +
                ", reultAxleRight='" + reultAxleRight + '\'' +
                ", reultAxleNeckLeft='" + reultAxleNeckLeft + '\'' +
                ", reultAxleNeckRight='" + reultAxleNeckRight + '\'' +
                ", reultisFinish='" + reultisFinish + '\'' +
                ", reultfinishTime='" + reultfinishTime + '\'' +
                ", reultInspector='" + reultInspector + '\'' +
                '}';
    }
}
