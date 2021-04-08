package com.springboot.domain;

import java.io.Serializable;

public class WheelMeasure implements Serializable {
    private Integer id;
    private Integer wheelId;
    private String  axleDiameter;
    private String  wheelDiameterLeft;
    private String  wheelDiameterRight;
    private String  treadWearLeft;
    private String  treadWearRight;
    private String  rimWideLeft;
    private String  rimWideRight;
    private String  flangeThickLeft;
    private String  flangeThickRight;
    private String  rimThickLeft;
    private String  rimThickRight;
    private String  internalDistance1;
    private String  internalDistance2;
    private String  internalDistance3;
    private String  problem;
    private String  repairProcess;
    private String  discardReason;
    private String  worker;
    private String  isFinish;
    private String  finishTime;

    public WheelMeasure() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        id = id;
    }

    public Integer getWheelId() {
        return wheelId;
    }

    public void setWheelId(Integer wheelId) {
        this.wheelId = wheelId;
    }

    public String getAxleDiameter() {
        return axleDiameter;
    }

    public void setAxleDiameter(String axleDiameter) {
        this.axleDiameter = axleDiameter;
    }

    public String getWheelDiameterLeft() {
        return wheelDiameterLeft;
    }

    public void setWheelDiameterLeft(String wheelDiameterLeft) {
        this.wheelDiameterLeft = wheelDiameterLeft;
    }

    public String getWheelDiameterRight() {
        return wheelDiameterRight;
    }

    public void setWheelDiameterRight(String wheelDiameterRight) {
        this.wheelDiameterRight = wheelDiameterRight;
    }

    public String getTreadWearLeft() {
        return treadWearLeft;
    }

    public void setTreadWearLeft(String treadWearLeft) {
        this.treadWearLeft = treadWearLeft;
    }

    public String getTreadWearRight() {
        return treadWearRight;
    }

    public void setTreadWearRight(String treadWearRight) {
        this.treadWearRight = treadWearRight;
    }

    public String getRimWideLeft() {
        return rimWideLeft;
    }

    public void setRimWideLeft(String rimWideLeft) {
        this.rimWideLeft = rimWideLeft;
    }

    public String getRimThickLeft() {
        return rimThickLeft;
    }

    public void setRimThickLeft(String rimThickLeft) {
        this.rimThickLeft = rimThickLeft;
    }

    public String getRimThickRight() {
        return rimThickRight;
    }

    public void setRimThickRight(String rimThickRight) {
        this.rimThickRight = rimThickRight;
    }

    public String getRimWideRight() {
        return rimWideRight;
    }

    public void setRimWideRight(String rimWideRight) {
        this.rimWideRight = rimWideRight;
    }

    public String getFlangeThickLeft() {
        return flangeThickLeft;
    }

    public void setFlangeThickLeft(String flangeThickLeft) {
        this.flangeThickLeft = flangeThickLeft;
    }

    public String getFlangeThickRight() {
        return flangeThickRight;
    }

    public void setFlangeThickRight(String flangeThickRight) {
        this.flangeThickRight = flangeThickRight;
    }

    public String getInternalDistance1() {
        return internalDistance1;
    }

    public void setInternalDistance1(String internalDistance1) {
        this.internalDistance1 = internalDistance1;
    }

    public String getInternalDistance2() {
        return internalDistance2;
    }

    public void setInternalDistance2(String internalDistance2) {
        this.internalDistance2 = internalDistance2;
    }

    public String getInternalDistance3() {
        return internalDistance3;
    }

    public void setInternalDistance3(String internalDistance3) {
        this.internalDistance3 = internalDistance3;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getRepairProcess() {
        return repairProcess;
    }

    public void setRepairProcess(String repairProcess) {
        this.repairProcess = repairProcess;
    }

    public String getworker() {
        return worker;
    }

    public void setworker(String worker) {
        this.worker = worker;
    }

    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getDiscardReason() {
        return discardReason;
    }

    public void setDiscardReason(String discardReason) {
        this.discardReason = discardReason;
    }

    @Override
    public String toString() {
        return "WheelMeasure{" +
                "Id=" + id +
                ", wheelId=" + wheelId +
                ", axleDiameter='" + axleDiameter + '\'' +
                ", wheelDiameterLeft='" + wheelDiameterLeft + '\'' +
                ", wheelDiameterRight='" + wheelDiameterRight + '\'' +
                ", treadWearLeft='" + treadWearLeft + '\'' +
                ", treadWearRight='" + treadWearRight + '\'' +
                ", rimWideLeft='" + rimWideLeft + '\'' +
                ", rimWideRight='" + rimWideRight + '\'' +
                ", flangeThickLeft='" + flangeThickLeft + '\'' +
                ", flangeThickRight='" + flangeThickRight + '\'' +
                ", internalDistance1='" + internalDistance1 + '\'' +
                ", internalDistance2='" + internalDistance2 + '\'' +
                ", internalDistance3='" + internalDistance3 + '\'' +
                ", problem='" + problem + '\'' +
                ", repairProcess='" + repairProcess + '\'' +
                ", worker='" + worker + '\'' +
                ", isFinish='" + isFinish + '\'' +
                ", finishTime='" + finishTime + '\'' +
                '}';
    }
}
