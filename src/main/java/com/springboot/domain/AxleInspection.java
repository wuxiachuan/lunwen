package com.springboot.domain;

import java.io.Serializable;

public class AxleInspection implements Serializable {

    private Integer	id	;
    private Integer	wheelId	;
    private String	magAxleBody	;
    private String	magtAxleNeckLeft	;
    private String	magAxleNeckRight	;
    private String	ultAxleLeft	;
    private String	ultAxleRight	;
    private String	reultAxleLeft	;
    private String	reultAxleRight	;
    private String	ultAxleFootLeft	;
    private String	ultAxleFootRight	;
    private String	ultAxleNeckLeft	;
    private String	ultAxleNeckRight	;
    private String	reultAxleNeckLeft	;
    private String	reultAxleNeckRight	;
    private String	magInspector	;
    private String	magisFinish	;
    private String	magfinishTime	;
    private String	ultisFinish	;
    private String	ultfinishTime	;
    private String	worker	;
    private String	reultisFinish	;
    private String	reultfinishTime	;
    private String	reultInspector	;
    private String  repairProcess;
    private String  discardReason;

    public AxleInspection() {
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

    public String getMagAxleBody() {
        return magAxleBody;
    }

    public void setMagAxleBody(String magAxleBody) {
        this.magAxleBody = magAxleBody;
    }

    public String getMagtAxleNeckLeft() {
        return magtAxleNeckLeft;
    }

    public void setMagtAxleNeckLeft(String magtAxleNeckLeft) {
        this.magtAxleNeckLeft = magtAxleNeckLeft;
    }

    public String getMagAxleNeckRight() {
        return magAxleNeckRight;
    }

    public void setMagAxleNeckRight(String magAxleNeckRight) {
        this.magAxleNeckRight = magAxleNeckRight;
    }

    public String getUltAxleLeft() {
        return ultAxleLeft;
    }

    public void setUltAxleLeft(String ultAxleLeft) {
        this.ultAxleLeft = ultAxleLeft;
    }

    public String getUltAxleRight() {
        return ultAxleRight;
    }

    public void setUltAxleRight(String ultAxleRight) {
        this.ultAxleRight = ultAxleRight;
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

    public String getUltAxleFootLeft() {
        return ultAxleFootLeft;
    }

    public void setUltAxleFootLeft(String ultAxleFootLeft) {
        this.ultAxleFootLeft = ultAxleFootLeft;
    }

    public String getUltAxleFootRight() {
        return ultAxleFootRight;
    }

    public void setUltAxleFootRight(String ultAxleFootRight) {
        this.ultAxleFootRight = ultAxleFootRight;
    }

    public String getUltAxleNeckLeft() {
        return ultAxleNeckLeft;
    }

    public void setUltAxleNeckLeft(String ultAxleNeckLeft) {
        this.ultAxleNeckLeft = ultAxleNeckLeft;
    }

    public String getUltAxleNeckRight() {
        return ultAxleNeckRight;
    }

    public void setUltAxleNeckRight(String ultAxleNeckRight) {
        this.ultAxleNeckRight = ultAxleNeckRight;
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

    public String getMagInspector() {
        return magInspector;
    }

    public void setMagInspector(String magInspector) {
        this.magInspector = magInspector;
    }

    public String getMagisFinish() {
        return magisFinish;
    }

    public void setMagisFinish(String magisFinish) {
        this.magisFinish = magisFinish;
    }

    public String getMagfinishTime() {
        return magfinishTime;
    }

    public void setMagfinishTime(String magfinishTime) {
        this.magfinishTime = magfinishTime;
    }

    public String getUltisFinish() {
        return ultisFinish;
    }

    public void setUltisFinish(String ultisFinish) {
        this.ultisFinish = ultisFinish;
    }

    public String getUltfinishTime() {
        return ultfinishTime;
    }

    public void setUltfinishTime(String ultfinishTime) {
        this.ultfinishTime = ultfinishTime;
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

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getReultInspector() {
        return reultInspector;
    }

    public void setReultInspector(String reultInspector) {
        this.reultInspector = reultInspector;
    }

    public String getRepairProcess() {
        return repairProcess;
    }

    public void setRepairProcess(String repairProcess) {
        this.repairProcess = repairProcess;
    }

    public String getDiscardReason() {
        return discardReason;
    }

    public void setDiscardReason(String discardReason) {
        this.discardReason = discardReason;
    }

    @Override
    public String toString() {
        return "AxleInspection{" +
                "id=" + id +
                ", wheelId=" + wheelId +
                ", magAxleBody='" + magAxleBody + '\'' +
                ", magtAxleNeckLeft='" + magtAxleNeckLeft + '\'' +
                ", magAxleNeckRight='" + magAxleNeckRight + '\'' +
                ", ultAxleLeft='" + ultAxleLeft + '\'' +
                ", ultAxleRight='" + ultAxleRight + '\'' +
                ", reultAxleLeft='" + reultAxleLeft + '\'' +
                ", reultAxleRight='" + reultAxleRight + '\'' +
                ", ultAxleFootLeft='" + ultAxleFootLeft + '\'' +
                ", ultAxleFootRight='" + ultAxleFootRight + '\'' +
                ", ultAxleNeckLeft='" + ultAxleNeckLeft + '\'' +
                ", ultAxleNeckRight='" + ultAxleNeckRight + '\'' +
                ", reultAxleNeckLeft='" + reultAxleNeckLeft + '\'' +
                ", reultAxleNeckRight='" + reultAxleNeckRight + '\'' +
                ", magInspector='" + magInspector + '\'' +
                ", magisFinish='" + magisFinish + '\'' +
                ", magfinishTime='" + magfinishTime + '\'' +
                ", ultisFinish='" + ultisFinish + '\'' +
                ", ultfinishTime='" + ultfinishTime + '\'' +
                ", worker='" + worker + '\'' +
                ", reultisFinish='" + reultisFinish + '\'' +
                ", reultfinishTime='" + reultfinishTime + '\'' +
                ", reultInspector='" + reultInspector + '\'' +
                '}';
    }
}
