package com.springboot.service;

import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import com.springboot.domain.WheelMeasure;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WheelService {
    WheelInfo insertWheelInfo(WheelInfo wheelInfo);
    List<WheelInfo> findWheelInfo(SearchWheelParam param);
    WheelInfo updateWheelInfo(WheelInfo wheelInfo);
    void deleteWheelInfo(String id);
    String generateQRcode(String wheelId) throws Exception;
}
