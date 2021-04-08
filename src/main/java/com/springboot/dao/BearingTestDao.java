package com.springboot.dao;

import com.springboot.domain.BearingTest;
import com.springboot.domain.WheelInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface BearingTestDao {
    void insertBearingTest(BearingTest bearingTest);
    void updateBearingTest(BearingTest bearingTest);

    List<WheelInfo> findWheelInfoToBearingTest();
    List<WheelInfo> searchWheelInfoByconditionTest(@Param("wheelId") String wheelId,
                                                   @Param("takeInDateFrom") String takeInDateFrom,
                                                   @Param("takeInDateTo") String takeInDateTo,
                                                   @Param("axleNumber") String axleNumber,
                                                   @Param("vehicleNumber")String vehicleNumber,
                                                   @Param("infoTakeFinishTimeFrom")String infoTakeFinishTimeFrom,
                                                   @Param("infoTakeFinishTimeTo")String infoTakeFinishTimeTo);
    BearingTest findBearingTestByWheelId(Integer id);
    void deleteBearingTestByWheelId(Integer wheelId);

    void flushWheelInfoRollTestFinish(Integer wheelId);
    void rollbackWheelInfoRollTestFinish(Integer wheelId);
    Integer findWheelIdCount(Integer wheelId);
}
