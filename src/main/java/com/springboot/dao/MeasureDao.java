package com.springboot.dao;

import com.springboot.domain.WheelInfo;
import com.springboot.domain.WheelMeasure;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MeasureDao {
    void insertWheelMeasure(WheelMeasure wheelMeasure);
    WheelMeasure findWheelMeasureByWheelId(Integer wheelId);
    void flushWheelInfoMeasureFinish(Integer wheelId);
    void rollbackWheelInfoMeasureFinish(Integer wheelId);
    void flushWheelInfoInspectionFinish(Integer wheelId);
    void flushWheelInfoMagInspectionFinish(Integer wheelId);
    void rollbackWheelInfoMagInspectionFinish(Integer wheelId);
    void rollbackWheelInfoInspectionFinish(Integer wheelId);
    void flushWheelInfoWheelRounding(Integer wheelId);
    void rollbackWheelInfoWheelRounding(Integer wheelId);
    void rollbackWheelInfoRepairFinish(Integer wheelId);

    void updateWheelMeasure(WheelMeasure wheelMeasure);
    void deleteWheelMeasure(Integer wheelId);
    List<WheelInfo> searchWheelInfoByconditionMeasure(@Param("wheelId") String wheelId,
                                                      @Param("takeInDateFrom") String takeInDateFrom,
                                                      @Param("takeInDateTo") String takeInDateTo,
                                                      @Param("axleNumber") String axleNumber,
                                                      @Param("vehicleNumber")String vehicleNumber,
                                                      @Param("infoTakeFinishTimeFrom")String infoTakeFinishTimeFrom,
                                                      @Param("infoTakeFinishTimeTo")String infoTakeFinishTimeTo);
    Integer findWheelIdCount(Integer wheelId);

}
