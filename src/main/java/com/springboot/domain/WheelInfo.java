package com.springboot.domain;

import java.io.Serializable;

public class WheelInfo implements Serializable {
    private Integer wheelId;
    private String  takeInDate;
    private String  takeInDepot;
    private String  takeInReason;
    private String  vehicleType;
    private String  vehicleNumber;
    private String  axleNumber;
    private String  axleType;
    private String  axleMaterial;
    private String  axleMadeDate;
    private String  axleMadeIn;
    private String  axlePosition;
    private String  wheelType;
    private String  wheelMaterial;
    private String  wheelMadeIn;
    private String  wheelAssemblefirstDate;
    private String  wheelAssemblefirstIn;
    private String  wheelAssemblelastDate;
    private String  wheelAssemblelastIn;
    private String  wheelMarkLeft;
    private String  wheelMarkRight;
    private String  worker;
    private String  infoTakeFinish;
    private String  infoTakeFinishTime;
    private String  isMeasureFinish;
    private String  isbearingRepairFinish;
    private String  isaxleInspectionFinish;
    private String  isWheelRoundingFinish;
    private String  isbearingLoadFinish;

    private String  ismagnetInspectionFinish;
    private String  isbearingCapFinish;

    private String  isbearingrollTestFinish;
    private String  iswheelDispatchFinish;
    private String  isqualityInspectionFinish;
    private String  isverifyFinish;
    private String  isbearingCapFinishW;
    private String  isbearingCapFinishL;

    private String   isbearingUnCapFinish;
    private String   isbearingUnloadFinish;
    private String   iswheelPolishFinish;
    private String   isreInspectionFinish;
    private String   isbearingNeckFinish;

    private String   isprocessFinish;
    private String   state;
    private String   dispatchVehicleType;
    private String   dipatchVehicleNumber;
    private String   dispatchDate;
    private String   dipatchAxlePosition;

    private String   storePositionX;
    private String   storePositionY;

    private String   reserve1;
    private String   reserve2;
    private String   isprocessFinishTime;
    private String   isreMeasureFinish;
    private String   finishTime;
    private String   repairWay;
    private String   repairProcess;
    private String   discardReason;


    public WheelInfo() {
        init();
    }

    private void init(){
        //信息采集 0 未完成，1已完成
        infoTakeFinish = "0";
        //轮对测量 0 未完成，1已完成
        isMeasureFinish= "0";
        //轴承检查 0 未完成，1已完成
        isbearingRepairFinish= "0";
        //除锈 -1不需要，0未完成，1已完成
        iswheelPolishFinish= "-1";
        //超声波探伤 -1不需要，0未完成，1已完成
        isaxleInspectionFinish= "-1";
        //磁粉探伤 -1不需要，0未完成，1已完成
        ismagnetInspectionFinish = "0";
        //人工复探 -1不需要，0未完成，1已完成
        isreInspectionFinish= "-1";
        //车轮旋面 -1不需要，0未完成，1已完成
        isWheelRoundingFinish= "-1";
        //轴颈测量 -1不需要，1已完成，2左端压装，3右端压装，5两端压装
        isbearingNeckFinish= "-1";
        //轴承压装 -1不需要，1已完成，2左端压装，3右端压装，5两端压装
        isbearingLoadFinish= "-1";
        //关盖 -1不需要，0未完成，1已完成
        isbearingCapFinish= "-1";
        //旋面关盖 -1不需要，0未完成，1已完成
        isbearingCapFinishW= "-1";
        //压装关盖 -1不需要，0未完成，1已完成
        isbearingCapFinishL= "-1";
        //开盖 -1不需要，0未完成，1已完成
        isbearingUnCapFinish= "-1";
        //退卸 -1不需要，1已完成,2左端退卸，3右端退卸，5两端退卸
        isbearingUnloadFinish= "-1";
        //磨合 0 未完成，1已完成
        isbearingrollTestFinish= "0";
        //支出 0 未完成，1已完成
        isreMeasureFinish = "0";
        //支出 0 未完成，1已完成
        iswheelDispatchFinish= "0";
        //质检 0 未完成，1已完成
        isqualityInspectionFinish= "0";
        //验收 0 未完成，1已完成
        isverifyFinish= "0";
        //是否完工 0 未完成，1已完成
        isprocessFinish= "0";
        //状态 0检修中，1良好存放，2支出，3报废存放，4已送厂
        state= "0";
    }
    public Integer getWheelId() {
        return wheelId;
    }

