package com.springboot.domain;

import java.io.Serializable;

public class BearingUnCap implements Serializable {
    private Integer id;
    private Integer wheelId;
    private String  unCapReasonLeft;
    private String  unCapReasonRight;
    private String  unCapperLeft;
    private String  unCapperRight;
    private String  unCapDateLeft;
    private String  unCapDateRight;

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

    public String getUnCapReasonLeft() {
        return unCapReasonLeft;
    }

    public void setUnCapReasonLeft(String unCapReasonLeft) {
        this.unCapReasonLeft = unCapReasonLeft;
    }

    public String getUnCapReasonRight() {
        return unCapReasonRight;
    }

    public void setUnCapReasonRight(String unCapReasonRight) {
        this.unCapReasonRight = unCapReasonRight;
    }

    public String getUnCapperLeft() {
        return unCapperLeft;
    }

    public void setUnCapperLeft(String unCapperLeft) {
        this.unCapperLeft = unCapperLeft;
    }

    public String getUnCapperRight() {
        return unCapperRight;
    }

    public void setUnCapperRight(String unCapperRight) {
        this.unCapperRight = unCapperRight;
    }

    public String getUnCapDateLeft() {
        return unCapDateLeft;
    }

    public void setUnCapDateLeft(String unCapDateLeft) {
        this.unCapDateLeft = unCapDateLeft;
    }

    public String getUnCapDateRight() {
        return unCapDateRight;
    }

    public void setUnCapDateRight(String unCapDateRight) {
        this.unCapDateRight = unCapDateRight;
    }

    @Override
    public String toString() {
        return "BearingUnCap{" +
                "id=" + id +
                ", wheelId=" + wheelId +
                ", unCapReasonLeft='" + unCapReasonLeft + '\'' +
                ", unCapReasonRight='" + unCapReasonRight + '\'' +
                ", unCapperLeft='" + unCapperLeft + '\'' +
                ", unCapperRight='" + unCapperRight + '\'' +
                ", unCapDateLeft='" + unCapDateLeft + '\'' +
                ", unCapDateRight='" + unCapDateRight + '\'' +
                '}';
    }
}
