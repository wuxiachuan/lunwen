package com.springboot.dao;

import com.springboot.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface WheelDispatchDao {
    void insertWheelDispatch(WheelDispatch wheelDispatch);
    void updateWheelDispatch(WheelDispatch wheelDispatch);

    List<WheelInfo> findWheelInfoToWheelDispatchRemeasure();
    List<WheelInfo> searchWheelInfoByconditionWheelDispatch(@Param("wheelId") String wheelId,
                                                            @Param("takeInDateFrom") String takeInDateFrom,
                                                            @Param("takeInDateTo") String takeInDateTo,
                                                            @Param("axleNumber") String axleNumber,
                                                            @Param("vehicleNumber")String vehicleNumber,
                                                            @Param("infoTakeFinishTimeFrom")String infoTakeFinishTimeFrom,
                                                            @Param("infoTakeFinishTimeTo")String infoTakeFinishTimeTo);

    WheelDispatch findWheelDispatchByWheelId(Integer id);
    void deleteWheelDispatchByWheelId(Integer wheelId);

    void flushWheelInfowheelRemeasureFinish(Integer wheelId);
    void rollbackWheelInfoWheelRemeasureFinish(Integer wheelId);

    void flushWheelInfoWheelDispatchFinish(@Param("wheelId") Integer wheelId,
                                           @Param("dispatchVehicleType") String dispatchVehicleType,
                                           @Param("dipatchVehicleNumber") String dipatchVehicleNumber,
                                           @Param("dipatchAxlePosition") Integer dipatchAxlePosition,
                                           @Param("dispatchDate") String dispatchDate);
    void rollbackWheelInfoWheelDispatchFinish(Integer wheelId);

    void flushWheelInfoqualityInspectionFinish(@Param("wheelId") Integer wheelId,
                                               @Param("time") String time,
                                               @Param("storePositionX") String storePositionX,
                                               @Param("storePositionY") String storePositionY);

    void finishInspection(@Param("inspector") String inspector,
                          @Param("wheelId") Integer wheelId,
                          @Param("time") String time,
                          @Param("storePositionX") String storePositionX,
                          @Param("storePositionY") String storePositionY);

    Integer findWheelIdCount(Integer wheelId);

    List<WheelMeasure> findWheelOrgInfoOfVehicle(String number);
    List<WheelDispatch> findTarget(@Param("low") String low,
                                   @Param("high")String high,
                                   @Param("axleLife")String axleLife,
                                   @Param("bearingLife")String bearingLife,
                                   @Param("axleType")String axleType);

    List<VehicleInfo> findvehicleNum(@Param("vehicleNum") String vehicleNum,
                                    @Param("takeInDateFrom")String takeInDateFrom,
                                    @Param("takeInDateTo")String takeInDateTo);

    void setchooseMark(@Param("id1")Integer id1,@Param("id2")Integer id2,@Param("id3")Integer id3,@Param("id4")Integer id4);
    void flushchooseMark(@Param("id1")String id1,@Param("id2")String id2,@Param("id3")String id3,@Param("id4")String id4);
    void resetchooseMark();
    void updateDispatchStatus(@Param("wheelId") Integer wheelId,
                              @Param("vehicleType") String vehicleType,
                              @Param("vehicleNumber") String vehicleNumber,
                              @Param("axleDispatchPosition") Integer axleDispatchPosition,
                              @Param("dispatchFinishTime") String dispatchFinishTime,
                              @Param("matcher") String matcher);
    List<WheelDispatch> find2match(@Param("axleNumber") String axleNumber,
                                   @Param("axleType")String axleType,
                                   @Param("axleMaterial")String axleMaterial,
                                   @Param("axleMadeIn")String axleMadeIn,
                                   @Param("wheelDiameterLow")String wheelDiameterLow,
                                   @Param("wheelDiameterHigh") String wheelDiameterHigh,
                                   @Param("bearingAssembleDateLeft")String bearingAssembleDateLeft,
                                   @Param("bearingAssembleDateRight")String bearingAssembleDateRight,
                                   @Param("axleMadeDate")String axleMadeDate,
                                   @Param("takeInDate")String takeInDate);

}
