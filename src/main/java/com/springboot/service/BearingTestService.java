package com.springboot.service;

import com.springboot.domain.BearingTest;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;

import java.util.List;

public interface BearingTestService {
    void addBearingTest(BearingTest bearingTest);
    void updateBearingTest(BearingTest bearingTest);
    void flushWheelInfoTest(BearingTest bearingTest);
    void deleteBearingTest(String id);
    List<WheelInfo> searchWheelInfoTest(SearchWheelParam param);
}
