package com.springboot.service;

import com.alibaba.fastjson.JSON;
import com.springboot.dao.*;
import com.springboot.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QualityServiceImp implements QualityService{
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private WheelRoundDao wheelRoundDao;
    @Autowired
    private MeasureDao measureDao;
    @Autowired
    private WheelDispatchDao wheelDispatchDao;
    @Autowired
    private AxleInspectionDao axleInspectionDao;
    @Autowired
    private BearingTestDao bearingTestDao;
    @Autowired
    private BearingCapDao bearingCapDao;
    @Autowired
    private BearingLoadDao bearingLoadDao;
    @Autowired
    private BearingRepairDao bearingRepairDao;
    @Autowired
    WheelRepository wheelRepository;
    private SimpleDateFormat dateFormater;
    public QualityServiceImp(){
        this.dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    @Override
    public void modifyInfo(String database, Object data) {
        if ("wheelinfo".equals(database)){
            WheelInfo info = JSON.parseObject(JSON.toJSONString(data),WheelInfo.class);
            wheelDao.updateWheelInfo(info);
        }
        else if ("wheelmeasure".equals(database)){
            WheelMeasure info = JSON.parseObject(JSON.toJSONString(data),WheelMeasure.class);
            measureDao.updateWheelMeasure(info);
        }
        else if ("bearingRepair".equals(database)){
            BearingRepair info = JSON.parseObject(JSON.toJSONString(data),BearingRepair.class);
            bearingRepairDao.updateBearingRepair(info);
        }
        else if ("axleInspection".equals(database)){
            AxleInspection info = JSON.parseObject(JSON.toJSONString(data),AxleInspection.class);
            axleInspectionDao.updateMagInspection(info);
            axleInspectionDao.updateAxleInspection(info);
        }
        else  if ("wheelRound".equals(database)){
            WheelRound info = JSON.parseObject(JSON.toJSONString(data),WheelRound.class);
            wheelRoundDao.updateWheelRound(info);
        }
        else if ("bearingLoad".equals(database)){
            BearingLoad info = JSON.parseObject(JSON.toJSONString(data),BearingLoad.class);
            bearingLoadDao.updateBearingLoad(info);
        }
        else if ("bearingCap".equals(database)){
            BearingCap info = JSON.parseObject(JSON.toJSONString(data),BearingCap.class);
            bearingCapDao.updateBearingCap(info);
        }
        else  if ("rollTest".equals(database)){
            BearingTest info = JSON.parseObject(JSON.toJSONString(data),BearingTest.class);
            bearingTestDao.updateBearingTest(info);
        }
        else if ("wheelDispatch".equals(database)){
            WheelDispatch info = JSON.parseObject(JSON.toJSONString(data),WheelDispatch.class);
            wheelDispatchDao.updateWheelDispatch(info);
        }else {

        }
    }

    @Override
    public void finishInspection(String name, String id) {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormater.format(new Date());
        String storePositionX = "null";
        String storePositionY = "null";
        List<Integer> position = wheelRepository.putAxleInPosition(id);
        if (position!=null) {
            storePositionX = String.valueOf(position.get(0));
            storePositionY = String.valueOf(position.get(1));
        }
        wheelDispatchDao.finishInspection(name,Integer.parseInt(id),time,storePositionX,storePositionY);
        wheelDispatchDao.flushWheelInfoqualityInspectionFinish(Integer.parseInt(id),time,storePositionX,storePositionY);
    }
}
