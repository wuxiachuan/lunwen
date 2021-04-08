package com.springboot.service;

import com.springboot.domain.AxleInspection;
import com.springboot.domain.BearingTest;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;

import java.util.List;

public interface AxleInspectionService {

    void addAxleInspection(AxleInspection axleInspection);
    void updateAxleInspection(AxleInspection axleInspection);
    void flushWheelInfoAxleInspection(AxleInspection axleInspection);
    void deleteBearingAxleInspection(String id);
    List<WheelInfo> searchWheelInfoAxleInspection(SearchWheelParam param);

    void addMagInspection(AxleInspection axleInspection);
    void updateMagInspection(AxleInspection axleInspection);
    void flushWheelInfoMagInspection(AxleInspection axleInspection);
    void deleteBearingMagInspection(String id);
    List<WheelInfo> searchWheelInfoMagInspection(SearchWheelParam param);

    void addReInspection(AxleInspection axleInspection);
    void updateReInspection(AxleInspection axleInspection);
    void flushWheelInfoReInspection(AxleInspection axleInspection);
    void deleteBearingReInspection(String id);
    List<WheelInfo> searchWheelInfoReInspection(SearchWheelParam param);
}
