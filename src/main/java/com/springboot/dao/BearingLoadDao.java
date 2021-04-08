package com.springboot.dao;

import com.springboot.domain.BearingLoad;
import com.springboot.domain.BearingUnLoad;
import com.springboot.domain.WheelInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BearingLoadDao {
    void updateBearingNeckMeasure(BearingLoad bearingLoad);
    void updateBearingLoad(BearingLoad bearingLoad);
    void addBearingNeckMeasure(BearingLoad bearingLoad);
    void addBearingLoad(BearingLoad bearingLoad);
    List<WheelInfo> findWheelInfoToBearingLoad();
    List<WheelInfo> searchWheelInfoBycondition(@Param("wheelId") String wheelId,
                                               @Param("takeInDateFrom") String takeInDateFrom,
                                               @Param("takeInDateTo") String takeInDateTo,
                                               @Param("axleNumber") String axleNumber,
                                               @Param("vehicleNumber")String vehicleNumber,
                                               @Param("infoTakeFinishTimeFrom")String infoTakeFinishTimeFrom,
                                               @Param("infoTakeFinishTimeTo")String infoTakeFinishTimeTo);

    BearingLoad findBearingLoadByWheelId(Integer id);
    void deleteBearingLoadByWheelId(Integer wheelId);

    void flushWheelInfoLoadFinish(Integer wheelId);
    void rollbackWheelInfoLoadFinish(@Param("wheelId") Integer wheelId,@Param("index") String index);
    Integer findWheelIdCount(Integer wheelId);
}
