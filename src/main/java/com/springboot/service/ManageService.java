package com.springboot.service;

import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelAll;
import com.springboot.domain.WheelInfo;

import java.text.ParseException;
import java.util.List;

public interface ManageService {
    List<WheelInfo> findWheelInfoByCondition(SearchWheelParam param);
    WheelAll findWheelAllByWheelInfo(WheelInfo wh);
    List<WheelAll> findWheelAllByCondition(SearchWheelParam param);
    List<WheelInfo> findWheelInfoByCondition2Check(SearchWheelParam param);
    List<WheelAll> findWheelAllByCondition2Check(SearchWheelParam param);
    List<WheelAll> findAllWheelAll();
    WheelAll findWheelAllByWheelId(Integer id);

    List<WheelInfo> findWheels(String name) throws ParseException;
}
