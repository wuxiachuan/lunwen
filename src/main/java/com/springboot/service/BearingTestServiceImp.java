package com.springboot.service;

import com.springboot.dao.BearingCapDao;
import com.springboot.dao.BearingTestDao;
import com.springboot.dao.WheelDao;
import com.springboot.domain.BearingCap;
import com.springboot.domain.BearingTest;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BearingTestServiceImp implements BearingTestService{
    @Autowired
    private BearingTestDao bearingTestDao;
    @Autowired
    private WheelDao wheelDao;
    @Override
    public void addBearingTest(BearingTest bearingTest) {

//        Integer count =  bearingTestDao.findWheelIdCount(bearingTest.getWheelId());
//        if (count == 1){
//            updateBearingTest(bearingTest);
//        }else{
//            bearingTestDao.insertBearingTest(bearingTest);
//        }
        bearingTestDao.insertBearingTest(bearingTest);
        flushWheelInfoTest(bearingTest);
    }

    @Override
    public void updateBearingTest(BearingTest bearingTest) {
        bearingTestDao.updateBearingTest(bearingTest);
        fresh(bearingTest.getWheelId());
    }

    @Override
    public void flushWheelInfoTest(BearingTest bearingTest) {
        bearingTestDao.flushWheelInfoRollTestFinish(bearingTest.getWheelId());
    }

    @Override
    public void deleteBearingTest(String wheelId) {
        Integer id = Integer.parseInt(wheelId);
        bearingTestDao.deleteBearingTestByWheelId(id);
        bearingTestDao.rollbackWheelInfoRollTestFinish(id);
        fresh(id);
    }

    @Override
    public List<WheelInfo> searchWheelInfoTest(SearchWheelParam param) {
        return bearingTestDao.searchWheelInfoByconditionTest(
                param.getWheelId(),
                param.getTakeInDateFrom(),
                param.getTakeInDateTo(),
                param.getAxleNumber(),param.getVehicleNumber(),
                param.getInfoTakeFinishTimeFrom(),
                param.getInfoTakeFinishTimeTo());
    }
    public void fresh(Integer id){
        wheelDao.rollbackWheelInfowheelDispatchFinish(id);
        wheelDao.rollbackWheelInfoqualityInspectionFinish(id);
        wheelDao.rollbackWheelInfowheelRemeasureFinish(id);
        wheelDao.rollbackWheelInfoverifyFinish(id);
        wheelDao.rollbackWheelInfoprocessFinish(id);
    }
}
