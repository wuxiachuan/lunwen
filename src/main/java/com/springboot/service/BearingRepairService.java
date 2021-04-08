package com.springboot.service;

import com.springboot.domain.BearingRepair;
import com.springboot.domain.BearingUnCap;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;

import java.util.List;

public interface BearingRepairService {
    BearingRepair addBearingRepair(BearingRepair bearingRepair);
    void updateBearingRepair(BearingRepair bearingRepair);
    void flushWheelInfo(BearingRepair bearingRepair);
    void deleteBearing(String id);
    List<WheelInfo> searchWheelInfoRepairCondition(SearchWheelParam param);
    void addBearingUnCap(BearingUnCap bearingUnCap);
}
