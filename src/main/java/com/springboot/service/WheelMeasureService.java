package com.springboot.service;

import com.springboot.domain.Result;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import com.springboot.domain.WheelMeasure;

import java.util.List;

public interface WheelMeasureService {
    void addMeasure(WheelMeasure wheelMeasure);
    WheelMeasure findWheelMeasureByWheelId(Integer wheelId);
    void updateWheelMeasure(WheelMeasure wheelMeasure);
    void deleteWheelMeasure(String wheelId);
    void flushWheelInfo(WheelMeasure wheelMeasure);
    List<WheelInfo> searchWheelInfoMeasure(SearchWheelParam param);
    void discardWheel(Integer wheelId,String reason);
}
