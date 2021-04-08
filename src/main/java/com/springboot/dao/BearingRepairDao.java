package com.springboot.dao;

import com.springboot.domain.BearingRepair;
import com.springboot.domain.BearingUnCap;
import com.springboot.domain.WheelInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BearingRepairDao {
    void insertBearingRepair(BearingRepair bearingRepair);
    void updateBearingRepair(BearingRepair bearingRepair);
    void updateBearingUnCap(BearingUnCap bearingUnCap);
    void flushWheelInfoRepairFinish(Integer wheelId);
    void rollbackWheelInfoRepairFinish(Integer wheelId);
    void flushWheelInfoLoadFinish(@Param("wheelId") Integer wheelId,@Param("loadindex") String loadindex,@Param("capindex") String capindex);
    void rollbackWheelInfoLoadFinish(Integer wheelId);
    BearingRepair findBearingRepairByWheelId(Integer wheelId);
    void deleteBearingRepairByWheelId(Integer wheelId);
    List<WheelInfo> searchWheelInfoByconditionRepair(@Param("wheelId") String wheelId,
                                                     @Param("takeInDateFrom") String takeInDateFrom,
                                                     @Param("takeInDateTo") String takeInDateTo,
                                                     @Param("axleNumber") String axleNumber,
                                                     @Param("vehicleNumber")String vehicleNumber,
                                                     @Param("infoTakeFinishTimeFrom")String infoTakeFinishTimeFrom,
                                                     @Param("infoTakeFinishTimeTo")String infoTakeFinishTimeTo);
    Integer findWheelIdCount(Integer wheelId);
}
