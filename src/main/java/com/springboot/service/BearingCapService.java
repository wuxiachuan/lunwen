package com.springboot.service;

import com.springboot.domain.BearingCap;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;

import java.util.List;

public interface BearingCapService {
    void addBearingCap(BearingCap bearingCap);
    void updateBearingCap(BearingCap bearingCap);
    void flushWheelInfo(BearingCap bearingCap);
    void deleteBearingCap(String id);
    List<WheelInfo> searchWheelInfoBycondition(SearchWheelParam param);
}
