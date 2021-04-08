package com.springboot.service;

import com.springboot.domain.BearingTest;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import com.springboot.domain.WheelRound;

import java.util.List;

public interface WheelRoundService {
    void addWheelRound(WheelRound wheelRound);
    void updateWheelRound(WheelRound wheelRound);
    void flushWheelInfoWheelRound(WheelRound wheelRound);
    void deleteWheelRound(String id);
    List<WheelInfo> searchWheelInfoWheelRound(SearchWheelParam param);
}
