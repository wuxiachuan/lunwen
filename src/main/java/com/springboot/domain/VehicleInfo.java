package com.springboot.domain;

import java.io.Serializable;
import java.util.List;

public class VehicleInfo implements Serializable {
    private Integer id;
    private String  vehicleNumber;
    private String  vehicleType;
    private String  RepairDate;
    private String  axleType;
    private String  preSessionRepair;
    private String  SessionRepair;
    private String  preOverhaul;
    private String  NextOverhaul;
    private String  axleIn1;
    private String  axleIn2;
    private String  axleIn3;
    private String  axleIn4;
    private String  axleOut1;
    private String  axleOut2;
    private String  axleOut3;
    private String  axleOut4;
    private WheelInfo in1;
    private WheelInfo in2;
    private WheelInfo in3;
    private WheelInfo in4;
    private WheelDispatch out1;
    private WheelDispatch out2;
    private WheelDispatch out3;
    private WheelDispatch out4;
    private String  isFinish;
    private String  finishTime;
    private String location;
    private String sequence;

    private List<WheelMeasure> axleIn;
    private List<WheelDispatch> axleOut;
    private String axleLife;
    private String bearingLife;
    private Integer low;
    private Integer high;
    private boolean isShow;
    private Integer offset;

    public VehicleInfo() {
        this.isShow = false;
        this.isFinish = "-1";
        this.offset = 0;
    }

    public VehicleInfo(String vehicleNumber,String  vehicleType,String  RepairDate,String  axleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.RepairDate = RepairDate;
        this.axleType = axleType;
        this.isShow = false;
        this.isFinish = "-1";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getRepairDate() {
        return RepairDate;
    }

    public void setRepairDate(String repairDate) {
        RepairDate = repairDate;
    }

    public String getAxleType() {
        return axleType;
    }

    public void setAxleType(String axleType) {
        this.axleType = axleType;
    }

    public String getPreSessionRepair() {
        return preSessionRepair;
    }

    public void setPreSessionRepair(String preSessionRepair) {
        this.preSessionRepair = preSessionRepair;
    }

    public String getSessionRepair() {
        return SessionRepair;
    }

    public void setSessionRepair(String sessionRepair) {
        SessionRepair = sessionRepair;
    }

    public String getPreOverhaul() {
        return preOverhaul;
    }

    public void setPreOverhaul(String preOverhaul) {
        this.preOverhaul = preOverhaul;
    }

    public String getNextOverhaul() {
        return NextOverhaul;
    }

    public void setNextOverhaul(String nextOverhaul) {
        NextOverhaul = nextOverhaul;
    }

    public String getAxleIn1() {
        return axleIn1;
    }

    public void setAxleIn1(String axleIn1) {
        this.axleIn1 = axleIn1;
    }

    public String getAxleIn2() {
        return axleIn2;
    }

    public void setAxleIn2(String axleIn2) {
        this.axleIn2 = axleIn2;
    }

    public String getAxleIn3() {
        return axleIn3;
    }

    public void setAxleIn3(String axleIn3) {
        this.axleIn3 = axleIn3;
    }

    public String getAxleIn4() {
        return axleIn4;
    }

    public void setAxleIn4(String axleIn4) {
        this.axleIn4 = axleIn4;
    }

    public String getAxleOut1() {
        return axleOut1;
    }

    public void setAxleOut1(String axleOut1) {
        this.axleOut1 = axleOut1;
    }

    public String getAxleOut2() {
        return axleOut2;
    }

    public void setAxleOut2(String axleOut2) {
        this.axleOut2 = axleOut2;
    }

    public String getAxleOut3() {
        return axleOut3;
    }

    public void setAxleOut3(String axleOut3) {
        this.axleOut3 = axleOut3;
    }

    public String getAxleOut4() {
        return axleOut4;
    }

    public void setAxleOut4(String axleOut4) {
        this.axleOut4 = axleOut4;
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

    public List<WheelMeasure> getAxleIn() {
        return axleIn;
    }

    public void setAxleIn(List<WheelMeasure> axleIn) {
        this.axleIn = axleIn;
    }

    public List<WheelDispatch> getAxleOut() {
        return axleOut;
    }

    public void setAxleOut(List<WheelDispatch> axleOut) {
        this.axleOut = axleOut;
    }

    public String getAxleLife() {
        return axleLife;
    }

    public void setAxleLife(String axleLife) {
        this.axleLife = axleLife;
    }

    public String getBearingLife() {
        return bearingLife;
    }

    public void setBearingLife(String bearingLife) {
        this.bearingLife = bearingLife;
    }

    public Integer getLow() {
        return low;
    }

    public void setLow(Integer low) {
        this.low = low;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public WheelInfo getIn1() {
        return in1;
    }

    public void setIn1(WheelInfo in1) {
        this.in1 = in1;
    }

    public WheelInfo getIn2() {
        return in2;
    }

    public void setIn2(WheelInfo in2) {
        this.in2 = in2;
    }

    public WheelInfo getIn3() {
        return in3;
    }

    public void setIn3(WheelInfo in3) {
        this.in3 = in3;
    }

    public WheelInfo getIn4() {
        return in4;
    }

    public void setIn4(WheelInfo in4) {
        this.in4 = in4;
    }

    public WheelDispatch getOut1() {
        return out1;
    }

    public void setOut1(WheelDispatch out1) {
        this.out1 = out1;
    }

    public WheelDispatch getOut2() {
        return out2;
    }

    public void setOut2(WheelDispatch out2) {
        this.out2 = out2;
    }

    public WheelDispatch getOut3() {
        return out3;
    }

    public void setOut3(WheelDispatch out3) {
        this.out3 = out3;
    }

    public WheelDispatch getOut4() {
        return out4;
    }

    public void setOut4(WheelDispatch out4) {
        this.out4 = out4;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "VehicleInfo{" +
                "id=" + id +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", RepairDate='" + RepairDate + '\'' +
                ", axleType='" + axleType + '\'' +
                ", preSessionRepair='" + preSessionRepair + '\'' +
                ", SessionRepair='" + SessionRepair + '\'' +
                ", preOverhaul='" + preOverhaul + '\'' +
                ", NextOverhaul='" + NextOverhaul + '\'' +
                ", axleIn1='" + axleIn1 + '\'' +
                ", axleIn2='" + axleIn2 + '\'' +
                ", axleIn3='" + axleIn3 + '\'' +
                ", axleIn4='" + axleIn4 + '\'' +
                ", axleOut1='" + axleOut1 + '\'' +
                ", axleOut2='" + axleOut2 + '\'' +
                ", axleOut3='" + axleOut3 + '\'' +
                ", axleOut4='" + axleOut4 + '\'' +
                ", in1=" + in1 +
                ", in2=" + in2 +
                ", in3=" + in3 +
                ", in4=" + in4 +
                ", out1=" + out1 +
                ", out2=" + out2 +
                ", out3=" + out3 +
                ", out4=" + out4 +
                ", isFinish='" + isFinish + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", location='" + location + '\'' +
                ", sequence='" + sequence + '\'' +
                '}';
    }
}