    public void setWheelId(Integer wheelId) {
        this.wheelId = wheelId;
    }

    public String getTakeInDate() {
        return takeInDate;
    }

    public void setTakeInDate(String takeInDate) {
        this.takeInDate = takeInDate;
    }

    public String getTakeInDepot() {
        return takeInDepot;
    }

    public void setTakeInDepot(String takeInDepot) {
        this.takeInDepot = takeInDepot;
    }

    public String getTakeInReason() {
        return takeInReason;
    }

    public void setTakeInReason(String takeInReason) {
        this.takeInReason = takeInReason;
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

    public String getAxlePosition() {
        return axlePosition;
    }

    public void setAxlePosition(String axlePosition) {
        this.axlePosition = axlePosition;
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

    public String getWheelAssemblefirstDate() {
        return wheelAssemblefirstDate;
    }

    public void setWheelAssemblefirstDate(String wheelAssemblefirstDate) {
        this.wheelAssemblefirstDate = wheelAssemblefirstDate;
    }

    public String getWheelAssemblefirstIn() {
        return wheelAssemblefirstIn;
    }

    public void setWheelAssemblefirstIn(String wheelAssemblefirstIn) {
        this.wheelAssemblefirstIn = wheelAssemblefirstIn;
    }

    public String getWheelAssemblelastDate() {
        return wheelAssemblelastDate;
    }

    public void setWheelAssemblelastDate(String wheelAssemblelastDate) {
        this.wheelAssemblelastDate = wheelAssemblelastDate;
    }

    public String getWheelAssemblelastIn() {
        return wheelAssemblelastIn;
    }

    public void setWheelAssemblelastIn(String wheelAssemblelastIn) {
        this.wheelAssemblelastIn = wheelAssemblelastIn;
    }

    public String getWheelMarkLeft() {
        return wheelMarkLeft;
    }

    public void setWheelMarkLeft(String wheelMarkLeft) {
        this.wheelMarkLeft = wheelMarkLeft;
    }

    public String getWheelMarkRight() {
        return wheelMarkRight;
    }

    public void setWheelMarkRight(String wheelMarkRight) {
        this.wheelMarkRight = wheelMarkRight;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getInfoTakeFinish() {
        return infoTakeFinish;
    }

    public void setInfoTakeFinish(String infoTakeFinish) {
        this.infoTakeFinish = infoTakeFinish;
    }

    public String getInfoTakeFinishTime() {
        return infoTakeFinishTime;
    }

    public void setInfoTakeFinishTime(String infoTakeFinishTime) {
        this.infoTakeFinishTime = infoTakeFinishTime;
    }


    public String getIsMeasureFinish() {
        return isMeasureFinish;
    }

    public void setIsMeasureFinish(String isMeasureFinish) {
        this.isMeasureFinish = isMeasureFinish;
    }

    public String getIsbearingRepairFinish() {
        return isbearingRepairFinish;
    }

    public void setIsbearingRepairFinish(String isbearingRepairFinish) {
        this.isbearingRepairFinish = isbearingRepairFinish;
    }

    public String getIsaxleInspectionFinish() {
        return isaxleInspectionFinish;
    }

    public void setIsaxleInspectionFinish(String isaxleInspectionFinish) {
        this.isaxleInspectionFinish = isaxleInspectionFinish;
    }

    public String getIsWheelRoundingFinish() {
        return isWheelRoundingFinish;
    }

    public void setIsWheelRoundingFinish(String isWheelRoundingFinish) {
        this.isWheelRoundingFinish = isWheelRoundingFinish;
    }

    public String getIsbearingLoadFinish() {
        return isbearingLoadFinish;
    }

    public void setIsbearingLoadFinish(String isbearingLoadFinish) {
        this.isbearingLoadFinish = isbearingLoadFinish;
    }

    public String getIsbearingrollTestFinish() {
        return isbearingrollTestFinish;
    }

    public void setIsbearingrollTestFinish(String isbearingrollTestFinish) {
        this.isbearingrollTestFinish = isbearingrollTestFinish;
    }

    public String getIsbearingCapFinishW() {
        return isbearingCapFinishW;
    }

    public void setIsbearingCapFinishW(String isbearingCapFinishW) {
        this.isbearingCapFinishW = isbearingCapFinishW;
    }

    public String getIsbearingCapFinishL() {
        return isbearingCapFinishL;
    }

    public void setIsbearingCapFinishL(String isbearingCapFinishL) {
        this.isbearingCapFinishL = isbearingCapFinishL;
    }

    public String getIswheelDispatchFinish() {
        return iswheelDispatchFinish;
    }

    public void setIswheelDispatchFinish(String iswheelDispatchFinish) {
        this.iswheelDispatchFinish = iswheelDispatchFinish;
    }

    public String getIsqualityInspectionFinish() {
        return isqualityInspectionFinish;
    }

    public void setIsqualityInspectionFinish(String isqualityInspectionFinish) {
        this.isqualityInspectionFinish = isqualityInspectionFinish;
    }

    public String getIsverifyFinish() {
        return isverifyFinish;
    }

    public void setIsverifyFinish(String isverifyFinish) {
        this.isverifyFinish = isverifyFinish;
    }

    public String getIsmagnetInspectionFinish() {
        return ismagnetInspectionFinish;
    }

    public void setIsmagnetInspectionFinish(String ismagnetInspectionFinish) {
        this.ismagnetInspectionFinish = ismagnetInspectionFinish;
    }

    public String getIsbearingCapFinish() {
        return isbearingCapFinish;
    }

    public void setIsbearingCapFinish(String isbearingCapFinish) {
        this.isbearingCapFinish = isbearingCapFinish;
    }

    public String getIsbearingUnCapFinish() {
        return isbearingUnCapFinish;
    }

    public void setIsbearingUnCapFinish(String isbearingUnCapFinish) {
        this.isbearingUnCapFinish = isbearingUnCapFinish;
    }

    public String getIsbearingUnloadFinish() {
        return isbearingUnloadFinish;
    }

    public void setIsbearingUnloadFinish(String isbearingUnloadFinish) {
        this.isbearingUnloadFinish = isbearingUnloadFinish;
    }

    public String getIswheelPolishFinish() {
        return iswheelPolishFinish;
    }

    public void setIswheelPolishFinish(String iswheelPolishFinish) {
        this.iswheelPolishFinish = iswheelPolishFinish;
    }

    public String getIsreInspectionFinish() {
        return isreInspectionFinish;
    }

    public void setIsreInspectionFinish(String isreInspectionFinish) {
        this.isreInspectionFinish = isreInspectionFinish;
    }

    public String getIsbearingNeckFinish() {
        return isbearingNeckFinish;
    }

    public void setIsbearingNeckFinish(String isbearingNeckFinish) {
        this.isbearingNeckFinish = isbearingNeckFinish;
    }

    public String getIsprocessFinish() {
        return isprocessFinish;
    }

    public void setIsprocessFinish(String isprocessFinish) {
        this.isprocessFinish = isprocessFinish;
    }

    public String getState() {
        return state;
    }

    public String getDipatchVehicleNumber() {
        return dipatchVehicleNumber;
    }

    public void setDipatchVehicleNumber(String dipatchVehicleNumber) {
        this.dipatchVehicleNumber = dipatchVehicleNumber;
    }

    public String getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(String dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public String getDipatchAxlePosition() {
        return dipatchAxlePosition;
    }

    public void setDipatchAxlePosition(String dipatchAxlePosition) {
        this.dipatchAxlePosition = dipatchAxlePosition;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public String getIsprocessFinishTime() {
        return isprocessFinishTime;
    }

    public void setIsprocessFinishTime(String isprocessFinishTime) {
        this.isprocessFinishTime = isprocessFinishTime;
    }

    public String getDispatchVehicleType() {
        return dispatchVehicleType;
    }

    public void setDispatchVehicleType(String dispatchVehicleType) {
        this.dispatchVehicleType = dispatchVehicleType;
    }

    public String getIsreMeasureFinish() {
        return isreMeasureFinish;
    }

    public void setIsreMeasureFinish(String isreMeasureFinish) {
        this.isreMeasureFinish = isreMeasureFinish;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
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

    public String getRepairWay() {
        return repairWay;
    }

    public void setRepairWay(String repairWay) {
        this.repairWay = repairWay;
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
        return "WheelInfo{" +
                "wheelId=" + wheelId +
                ", takeInDate='" + takeInDate + '\'' +
                ", takeInDepot='" + takeInDepot + '\'' +
                ", takeInReason='" + takeInReason + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", axleNumber='" + axleNumber + '\'' +
                ", axleType='" + axleType + '\'' +
                ", axleMaterial='" + axleMaterial + '\'' +
                ", axleMadeDate='" + axleMadeDate + '\'' +
                ", axleMadeIn='" + axleMadeIn + '\'' +
                ", axlePosition='" + axlePosition + '\'' +
                ", wheelType='" + wheelType + '\'' +
                ", wheelMaterial='" + wheelMaterial + '\'' +
                ", wheelMadeIn='" + wheelMadeIn + '\'' +
                ", wheelAssemblefirstDate='" + wheelAssemblefirstDate + '\'' +
                ", wheelAssemblefirstIn='" + wheelAssemblefirstIn + '\'' +
                ", wheelAssemblelastDate='" + wheelAssemblelastDate + '\'' +
                ", wheelAssemblelastIn='" + wheelAssemblelastIn + '\'' +
                ", wheelMarkLeft='" + wheelMarkLeft + '\'' +
                ", wheelMarkRight='" + wheelMarkRight + '\'' +
                ", worker='" + worker + '\'' +
                ", infoTakeFinish='" + infoTakeFinish + '\'' +
                ", infoTakeFinishTime='" + infoTakeFinishTime + '\'' +
                ", isMeasureFinish='" + isMeasureFinish + '\'' +
                ", isbearingRepairFinish='" + isbearingRepairFinish + '\'' +
                ", isaxleInspectionFinish='" + isaxleInspectionFinish + '\'' +
                ", isWheelRoundingFinish='" + isWheelRoundingFinish + '\'' +
                ", isbearingLoadFinish='" + isbearingLoadFinish + '\'' +
                ", ismagnetInspectionFinish='" + ismagnetInspectionFinish + '\'' +
                ", isbearingCapFinish='" + isbearingCapFinish + '\'' +
                ", isbearingrollTestFinish='" + isbearingrollTestFinish + '\'' +
                ", iswheelDispatchFinish='" + iswheelDispatchFinish + '\'' +
                ", isqualityInspectionFinish='" + isqualityInspectionFinish + '\'' +
                ", isverifyFinish='" + isverifyFinish + '\'' +
                ", isbearingCapFinishW='" + isbearingCapFinishW + '\'' +
                ", isbearingCapFinishL='" + isbearingCapFinishL + '\'' +
                ", isbearingUnCapFinish='" + isbearingUnCapFinish + '\'' +
                ", isbearingUnloadFinish='" + isbearingUnloadFinish + '\'' +
                ", iswheelPolishFinish='" + iswheelPolishFinish + '\'' +
                ", isreInspectionFinish='" + isreInspectionFinish + '\'' +
                ", isbearingNeckFinish='" + isbearingNeckFinish + '\'' +
                ", isprocessFinish='" + isprocessFinish + '\'' +
                ", state='" + state + '\'' +
                ", dispatchVehicleType='" + dispatchVehicleType + '\'' +
                ", dipatchVehicleNumber='" + dipatchVehicleNumber + '\'' +
                ", dispatchDate='" + dispatchDate + '\'' +
                ", dipatchAxlePosition='" + dipatchAxlePosition + '\'' +
                ", storePositionX='" + storePositionX + '\'' +
                ", storePositionY='" + storePositionY + '\'' +
                ", reserve1='" + reserve1 + '\'' +
                ", reserve2='" + reserve2 + '\'' +
                ", isprocessFinishTime='" + isprocessFinishTime + '\'' +
                ", isreMeasureFinish='" + isreMeasureFinish + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", repairWay='" + repairWay + '\'' +
                ", repairProcess='" + repairProcess + '\'' +
                ", discardReason='" + discardReason + '\'' +
                '}';
    }
}
