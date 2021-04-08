package com.springboot.service;

import com.springboot.domain.*;

import java.util.List;

public interface WheelDispatchService {
    void addWheelDispatchRemeasure(WheelDispatch wheelDispatch);
    void updateWheelDispatchRemeasure(WheelDispatch wheelDispatch);
    void flushWheelInfoRemeasure(WheelDispatch wheelDispatch);
    void deleteWheelDispatch(String id);
    List<WheelInfo> searchWheelInfoDispatch(SearchWheelParam param);
    List<VehicleInfo> dispatch(List<VehicleInfo> numlist);
    List<WheelDispatch> find2match(VehicleInfo target);
    Boolean receiveResult(List<VehicleInfo> resultlist,String matcher);
    void adddata();
}
