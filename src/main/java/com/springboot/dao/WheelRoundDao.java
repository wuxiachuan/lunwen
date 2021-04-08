package com.springboot.dao;

import com.springboot.domain.BearingTest;
import com.springboot.domain.WheelInfo;
import com.springboot.domain.WheelMeasure;
import com.springboot.domain.WheelRound;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface WheelRoundDao {
    void insertWheelRound(WheelRound wheelRound);
    void updateWheelRound(WheelRound wheelRound);

    List<WheelInfo> findWheelInfoToWheelRound();
    List<WheelInfo> searchWheelInfoByconditionWheelRound(@Param("wheelId") String wheelId,
                                                         @Param("takeInDateFrom") String takeInDateFrom,
                                                         @Param("takeInDateTo") String takeInDateTo,
                                                         @Param("axleNumber") String axleNumber,
                                                         @Param("vehicleNumber")String vehicleNumber,
                                                         @Param("infoTakeFinishTimeFrom")String infoTakeFinishTimeFrom,
                                                         @Param("infoTakeFinishTimeTo")String infoTakeFinishTimeTo);

    WheelRound findWheelRoundByWheelId(Integer id);
    void deleteWheelRoundByWheelId(Integer wheelId);

    void flushWheelInfoWheelRoundFinish(Integer wheelId);
    void rollbackWheelInfoWheelRoundFinish(Integer wheelId);

    WheelMeasure getoriginwheelround(Integer wheelId);
    Integer findWheelIdCount(Integer wheelId);
}
