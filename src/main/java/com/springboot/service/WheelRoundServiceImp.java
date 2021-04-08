package com.springboot.service;

import com.springboot.dao.BearingCapDao;
import com.springboot.dao.WheelDao;
import com.springboot.dao.WheelRoundDao;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import com.springboot.domain.WheelRound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WheelRoundServiceImp implements WheelRoundService{
    @Autowired
    private WheelRoundDao wheelRoundDao;
    @Autowired
    private BearingCapDao bearingCapDao;
    @Autowired
    private WheelDao wheelDao;
    @Override
    public void addWheelRound(WheelRound wheelRound) {
//        Integer count =  wheelRoundDao.findWheelIdCount(wheelRound.getWheelId());
//        if (count == 1){
//            updateWheelRound(wheelRound);
//        }else{
//            wheelRoundDao.insertWheelRound(wheelRound);
//        }
//        flushWheelInfoWheelRound(wheelRound);
        wheelRoundDao.insertWheelRound(wheelRound);
        flushWheelInfoWheelRound(wheelRound);
    }

    @Override
    public void updateWheelRound(WheelRound wheelRound) {
        wheelRoundDao.updateWheelRound(wheelRound);
        fresh(wheelRound.getWheelId());
    }

    @Override
    public void flushWheelInfoWheelRound(WheelRound wheelRound) {
        wheelRoundDao.flushWheelInfoWheelRoundFinish(wheelRound.getWheelId());
    }

    @Override
    public void deleteWheelRound(String wheelId) {
        Integer id = Integer.parseInt(wheelId);
        wheelRoundDao.deleteWheelRoundByWheelId(id);
        wheelRoundDao.rollbackWheelInfoWheelRoundFinish(id);

//        wheelDao.rollbackWheelInfobearingCapFinish(id);
        fresh(id);
    }

    @Override
    public List<WheelInfo> searchWheelInfoWheelRound(SearchWheelParam param) {
        return wheelRoundDao.searchWheelInfoByconditionWheelRound(
                                param.getWheelId(),
                                param.getTakeInDateFrom(),
                                param.getTakeInDateTo(),
                                param.getAxleNumber(),param.getVehicleNumber(),
                                param.getInfoTakeFinishTimeFrom(),
                                param.getInfoTakeFinishTimeTo());
    }

    private void fresh(Integer id){
        bearingCapDao.rollbackWheelInfoCapFinish(id);
        wheelDao.rollbackWheelInforollTestFinish(id);
        wheelDao.rollbackWheelInfowheelDispatchFinish(id);
        wheelDao.rollbackWheelInfoqualityInspectionFinish(id);
        wheelDao.rollbackWheelInfoverifyFinish(id);
        wheelDao.rollbackWheelInfoprocessFinish(id);
    }
}
