package com.springboot.domain;

import java.io.Serializable;

public class WheelDispatch implements Serializable {
    private Integer  	id	;
    private Integer  	wheelId	;
    private String  	axleDiameter	;
    private String  	wheelDiameterLeft	;
    private String  	wheelDiameterRight	;
    private String  	treadWearLeft	;
    private String  	treadWearRight	;
    private String  	rimWideLeft	;
    private String  	rimWideRight	;
    private String  	flangeThickLeft	;
    private String  	flangeThickRight	;
    private String  	rimThickLeft	;
    private String  	rimThickRight	;
    private String  	internalDistance1	;
    private String  	internalDistance2	;
    private String  	internalDistance3	;
    private String  	axleNumber	;
    private String  	axleType	;
    private String  	axleMaterial	;
    private String  	axleMadeDate	;
    private String  	axleMadeIn	;
    private String  	wheelType	;
    private String  	wheelMaterial	;
    private String  	wheelMadeIn	;
    private String  	wheelAssemblefirst	;
    private String  	wheelAssemblefirstIn	;
    private String  	wheelAssemblelast	;
    private String  	wheelAssemblelastIn	;
    private String      bearingTypeLeft;
    private String      bearingAssembleDateLeft;
    private String      bearingMadeInLeft;
    private String      bearingTypeRight;
    private String      bearingAssembleDateRight;
    private String      bearingMadeInRight;
    private String  	isFinish	;
    private String  	finishTime	;
    private String  	worker	;

    private String      storePositionX;
    private String      storePositionY;
    private String      wheelStatus;
    private String      vehicleType;
    private String      vehicleNumber;
    private String      matcher;
    private String      foreMan;
    private String      qualityInspector;
    private String      Inspector;
    private String      dispatchFinishTime;
    private String      qualityInspectFinishTime;
    private String      axleDispatchPosition;

    public WheelDispatch() {
        wheelStatus = "0";
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

    public String getAxleMaterial() {
        return axleMaterial;
    }

    public void setAxleMaterial(String axleMaterial) {
        this.axleMaterial = axleMaterial;
    }

    public String getAxleMadeDate() {
        return axleMadeDate;
    }

    public void setAxleMadeDate(String axleMadeDate) {
        this.axleMadeDate = axleMadeDate;
    }

    public String getAxleMadeIn() {
        return axleMadeIn;
    }

    public void setAxleMadeIn(String axleMadeIn) {
        this.axleMadeIn = axleMadeIn;
    }

    public String getWheelType() {
        return wheelType;
    }

    public void setWheelType(String wheelType) {
        this.wheelType = wheelType;
    }

    public String getWheelMaterial() {
        return wheelMaterial;
    }

    public void setWheelMaterial(String wheelMaterial) {
        this.wheelMaterial = wheelMaterial;
    }

    public String getWheelMadeIn() {
        return wheelMadeIn;
    }

    public void setWheelMadeIn(String wheelMadeIn) {
        this.wheelMadeIn = wheelMadeIn;
    }

    public String getWheelAssemblefirst() {
        return wheelAssemblefirst;
    }

    public void setWheelAssemblefirst(String wheelAssemblefirst) {
        this.wheelAssemblefirst = wheelAssemblefirst;
    }

    public String getWheelAssemblefirstIn() {
        return wheelAssemblefirstIn;
    }

    public void setWheelAssemblefirstIn(String wheelAssemblefirstIn) {
        this.wheelAssemblefirstIn = wheelAssemblefirstIn;
    }

    public String getWheelAssemblelast() {
        return wheelAssemblelast;
    }

    public void setWheelAssemblelast(String wheelAssemblelast) {
        this.wheelAssemblelast = wheelAssemblelast;
    }

    public String getWheelAssemblelastIn() {
        return wheelAssemblelastIn;
    }

    public void setWheelAssemblelastIn(String wheelAssemblelastIn) {
        this.wheelAssemblelastIn = wheelAssemblelastIn;
    }

    public String getBearingTypeLeft() {
        return bearingTypeLeft;
    }

    public void setBearingTypeLeft(String bearingTypeLeft) {
        this.bearingTypeLeft = bearingTypeLeft;
    }

    public String getBearingAssembleDateLeft() {
        return bearingAssembleDateLeft;
    }

    public void setBearingAssembleDateLeft(String bearingAssembleDateLeft) {
        this.bearingAssembleDateLeft = bearingAssembleDateLeft;
    }

    public String getBearingMadeInLeft() {
        return bearingMadeInLeft;
    }

    public void setBearingMadeInLeft(String bearingMadeInLeft) {
        this.bearingMadeInLeft = bearingMadeInLeft;
    }

    public String getBearingTypeRight() {
        return bearingTypeRight;
    }

    public void setBearingTypeRight(String bearingTypeRight) {
        this.bearingTypeRight = bearingTypeRight;
    }

    public String getBearingAssembleDateRight() {
        return bearingAssembleDateRight;
    }

    public void setBearingAssembleDateRight(String bearingAssembleDateRight) {
        this.bearingAssembleDateRight = bearingAssembleDateRight;
    }

    public String getBearingMadeInRight() {
        return bearingMadeInRight;
    }

    public void setBearingMadeInRight(String bearingMadeInRight) {
        this.bearingMadeInRight = bearingMadeInRight;
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

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getStorePositionX() {
        return storePositionX;
    }

    public void setStorePositionX(String storePositionX) {
        this.storePositionX = storePositionX;
    }

    public String getStorePositionY() {
        return storePositionY;
    }

    public void setStorePositionY(String storePositionY) {
        this.storePositionY = storePositionY;
    }

    public String getWheelStatus() {
        return wheelStatus;
    }

    public void setWheelStatus(String wheelStatus) {
        this.wheelStatus = wheelStatus;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getMatcher() {
        return matcher;
    }

    public void setMatcher(String matcher) {
        this.matcher = matcher;
    }

    public String getForeMan() {
        return foreMan;
    }

    public void setForeMan(String foreMan) {
        this.foreMan = foreMan;
    }

    public String getQualityInspector() {
        return qualityInspector;
    }

    public void setQualityInspector(String qualityInspector) {
        this.qualityInspector = qualityInspector;
    }

    public String getInspector() {
        return Inspector;
    }

    public void setInspector(String inspector) {
        Inspector = inspector;
    }

    public String getDispatchFinishTime() {
        return dispatchFinishTime;
    }

    public void setDispatchFinishTime(String dispatchFinishTime) {
        this.dispatchFinishTime = dispatchFinishTime;
    }

    public String getQualityInspectFinishTime() {
        return qualityInspectFinishTime;
    }

    public void setQualityInspectFinishTime(String qualityInspectFinishTime) {
        this.qualityInspectFinishTime = qualityInspectFinishTime;
    }

    public String getAxleDispatchPosition() {
        return axleDispatchPosition;
    }

    public void setAxleDispatchPosition(String axleDispatchPosition) {
        this.axleDispatchPosition = axleDispatchPosition;
    }
}
