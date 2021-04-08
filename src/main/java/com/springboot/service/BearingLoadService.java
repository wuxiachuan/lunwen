package com.springboot.service;

import com.springboot.domain.*;

import java.util.List;

public interface BearingLoadService {
    void addBearingLoad(BearingLoad bearingLoad);
    void addBearingUnLoad(BearingUnLoad bearingUnLoad);
    void addBearingNeckMeasure(BearingLoad bearingLoad);
    void updateBearingLoad(BearingLoad bearingLoad);
    void flushWheelInfo(BearingLoad bearingLoad);
    void deleteBearing(String id,String index);
    List<WheelInfo>  searchWheelInfoBycondition(SearchWheelParam param);
}
