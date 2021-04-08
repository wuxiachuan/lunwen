package com.springboot.domain;

import java.io.Serializable;

public class BearingLoad implements Serializable {
    private  Integer id	;
    private  Integer	wheelId	;
    private  String	axleNeckDiameterLeftA1	;
    private  String	axleNeckDiameterLeftA2	;
    private  String	axleNeckDiameterLeftA3	;
    private  String	axleNeckDiameterLeftB1	;
    private  String	axleNeckDiameterLeftB2	;
    private  String	axleNeckDiameterLeftB3	;
    private  String	axleNeckDiameterRightA1	;
    private  String	axleNeckDiameterRightA2	;
    private  String	axleNeckDiameterRightA3	;
    private  String	axleNeckDiameterRightB1	;
    private  String	axleNeckDiameterRightB2	;
    private  String	axleNeckDiameterRightB3	;

    private  String  axleNeckRoundnessLeftA;
    private  String axleNeckRoundnessLeftB;

    private  String axleNeckRoundnessRightA;
    private  String  axleNeckRoundnessRightB	;

    private  String axleNeckDiameterLeftAvgA;
    private  String  axleNeckDiameterLeftAvgB;

    private  String axleNeckDiameterRightAvgA;
    private  String  axleNeckDiameterRightAvgB;

    private  String axleNeckDiameterLeftAvg;
    private  String  axleNeckDiameterRightAvg;

    private  String	neckFootDiameterLeft1	;
    private  String	neckFootDiameterLeft2	;
    private  String	neckFootDiameterRight1	;
    private  String	neckFootDiameterRight2	;
    private  String	neckFootRoundnessLeft	;
    private  String	neckFootRoundnessRight	;
    private  String	axleTypeLeft	;
    private  String	axleTypeRight	;
    private  String	bearingInnerDiameterLeft	;
    private  String	bearingInnerDiameterRight	;
    private  String	sealDiameterLeft	;
    private  String	sealDiameterRight	;
    private  String	backerInnerDiameterLeft	;
    private  String	backerInnerDiameterRight	;
    private  String	backerRoundnessLeft	;
    private  String	backerRoundnessRight	;
    private  String	fitBearing2NeckLeft	;
    private  String	fitSeal2NeckLeft	;
    private  String	fitSeal2BackerLeft	;
    private  String	fitBearing2NeckRight	;
    private  String	fitSeal2NeckRight	;
    private  String	fitSeal2BackerRight	;
    private  String	maxPressureLeft	;
    private  String	maxPressureRight	;
    private  String	finalPressureLeft	;
    private  String	finalPressureRight	;
    private  String	axleGapLeft	;
    private  String	axleGapRight	;
    private  String	bearingAssemble1stLeft	;
    private  String	bearingLevelLeft	;
    private  String	bearingmadeInLeft	;
    private  String	wheelAssemble1st	;
    private  String	axleNumber	;
    private  String	bearingAssembleLeft	;
    private  String	axleMadeDate	;
    private  String	axleMaterial	;
    private  String	axleMadeIn	;
    private  String	bearingAssembleInLeft	;
    private  String	repairLevelLeft	;
    private  String	bearingAssemble1stRight	;
    private  String	bearingLevelRight	;
    private  String	bearingmadeInRight	;
    private  String	wheelAssemblelast	;
    private  String	wheelAssembleIn	;
    private  String	bearingAssembleRight	;
    private  String	bearingAssembleInRight	;
    private  String	repairLevelRight	;
    private  String	sealMarkLeft	;
    private  String	sealMarkRight	;
    private  String	envTemperatureLeft	;
    private  String	envTemperatureRight	;
    private  String	temperatureRiseLeft	;
    private  String	temperatureRiseRight	;
    private  String	highestTempLeft	;
    private  String	highestTempRight	;
    private  String	matcherLeft	;
    private  String	worker	;
    private  String	caperLeft	;
    private  String	matcherRight	;
    private  String	loaderLeft	;
    private  String	loaderRight	;
    private  String	caperRight	;
    private  String	foreMan	;
    private  String	qualityInspector	;
    private  String	Inspector	;
    private  String	isFinish	;
    private  String	finishTime	;
    private  String repairProgress;
    private  String repairSaved;
    private  String neckFootDiameterLeftAvg ;
    private  String neckFootDiameterRightAvg ;
    private  String neckMeasureWorker;
    private  String isneckMeasureFinish;
    private  String neckMeasureFinishTime;
    private  String axleNeckRoundnessRight;
    private  String axleNeckRoundnessLeft;

