package com.springboot.domain;

import java.io.Serializable;

public class BearingTest  implements Serializable {

    private Integer id ;
    private Integer  wheelId ;
    private String  envTemperatureLeft;
    private String  envTemperatureRight ;
    private String temperatureRiseLeft ;
    private String  temperatureRiseRight ;
    private String highestTempLeft ;
    private String  highestTempRight ;
    private String  worker ;
    private String  testerRight ;
    private String  isFinish;
    private String  finishTime;

    public BearingTest() {
    }

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

    public String getEnvTemperatureLeft() {
        return envTemperatureLeft;
    }

    public void setEnvTemperatureLeft(String envTemperatureLeft) {
        this.envTemperatureLeft = envTemperatureLeft;
    }

    public String getEnvTemperatureRight() {
        return envTemperatureRight;
    }

    public void setEnvTemperatureRight(String envTemperatureRight) {
        this.envTemperatureRight = envTemperatureRight;
    }

    public String getTemperatureRiseLeft() {
        return temperatureRiseLeft;
    }

    public void setTemperatureRiseLeft(String temperatureRiseLeft) {
        this.temperatureRiseLeft = temperatureRiseLeft;
    }

    public String getTemperatureRiseRight() {
        return temperatureRiseRight;
    }

    public void setTemperatureRiseRight(String temperatureRiseRight) {
        this.temperatureRiseRight = temperatureRiseRight;
    }

    public String getHighestTempLeft() {
        return highestTempLeft;
    }

    public void setHighestTempLeft(String highestTempLeft) {
        this.highestTempLeft = highestTempLeft;
    }

    public String getHighestTempRight() {
        return highestTempRight;
    }

    public void setHighestTempRight(String highestTempRight) {
        this.highestTempRight = highestTempRight;
    }

    public String getworker() {
        return worker;
    }

    public void setworker(String worker) {
        this.worker = worker;
    }

    public String getTesterRight() {
        return testerRight;
    }

    public void setTesterRight(String testerRight) {
        this.testerRight = testerRight;
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

    @Override
    public String toString() {
        return "BearingTest{" +
                "id=" + id +
                ", wheelId=" + wheelId +
                ", envTemperatureLeft='" + envTemperatureLeft + '\'' +
                ", envTemperatureRight='" + envTemperatureRight + '\'' +
                ", temperatureRiseLeft='" + temperatureRiseLeft + '\'' +
                ", temperatureRiseRight='" + temperatureRiseRight + '\'' +
                ", highestTempLeft='" + highestTempLeft + '\'' +
                ", highestTempRight='" + highestTempRight + '\'' +
                ", worker='" + worker + '\'' +
                ", testerRight='" + testerRight + '\'' +
                ", isFinish='" + isFinish + '\'' +
                ", finishTime='" + finishTime + '\'' +
                '}';
    }
}
