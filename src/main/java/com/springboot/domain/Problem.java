package com.springboot.domain;

import java.io.Serializable;

public class Problem implements Serializable {
        private  Integer id ;
        private  Integer wheelId ;
        private String processBelong ;
        private String problemDescription;
        private String problemDetails ;
        private String worker ;
        private String problemFinder ;
        private String findTime ;
        private String problemStatus;
        private String correctTime;
        private String confirm ;
        private String confirmTime ;
        private String axleNumber;
        private String axleType;
        private String axleMadeIn;


        public Problem() {
            this.problemStatus = "0";
            this.confirm = "0";
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

    public String getProcessBelong() {
        return processBelong;
    }

    public void setProcessBelong(String processBelong) {
        this.processBelong = processBelong;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getProblemDetails() {
        return problemDetails;
    }

    public void setProblemDetails(String problemDetails) {
        this.problemDetails = problemDetails;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getProblemFinder() {
        return problemFinder;
    }

    public void setProblemFinder(String problemFinder) {
        this.problemFinder = problemFinder;
    }

    public String getFindTime() {
        return findTime;
    }

    public void setFindTime(String findTime) {
        this.findTime = findTime;
    }

    public String getProblemStatus() {
        return problemStatus;
    }

    public void setProblemStatus(String problemStatus) {
        this.problemStatus = problemStatus;
    }

    public String getCorrectTime() {
        return correctTime;
    }

    public void setCorrectTime(String correctTime) {
        this.correctTime = correctTime;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getAxleNumber() {
        return axleNumber;
    }

    public void setAxleNumber(String axleNumber) {
        this.axleNumber = axleNumber;
    }

    public String getAxleType() {
        return axleType;
    }

    public void setAxleType(String axleType) {
        this.axleType = axleType;
    }

    public String getAxleMadeIn() {
        return axleMadeIn;
    }

    public void setAxleMadeIn(String axleMadeIn) {
        this.axleMadeIn = axleMadeIn;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", wheelId=" + wheelId +
                ", processBelong='" + processBelong + '\'' +
                ", problemDescription='" + problemDescription + '\'' +
                ", problemDetails='" + problemDetails + '\'' +
                ", worker='" + worker + '\'' +
                ", problemFinder='" + problemFinder + '\'' +
                ", findTime='" + findTime + '\'' +
                ", problemStatus='" + problemStatus + '\'' +
                ", correctTime='" + correctTime + '\'' +
                ", confirm='" + confirm + '\'' +
                ", confirmTime='" + confirmTime + '\'' +
                '}';
    }
}
