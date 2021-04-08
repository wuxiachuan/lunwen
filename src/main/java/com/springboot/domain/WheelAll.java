package com.springboot.domain;

import java.io.Serializable;
import java.util.List;

public class WheelAll  implements Serializable {
    private Integer wheelId;
    private WheelInfo wheelInfo;
    private WheelMeasure wheelMeasure;
    private WheelRound wheelRound;
    private AxleInspection axleInspection;
    private BearingCap bearingCap;
    private BearingTest bearingTest;
    private BearingLoad bearingLoad;
    private BearingRepair bearingRepair;
    private WheelDispatch wheelDispatch;
    private List<Problem> problem;
    private List<BearingUnLoad> bearingUnLoad;

    public WheelAll() {
    }

    public Integer getWheelId() {
        return wheelId;
    }

    public void setWheelId(Integer wheelId) {
        this.wheelId = wheelId;
    }

    public WheelInfo getWheelInfo() {
        return wheelInfo;
    }

    public void setWheelInfo(WheelInfo wheelInfo) {
        this.wheelInfo = wheelInfo;
    }

    public WheelMeasure getWheelMeasure() {
        return wheelMeasure;
    }

    public void setWheelMeasure(WheelMeasure wheelMeasure) {
        this.wheelMeasure = wheelMeasure;
    }

    public WheelRound getWheelRound() {
        return wheelRound;
    }

    public void setWheelRound(WheelRound wheelRound) {
        this.wheelRound = wheelRound;
    }

    public AxleInspection getAxleInspection() {
        return axleInspection;
    }

    public void setAxleInspection(AxleInspection axleInspection) {
        this.axleInspection = axleInspection;
    }

    public BearingCap getBearingCap() {
        return bearingCap;
    }

    public void setBearingCap(BearingCap bearingCap) {
        this.bearingCap = bearingCap;
    }

    public BearingTest getBearingTest() {
        return bearingTest;
    }

    public void setBearingTest(BearingTest bearingTest) {
        this.bearingTest = bearingTest;
    }

    public BearingLoad getBearingLoad() {
        return bearingLoad;
    }

    public void setBearingLoad(BearingLoad bearingLoad) {
        this.bearingLoad = bearingLoad;
    }

    public BearingRepair getBearingRepair() {
        return bearingRepair;
    }

    public void setBearingRepair(BearingRepair bearingRepair) {
        this.bearingRepair = bearingRepair;
    }

    public WheelDispatch getWheelDispatch() {
        return wheelDispatch;
    }

    public void setWheelDispatch(WheelDispatch wheelDispatch) {
        this.wheelDispatch = wheelDispatch;
    }

    public List<Problem> getProblem() {
        return problem;
    }

    public void setProblem(List<Problem> problem) {
        this.problem = problem;
    }

    public List<BearingUnLoad> getBearingUnLoad() {
        return bearingUnLoad;
    }

    public void setBearingUnLoad(List<BearingUnLoad> bearingUnLoad) {
        this.bearingUnLoad = bearingUnLoad;
    }
}