    public BearingLoad() {
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

    public String getAxleNeckDiameterLeftA1() {
        return axleNeckDiameterLeftA1;
    }

    public void setAxleNeckDiameterLeftA1(String axleNeckDiameterLeftA1) {
        this.axleNeckDiameterLeftA1 = axleNeckDiameterLeftA1;
    }

    public String getAxleNeckDiameterLeftA2() {
        return axleNeckDiameterLeftA2;
    }

    public void setAxleNeckDiameterLeftA2(String axleNeckDiameterLeftA2) {
        this.axleNeckDiameterLeftA2 = axleNeckDiameterLeftA2;
    }

    public String getAxleNeckDiameterLeftA3() {
        return axleNeckDiameterLeftA3;
    }

    public void setAxleNeckDiameterLeftA3(String axleNeckDiameterLeftA3) {
        this.axleNeckDiameterLeftA3 = axleNeckDiameterLeftA3;
    }

    public String getAxleNeckDiameterLeftB1() {
        return axleNeckDiameterLeftB1;
    }

    public void setAxleNeckDiameterLeftB1(String axleNeckDiameterLeftB1) {
        this.axleNeckDiameterLeftB1 = axleNeckDiameterLeftB1;
    }

    public String getAxleNeckDiameterLeftB2() {
        return axleNeckDiameterLeftB2;
    }

    public void setAxleNeckDiameterLeftB2(String axleNeckDiameterLeftB2) {
        this.axleNeckDiameterLeftB2 = axleNeckDiameterLeftB2;
    }

    public String getAxleNeckDiameterLeftB3() {
        return axleNeckDiameterLeftB3;
    }

    public void setAxleNeckDiameterLeftB3(String axleNeckDiameterLeftB3) {
        this.axleNeckDiameterLeftB3 = axleNeckDiameterLeftB3;
    }

    public String getAxleNeckDiameterRightA1() {
        return axleNeckDiameterRightA1;
    }

    public void setAxleNeckDiameterRightA1(String axleNeckDiameterRightA1) {
        this.axleNeckDiameterRightA1 = axleNeckDiameterRightA1;
    }

    public String getAxleNeckDiameterRightA2() {
        return axleNeckDiameterRightA2;
    }

    public void setAxleNeckDiameterRightA2(String axleNeckDiameterRightA2) {
        this.axleNeckDiameterRightA2 = axleNeckDiameterRightA2;
    }

    public String getAxleNeckDiameterRightA3() {
        return axleNeckDiameterRightA3;
    }

    public void setAxleNeckDiameterRightA3(String axleNeckDiameterRightA3) {
        this.axleNeckDiameterRightA3 = axleNeckDiameterRightA3;
    }

    public String getAxleNeckDiameterRightB1() {
        return axleNeckDiameterRightB1;
    }

    public void setAxleNeckDiameterRightB1(String axleNeckDiameterRightB1) {
        this.axleNeckDiameterRightB1 = axleNeckDiameterRightB1;
    }

    public String getAxleNeckDiameterRightB2() {
        return axleNeckDiameterRightB2;
    }

    public void setAxleNeckDiameterRightB2(String axleNeckDiameterRightB2) {
        this.axleNeckDiameterRightB2 = axleNeckDiameterRightB2;
    }

    public String getAxleNeckDiameterRightB3() {
        return axleNeckDiameterRightB3;
    }

    public void setAxleNeckDiameterRightB3(String axleNeckDiameterRightB3) {
        this.axleNeckDiameterRightB3 = axleNeckDiameterRightB3;
    }

    public String getAxleNeckRoundnessLeftA() {
        return axleNeckRoundnessLeftA;
    }

    public void setAxleNeckRoundnessLeftA(String axleNeckRoundnessLeftA) {
        this.axleNeckRoundnessLeftA = axleNeckRoundnessLeftA;
    }

    public String getAxleNeckRoundnessLeftB() {
        return axleNeckRoundnessLeftB;
    }

    public void setAxleNeckRoundnessLeftB(String axleNeckRoundnessLeftB) {
        this.axleNeckRoundnessLeftB = axleNeckRoundnessLeftB;
    }

    public String getAxleNeckRoundnessRightA() {
        return axleNeckRoundnessRightA;
    }

    public void setAxleNeckRoundnessRightA(String axleNeckRoundnessRightA) {
        this.axleNeckRoundnessRightA = axleNeckRoundnessRightA;
    }

    public String getAxleNeckRoundnessRightB() {
        return axleNeckRoundnessRightB;
    }

    public void setAxleNeckRoundnessRightB(String axleNeckRoundnessRightB) {
        this.axleNeckRoundnessRightB = axleNeckRoundnessRightB;
    }

    public String getAxleNeckDiameterLeftAvgA() {
        return axleNeckDiameterLeftAvgA;
    }

    public void setAxleNeckDiameterLeftAvgA(String axleNeckDiameterLeftAvgA) {
        this.axleNeckDiameterLeftAvgA = axleNeckDiameterLeftAvgA;
    }

    public String getAxleNeckDiameterLeftAvgB() {
        return axleNeckDiameterLeftAvgB;
    }

    public void setAxleNeckDiameterLeftAvgB(String axleNeckDiameterLeftAvgB) {
        this.axleNeckDiameterLeftAvgB = axleNeckDiameterLeftAvgB;
    }

    public String getAxleNeckDiameterRightAvgA() {
        return axleNeckDiameterRightAvgA;
    }

    public void setAxleNeckDiameterRightAvgA(String axleNeckDiameterRightAvgA) {
        this.axleNeckDiameterRightAvgA = axleNeckDiameterRightAvgA;
    }

    public String getAxleNeckDiameterRightAvgB() {
        return axleNeckDiameterRightAvgB;
    }

    public void setAxleNeckDiameterRightAvgB(String axleNeckDiameterRightAvgB) {
        this.axleNeckDiameterRightAvgB = axleNeckDiameterRightAvgB;
    }

    public String getAxleNeckDiameterLeftAvg() {
        return axleNeckDiameterLeftAvg;
    }

    public void setAxleNeckDiameterLeftAvg(String axleNeckDiameterLeftAvg) {
        this.axleNeckDiameterLeftAvg = axleNeckDiameterLeftAvg;
    }

    public String getNeckMeasureWorker() {
        return neckMeasureWorker;
    }

    public void setNeckMeasureWorker(String neckMeasureWorker) {
        this.neckMeasureWorker = neckMeasureWorker;
    }

    public String getIsneckMeasureFinish() {
        return isneckMeasureFinish;
    }

    public void setIsneckMeasureFinish(String isneckMeasureFinish) {
        this.isneckMeasureFinish = isneckMeasureFinish;
    }

    public String getNeckMeasureFinishTime() {
        return neckMeasureFinishTime;
    }

    public void setNeckMeasureFinishTime(String neckMeasureFinishTime) {
        this.neckMeasureFinishTime = neckMeasureFinishTime;
    }

    public String getAxleNeckDiameterRightAvg() {
        return axleNeckDiameterRightAvg;
    }

    public void setAxleNeckDiameterRightAvg(String axleNeckDiameterRightAvg) {
        this.axleNeckDiameterRightAvg = axleNeckDiameterRightAvg;
    }

    public String getNeckFootDiameterLeft1() {
        return neckFootDiameterLeft1;
    }

    public void setNeckFootDiameterLeft1(String neckFootDiameterLeft1) {
        this.neckFootDiameterLeft1 = neckFootDiameterLeft1;
    }

    public String getNeckFootDiameterLeft2() {
        return neckFootDiameterLeft2;
    }

    public void setNeckFootDiameterLeft2(String neckFootDiameterLeft2) {
        this.neckFootDiameterLeft2 = neckFootDiameterLeft2;
    }

    public String getNeckFootDiameterRight1() {
        return neckFootDiameterRight1;
    }

    public void setNeckFootDiameterRight1(String neckFootDiameterRight1) {
        this.neckFootDiameterRight1 = neckFootDiameterRight1;
    }

    public String getNeckFootDiameterRight2() {
        return neckFootDiameterRight2;
    }

    public void setNeckFootDiameterRight2(String neckFootDiameterRight2) {
        this.neckFootDiameterRight2 = neckFootDiameterRight2;
    }

    public String getNeckFootRoundnessLeft() {
        return neckFootRoundnessLeft;
    }

    public void setNeckFootRoundnessLeft(String neckFootRoundnessLeft) {
        this.neckFootRoundnessLeft = neckFootRoundnessLeft;
    }

    public String getNeckFootRoundnessRight() {
        return neckFootRoundnessRight;
    }

    public void setNeckFootRoundnessRight(String neckFootRoundnessRight) {
        this.neckFootRoundnessRight = neckFootRoundnessRight;
    }

    public String getAxleTypeLeft() {
        return axleTypeLeft;
    }

    public void setAxleTypeLeft(String axleTypeLeft) {
        this.axleTypeLeft = axleTypeLeft;
    }

    public String getAxleTypeRight() {
        return axleTypeRight;
    }

    public void setAxleTypeRight(String axleTypeRight) {
        this.axleTypeRight = axleTypeRight;
    }

    public String getBearingInnerDiameterLeft() {
        return bearingInnerDiameterLeft;
    }

    public void setBearingInnerDiameterLeft(String bearingInnerDiameterLeft) {
        this.bearingInnerDiameterLeft = bearingInnerDiameterLeft;
    }

    public String getBearingInnerDiameterRight() {
        return bearingInnerDiameterRight;
    }

    public void setBearingInnerDiameterRight(String bearingInnerDiameterRight) {
        this.bearingInnerDiameterRight = bearingInnerDiameterRight;
    }

    public String getSealDiameterLeft() {
        return sealDiameterLeft;
    }

    public void setSealDiameterLeft(String sealDiameterLeft) {
        this.sealDiameterLeft = sealDiameterLeft;
    }

    public String getSealDiameterRight() {
        return sealDiameterRight;
    }

    public void setSealDiameterRight(String sealDiameterRight) {
        this.sealDiameterRight = sealDiameterRight;
    }

    public String getBackerInnerDiameterLeft() {
        return backerInnerDiameterLeft;
    }

    public void setBackerInnerDiameterLeft(String backerInnerDiameterLeft) {
        this.backerInnerDiameterLeft = backerInnerDiameterLeft;
    }

    public String getBackerInnerDiameterRight() {
        return backerInnerDiameterRight;
    }

    public void setBackerInnerDiameterRight(String backerInnerDiameterRight) {
        this.backerInnerDiameterRight = backerInnerDiameterRight;
    }

    public String getBackerRoundnessLeft() {
        return backerRoundnessLeft;
    }

    public void setBackerRoundnessLeft(String backerRoundnessLeft) {
        this.backerRoundnessLeft = backerRoundnessLeft;
    }

    public String getBackerRoundnessRight() {
        return backerRoundnessRight;
    }

    public void setBackerRoundnessRight(String backerRoundnessRight) {
        this.backerRoundnessRight = backerRoundnessRight;
    }

    public String getFitBearing2NeckLeft() {
        return fitBearing2NeckLeft;
    }

    public void setFitBearing2NeckLeft(String fitBearing2NeckLeft) {
        this.fitBearing2NeckLeft = fitBearing2NeckLeft;
    }

    public String getFitSeal2NeckLeft() {
        return fitSeal2NeckLeft;
    }

    public void setFitSeal2NeckLeft(String fitSeal2NeckLeft) {
        this.fitSeal2NeckLeft = fitSeal2NeckLeft;
    }

    public String getFitSeal2BackerLeft() {
        return fitSeal2BackerLeft;
    }

    public void setFitSeal2BackerLeft(String fitSeal2BackerLeft) {
        this.fitSeal2BackerLeft = fitSeal2BackerLeft;
    }

    public String getFitBearing2NeckRight() {
        return fitBearing2NeckRight;
    }

    public void setFitBearing2NeckRight(String fitBearing2NeckRight) {
        this.fitBearing2NeckRight = fitBearing2NeckRight;
    }

    public String getFitSeal2NeckRight() {
        return fitSeal2NeckRight;
    }

    public void setFitSeal2NeckRight(String fitSeal2NeckRight) {
        this.fitSeal2NeckRight = fitSeal2NeckRight;
    }

    public String getFitSeal2BackerRight() {
        return fitSeal2BackerRight;
    }

    public void setFitSeal2BackerRight(String fitSeal2BackerRight) {
        this.fitSeal2BackerRight = fitSeal2BackerRight;
    }

    public String getMaxPressureLeft() {
        return maxPressureLeft;
    }

    public void setMaxPressureLeft(String maxPressureLeft) {
        this.maxPressureLeft = maxPressureLeft;
    }

    public String getMaxPressureRight() {
        return maxPressureRight;
    }

    public void setMaxPressureRight(String maxPressureRight) {
        this.maxPressureRight = maxPressureRight;
    }

    public String getFinalPressureLeft() {
        return finalPressureLeft;
    }

    public void setFinalPressureLeft(String finalPressureLeft) {
        this.finalPressureLeft = finalPressureLeft;
    }

    public String getFinalPressureRight() {
        return finalPressureRight;
    }

    public void setFinalPressureRight(String finalPressureRight) {
        this.finalPressureRight = finalPressureRight;
    }

    public String getAxleGapLeft() {
        return axleGapLeft;
    }

    public void setAxleGapLeft(String axleGapLeft) {
        this.axleGapLeft = axleGapLeft;
    }

    public String getAxleGapRight() {
        return axleGapRight;
    }

    public void setAxleGapRight(String axleGapRight) {
        this.axleGapRight = axleGapRight;
    }

    public String getBearingAssemble1stLeft() {
        return bearingAssemble1stLeft;
    }

    public void setBearingAssemble1stLeft(String bearingAssemble1stLeft) {
        this.bearingAssemble1stLeft = bearingAssemble1stLeft;
    }

    public String getBearingLevelLeft() {
        return bearingLevelLeft;
    }

    public void setBearingLevelLeft(String bearingLevelLeft) {
        this.bearingLevelLeft = bearingLevelLeft;
    }

    public String getBearingmadeInLeft() {
        return bearingmadeInLeft;
    }

    public void setBearingmadeInLeft(String bearingmadeInLeft) {
        this.bearingmadeInLeft = bearingmadeInLeft;
    }

    public String getWheelAssemble1st() {
        return wheelAssemble1st;
    }

    public void setWheelAssemble1st(String wheelAssemble1st) {
        this.wheelAssemble1st = wheelAssemble1st;
    }

    public String getAxleNumber() {
        return axleNumber;
    }

    public void setAxleNumber(String axleNumber) {
        this.axleNumber = axleNumber;
    }

    public String getBearingAssembleLeft() {
        return bearingAssembleLeft;
    }

    public void setBearingAssembleLeft(String bearingAssembleLeft) {
        this.bearingAssembleLeft = bearingAssembleLeft;
    }

    public String getAxleMadeDate() {
        return axleMadeDate;
    }

    public void setAxleMadeDate(String axleMadeDate) {
        this.axleMadeDate = axleMadeDate;
    }

    public String getAxleMaterial() {
        return axleMaterial;
    }

    public void setAxleMaterial(String axleMaterial) {
        this.axleMaterial = axleMaterial;
    }

    public String getAxleMadeIn() {
        return axleMadeIn;
    }

    public void setAxleMadeIn(String axleMadeIn) {
        this.axleMadeIn = axleMadeIn;
    }

    public String getBearingAssembleInLeft() {
        return bearingAssembleInLeft;
    }

    public void setBearingAssembleInLeft(String bearingAssembleInLeft) {
        this.bearingAssembleInLeft = bearingAssembleInLeft;
    }

    public String getRepairLevelLeft() {
        return repairLevelLeft;
    }

    public void setRepairLevelLeft(String repairLevelLeft) {
        this.repairLevelLeft = repairLevelLeft;
    }

    public String getBearingAssemble1stRight() {
        return bearingAssemble1stRight;
    }

    public void setBearingAssemble1stRight(String bearingAssemble1stRight) {
        this.bearingAssemble1stRight = bearingAssemble1stRight;
    }

    public String getBearingLevelRight() {
        return bearingLevelRight;
    }

    public void setBearingLevelRight(String bearingLevelRight) {
        this.bearingLevelRight = bearingLevelRight;
    }

    public String getBearingmadeInRight() {
        return bearingmadeInRight;
    }

    public void setBearingmadeInRight(String bearingmadeInRight) {
        this.bearingmadeInRight = bearingmadeInRight;
    }

    public String getWheelAssemblelast() {
        return wheelAssemblelast;
    }

    public void setWheelAssemblelast(String wheelAssemblelast) {
        this.wheelAssemblelast = wheelAssemblelast;
    }

    public String getWheelAssembleIn() {
        return wheelAssembleIn;
    }

    public void setWheelAssembleIn(String wheelAssembleIn) {
        this.wheelAssembleIn = wheelAssembleIn;
    }

    public String getBearingAssembleRight() {
        return bearingAssembleRight;
    }

    public void setBearingAssembleRight(String bearingAssembleRight) {
        this.bearingAssembleRight = bearingAssembleRight;
    }

    public String getBearingAssembleInRight() {
        return bearingAssembleInRight;
    }

    public void setBearingAssembleInRight(String bearingAssembleInRight) {
        this.bearingAssembleInRight = bearingAssembleInRight;
    }

    public String getRepairLevelRight() {
        return repairLevelRight;
    }

    public void setRepairLevelRight(String repairLevelRight) {
        this.repairLevelRight = repairLevelRight;
    }

    public String getSealMarkLeft() {
        return sealMarkLeft;
    }

    public void setSealMarkLeft(String sealMarkLeft) {
        this.sealMarkLeft = sealMarkLeft;
    }

    public String getSealMarkRight() {
        return sealMarkRight;
    }

    public void setSealMarkRight(String sealMarkRight) {
        this.sealMarkRight = sealMarkRight;
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

    public String getMatcherLeft() {
        return matcherLeft;
    }

    public void setMatcherLeft(String matcherLeft) {
        this.matcherLeft = matcherLeft;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getCaperLeft() {
        return caperLeft;
    }

    public void setCaperLeft(String caperLeft) {
        this.caperLeft = caperLeft;
    }

    public String getMatcherRight() {
        return matcherRight;
    }

    public void setMatcherRight(String matcherRight) {
        this.matcherRight = matcherRight;
    }

    public String getLoaderLeft() {
        return loaderLeft;
    }

    public void setLoaderLeft(String loaderLeft) {
        this.loaderLeft = loaderLeft;
    }

    public String getLoaderRight() {
        return loaderRight;
    }

    public void setLoaderRight(String loaderRight) {
        this.loaderRight = loaderRight;
    }

    public String getCaperRight() {
        return caperRight;
    }

    public void setCaperRight(String caperRight) {
        this.caperRight = caperRight;
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

    public String getRepairProgress() {
        return repairProgress;
    }

    public void setRepairProgress(String repairProgress) {
        this.repairProgress = repairProgress;
    }

    public String getRepairSaved() {
        return repairSaved;
    }

    public void setRepairSaved(String repairSaved) {
        this.repairSaved = repairSaved;
    }

    public String getNeckFootDiameterLeftAvg() {
        return neckFootDiameterLeftAvg;
    }

    public void setNeckFootDiameterLeftAvg(String neckFootDiameterLeftAvg) {
        this.neckFootDiameterLeftAvg = neckFootDiameterLeftAvg;
    }

    public String getNeckFootDiameterRightAvg() {
        return neckFootDiameterRightAvg;
    }

    public void setNeckFootDiameterRightAvg(String neckFootDiameterRightAvg) {
        this.neckFootDiameterRightAvg = neckFootDiameterRightAvg;
    }

    public String getAxleNeckRoundnessRight() {
        return axleNeckRoundnessRight;
    }

    public void setAxleNeckRoundnessRight(String axleNeckRoundnessRight) {
        this.axleNeckRoundnessRight = axleNeckRoundnessRight;
    }

    public String getAxleNeckRoundnessLeft() {
        return axleNeckRoundnessLeft;
    }

    public void setAxleNeckRoundnessLeft(String axleNeckRoundnessLeft) {
        this.axleNeckRoundnessLeft = axleNeckRoundnessLeft;
    }

    @Override
    public String toString() {
        return "BearingLoad{" +
                "id=" + id +
                ", wheelId=" + wheelId +
                ", axleNeckDiameterLeftA1='" + axleNeckDiameterLeftA1 + '\'' +
                ", axleNeckDiameterLeftA2='" + axleNeckDiameterLeftA2 + '\'' +
                ", axleNeckDiameterLeftA3='" + axleNeckDiameterLeftA3 + '\'' +
                ", axleNeckDiameterLeftB1='" + axleNeckDiameterLeftB1 + '\'' +
                ", axleNeckDiameterLeftB2='" + axleNeckDiameterLeftB2 + '\'' +
                ", axleNeckDiameterLeftB3='" + axleNeckDiameterLeftB3 + '\'' +
                ", axleNeckDiameterRightA1='" + axleNeckDiameterRightA1 + '\'' +
                ", axleNeckDiameterRightA2='" + axleNeckDiameterRightA2 + '\'' +
                ", axleNeckDiameterRightA3='" + axleNeckDiameterRightA3 + '\'' +
                ", axleNeckDiameterRightB1='" + axleNeckDiameterRightB1 + '\'' +
                ", axleNeckDiameterRightB2='" + axleNeckDiameterRightB2 + '\'' +
                ", axleNeckDiameterRightB3='" + axleNeckDiameterRightB3 + '\'' +
                ", axleNeckRoundnessLeftA='" + axleNeckRoundnessLeftA + '\'' +
                ", axleNeckRoundnessLeftB='" + axleNeckRoundnessLeftB + '\'' +
                ", axleNeckRoundnessRightA='" + axleNeckRoundnessRightA + '\'' +
                ", axleNeckRoundnessRightB='" + axleNeckRoundnessRightB + '\'' +
                ", axleNeckDiameterLeftAvgA='" + axleNeckDiameterLeftAvgA + '\'' +
                ", axleNeckDiameterLeftAvgB='" + axleNeckDiameterLeftAvgB + '\'' +
                ", axleNeckDiameterRightAvgA='" + axleNeckDiameterRightAvgA + '\'' +
                ", axleNeckDiameterRightAvgB='" + axleNeckDiameterRightAvgB + '\'' +
                ", axleNeckDiameterLeftAvg='" + axleNeckDiameterLeftAvg + '\'' +
                ", axleNeckDiameterRightAvg='" + axleNeckDiameterRightAvg + '\'' +
                ", neckFootDiameterLeft1='" + neckFootDiameterLeft1 + '\'' +
                ", neckFootDiameterLeft2='" + neckFootDiameterLeft2 + '\'' +
                ", neckFootDiameterRight1='" + neckFootDiameterRight1 + '\'' +
                ", neckFootDiameterRight2='" + neckFootDiameterRight2 + '\'' +
                ", neckFootRoundnessLeft='" + neckFootRoundnessLeft + '\'' +
                ", neckFootRoundnessRight='" + neckFootRoundnessRight + '\'' +
                ", axleTypeLeft='" + axleTypeLeft + '\'' +
                ", axleTypeRight='" + axleTypeRight + '\'' +
                ", bearingInnerDiameterLeft='" + bearingInnerDiameterLeft + '\'' +
                ", bearingInnerDiameterRight='" + bearingInnerDiameterRight + '\'' +
                ", sealDiameterLeft='" + sealDiameterLeft + '\'' +
                ", sealDiameterRight='" + sealDiameterRight + '\'' +
                ", backerInnerDiameterLeft='" + backerInnerDiameterLeft + '\'' +
                ", backerInnerDiameterRight='" + backerInnerDiameterRight + '\'' +
                ", backerRoundnessLeft='" + backerRoundnessLeft + '\'' +
                ", backerRoundnessRight='" + backerRoundnessRight + '\'' +
                ", fitBearing2NeckLeft='" + fitBearing2NeckLeft + '\'' +
                ", fitSeal2NeckLeft='" + fitSeal2NeckLeft + '\'' +
                ", fitSeal2BackerLeft='" + fitSeal2BackerLeft + '\'' +
                ", fitBearing2NeckRight='" + fitBearing2NeckRight + '\'' +
                ", fitSeal2NeckRight='" + fitSeal2NeckRight + '\'' +
                ", fitSeal2BackerRight='" + fitSeal2BackerRight + '\'' +
                ", maxPressureLeft='" + maxPressureLeft + '\'' +
                ", maxPressureRight='" + maxPressureRight + '\'' +
                ", finalPressureLeft='" + finalPressureLeft + '\'' +
                ", finalPressureRight='" + finalPressureRight + '\'' +
                ", axleGapLeft='" + axleGapLeft + '\'' +
                ", axleGapRight='" + axleGapRight + '\'' +
                ", bearingAssemble1stLeft='" + bearingAssemble1stLeft + '\'' +
                ", bearingLevelLeft='" + bearingLevelLeft + '\'' +
                ", bearingmadeInLeft='" + bearingmadeInLeft + '\'' +
                ", wheelAssemble1st='" + wheelAssemble1st + '\'' +
                ", axleNumber='" + axleNumber + '\'' +
                ", bearingAssembleLeft='" + bearingAssembleLeft + '\'' +
                ", axleMadeDate='" + axleMadeDate + '\'' +
                ", axleMaterial='" + axleMaterial + '\'' +
                ", axleMadeIn='" + axleMadeIn + '\'' +
                ", bearingAssembleInLeft='" + bearingAssembleInLeft + '\'' +
                ", repairLevelLeft='" + repairLevelLeft + '\'' +
                ", bearingAssemble1stRight='" + bearingAssemble1stRight + '\'' +
                ", bearingLevelRight='" + bearingLevelRight + '\'' +
                ", bearingmadeInRight='" + bearingmadeInRight + '\'' +
                ", wheelAssemblelast='" + wheelAssemblelast + '\'' +
                ", wheelAssembleIn='" + wheelAssembleIn + '\'' +
                ", bearingAssembleRight='" + bearingAssembleRight + '\'' +
                ", bearingAssembleInRight='" + bearingAssembleInRight + '\'' +
                ", repairLevelRight='" + repairLevelRight + '\'' +
                ", sealMarkLeft='" + sealMarkLeft + '\'' +
                ", sealMarkRight='" + sealMarkRight + '\'' +
                ", envTemperatureLeft='" + envTemperatureLeft + '\'' +
                ", envTemperatureRight='" + envTemperatureRight + '\'' +
                ", temperatureRiseLeft='" + temperatureRiseLeft + '\'' +
                ", temperatureRiseRight='" + temperatureRiseRight + '\'' +
                ", highestTempLeft='" + highestTempLeft + '\'' +
                ", highestTempRight='" + highestTempRight + '\'' +
                ", matcherLeft='" + matcherLeft + '\'' +
                ", worker='" + worker + '\'' +
                ", caperLeft='" + caperLeft + '\'' +
                ", matcherRight='" + matcherRight + '\'' +
                ", loaderLeft='" + loaderLeft + '\'' +
                ", loaderRight='" + loaderRight + '\'' +
                ", caperRight='" + caperRight + '\'' +
                ", foreMan='" + foreMan + '\'' +
                ", qualityInspector='" + qualityInspector + '\'' +
                ", Inspector='" + Inspector + '\'' +
                ", isFinish='" + isFinish + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", repairProgress='" + repairProgress + '\'' +
                ", repairSaved='" + repairSaved + '\'' +
                ", neckFootDiameterLeftAvg='" + neckFootDiameterLeftAvg + '\'' +
                ", neckFootDiameterRightAvg='" + neckFootDiameterRightAvg + '\'' +
                ", neckMeasureWorker='" + neckMeasureWorker + '\'' +
                ", isneckMeasureFinish='" + isneckMeasureFinish + '\'' +
                ", neckMeasureFinishTime='" + neckMeasureFinishTime + '\'' +
                '}';
    }
}
