package com.springboot.dao;

import com.springboot.domain.WheelInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WheelDao {
    void insertWheelInfo(WheelInfo wheelInfo);
    List<WheelInfo> findWheelInfo(@Param("wheelId") String wheelId,
                                  @Param("takeInDateFrom") String takeInDateFrom,
                                  @Param("takeInDateTo") String takeInDateTo,
                                  @Param("axleNumber") String axleNumber,
                                  @Param("vehicleNumber")String vehicleNumber,
                                  @Param("infoTakeFinishTimeFrom")String infoTakeFinishTimeFrom,
                                  @Param("infoTakeFinishTimeTo")String infoTakeFinishTimeTo);
    void updateWheelInfo(WheelInfo wheelInfo);
    void deleteWheelInfo(Integer id);
    List<WheelInfo> findWheelInfoToMeasure();
    List<WheelInfo> findWheelInfoToBearing();

    WheelInfo findWheelInfoById(Integer wheelId);
    WheelInfo findWheelInfoByIdAll(Integer wheelId);
    List<WheelInfo> findAllWheelInfo();
    List<WheelInfo> findTodayWheelInfo(String date);
    List<WheelInfo> findWheelInfoByCondition(@Param("wheelId") String wheelId,
                                             @Param("takeInDateFrom") String takeInDateFrom,
                                             @Param("takeInDateTo") String takeInDateTo,
                                             @Param("axleNumber") String axleNumber,
                                             @Param("vehicleNumber")String vehicleNumber,
                                             @Param("infoTakeFinishTimeFrom")String infoTakeFinishTimeFrom,
                                             @Param("infoTakeFinishTimeTo")String infoTakeFinishTimeTo,
                                             @Param("dispatchDateFrom")String dispatchDateFrom,
                                             @Param("dispatchDateTo")String dispatchDateTo,
                                             @Param("dipatchVehicleNumber")String dipatchVehicleNumber,
                                             @Param("status")String status,
                                             @Param("axleType")String axleType,
                                             @Param("takeInReason")String takeInReason,
                                             @Param("isprocessFinish") String isprocessFinish,
                                             //车轴制造单位
                                             @Param("infoTakeFinishTime") String infoTakeFinishTime,
                                             //车轮制造单位
                                             @Param("dispatchDate") String dispatchDate);


    List<WheelInfo> findWheelInfoByCondition2Check(@Param("wheelId") String wheelId,
                                             @Param("takeInDateFrom") String takeInDateFrom,
                                             @Param("takeInDateTo") String takeInDateTo,
                                             @Param("axleNumber") String axleNumber,
                                             @Param("vehicleNumber")String vehicleNumber,
                                             @Param("infoTakeFinishTimeFrom")String infoTakeFinishTimeFrom,
                                             @Param("infoTakeFinishTimeTo")String infoTakeFinishTimeTo,
                                             @Param("dispatchDateFrom")String dispatchDateFrom,
                                             @Param("dispatchDateTo")String dispatchDateTo,
                                             @Param("dipatchVehicleNumber")String dipatchVehicleNumber,
                                             @Param("status")String status,
                                             @Param("axleType")String axleType,
                                             @Param("takeInReason")String takeInReason,
                                             @Param("isprocessFinish") String isprocessFinish,
                                             //车轴制造单位
                                             @Param("infoTakeFinishTime") String infoTakeFinishTime,
                                             //车轮制造单位
                                             @Param("dispatchDate") String dispatchDate);
    void robackWheelInfoMeasureFinish(Integer wheelId);
    void finishWheelInfoMeasure(Integer wheelId);
    void rollbackWheelInfobearingUnCapFinish(Integer wheelId);
    void finishWheelInfobearingUnCap(Integer wheelId);
    void finishWheelInfobearingUnLoad(Integer wheelId);
    void rollbackWheelInfoMagInspectionFinish(Integer wheelId);
    void finishWheelInfoMagInspection(Integer wheelId);
    void rollbackWheelInfoAxleInspectionFinish(Integer wheelId);
    void finishWheelInfoAxleInspection(Integer wheelId);
    void rollbackWheelInfoWheelRoundingFinish(Integer wheelId);
    void rollbackWheelInfoRepairFinish(Integer wheelId);
    void rollbackWheelInfoisbearingLoadFinish(Integer wheelId);
    void rollbackWheelInfobearingCapFinish(Integer wheelId);
    void rollbackWheelInforollTestFinish(Integer wheelId);
    void rollbackWheelInfowheelRemeasureFinish(Integer wheelId);
    void rollbackWheelInfowheelDispatchFinish(Integer wheelId);
    void rollbackWheelInfoqualityInspectionFinish(Integer wheelId);
    void rollbackWheelInfoverifyFinish(Integer wheelId);
    void rollbackWheelInfoprocessFinish(Integer wheelId);
    void finishWheelInfoisbearingNeck(Integer wheelId);
    void finishWheelInfoisbearingLoad(Integer wheelId);

    void setWheelInfoAxleInspectionFinish(Integer wheelId);
    void setWheelInfoMagInspectionFinish(Integer wheelId);
    void setWheelInfoWheelRoundingFinish(Integer wheelId);
    void setWheelInfoisbearingNeckFinish(@Param("wheelId") Integer wheelId,@Param("flag") String flag);
    void setWheelInfoisbearingLoadFinish(@Param("wheelId") Integer wheelId,@Param("flag") String flag);
    void setWheelInfoisbearingUnLoadFinish(@Param("wheelId") Integer wheelId,@Param("flag") String flag);
    void setWheelInfobearingCapFinish(Integer wheelId);
    void setWheelInfobearingUnCapFinish(Integer wheelId);





    List<WheelInfo> findWheelMeasure();
    List<WheelInfo> findBearingRepair();
    List<WheelInfo> findMagInspect();
    List<WheelInfo> findUtrInspect();
    List<WheelInfo> findWheelRound();
    List<WheelInfo> findWheelLoad();
    WheelInfo findBearingCap(Integer wheelId);
    WheelInfo findBearingCap2(Integer wheelId);
    WheelInfo findBearingTest(Integer wheelId);
    WheelInfo findBearingTest2(Integer wheelId);
    WheelInfo findBearingTest3(Integer wheelId);
    List<WheelInfo> findWheelRemeasure();
    List<WheelInfo> findWheelQalityInspect();
    void discardWheel(@Param("wheelId") Integer wheelId,@Param("state") String state,@Param("reason") String reason);


}
