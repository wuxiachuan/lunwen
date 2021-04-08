package com.springboot.dao;

import com.springboot.domain.VehicleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VehicleInfoDao {
    void insertVehicleInfo(VehicleInfo vehicleInfo);
    void updateVehicleInfo(VehicleInfo vehicleInfo);
    VehicleInfo findVehicleInfo(String vehicleNum);
    void addAxleIn1(@Param("axleNum") String axleNum, @Param("vehicleNum")  String vehicleNum);
    void addAxleIn2(@Param("axleNum") String axleNum,@Param("vehicleNum")  String vehicleNum);
    void addAxleIn3(@Param("axleNum") String axleNum,@Param("vehicleNum")  String vehicleNum);
    void addAxleIn4(@Param("axleNum") String axleNum,@Param("vehicleNum")  String vehicleNum);
    List<VehicleInfo> findVehicleInfoByCondition(
                                                @Param("Id") String Id,
                                                @Param("vehicleNumber") String vehicleNumber,
                                                @Param("vehicleType")String vehicleType,
                                                @Param("RepairDateFrom")String RepairDateFrom,
                                                @Param("RepairDateTo")String RepairDateTo,
                                                @Param("finishTimeFrom")String finishTimeFrom,
                                                @Param("finishTimeTo")String finishTimeTo,
                                                @Param("isFinish")String isFinish);
    List<VehicleInfo> findVehicleToMatch(
                                            @Param("Id") String Id,
                                            @Param("vehicleNumber") String vehicleNumber,
                                            @Param("vehicleType")String vehicleType,
                                            @Param("RepairDate")String RepairDate);
    void prepareVehicle(String vehicleNum);
    List<VehicleInfo> findVehicleInfoByDate(String RepairDate);
    List<VehicleInfo> findVehicleInfoAll();
}
