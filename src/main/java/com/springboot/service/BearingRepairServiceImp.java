package com.springboot.service;

import com.springboot.dao.BearingRepairDao;
import com.springboot.dao.WheelDao;
import com.springboot.domain.BearingRepair;
import com.springboot.domain.BearingUnCap;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BearingRepairServiceImp implements BearingRepairService{
    @Autowired
    private BearingRepairDao bearingRepairDao;
    @Autowired
    private WheelDao wheelDao;
    @Override
    public BearingRepair addBearingRepair(BearingRepair bearingRepair) {

//        Integer count =  bearingRepairDao.findWheelIdCount(bearingRepair.getWheelId());
//        if (count == 1){
//            updateBearingRepair(bearingRepair);
//        }else{
//            bearingRepairDao.insertBearingRepair(bearingRepair);
//        }
//        bearingRepairDao.flushWheelInfoRepairFinish(bearingRepair.getWheelId());
//        flushWheelInfo(bearingRepair);
        bearingRepairDao.insertBearingRepair(bearingRepair);
        bearingRepairDao.flushWheelInfoRepairFinish(bearingRepair.getWheelId());
        return bearingRepair;
    }

    @Override
    public void updateBearingRepair(BearingRepair bearingRepair) {
        bearingRepairDao.updateBearingRepair(bearingRepair);
        this.fresh(bearingRepair.getWheelId());
        flushWheelInfo(bearingRepair);
    }

    @Override
    public void deleteBearing(String wheelId) {
        Integer id = Integer.parseInt(wheelId);
//        bearingRepairDao.deleteBearingRepairByWheelId(wheelId);
        //回滚完工状态
        bearingRepairDao.rollbackWheelInfoRepairFinish(id);
//        //回滚压装状态
//        bearingRepairDao.rollbackWheelInfoLoadFinish(wheelId);

        this.fresh(id);
    }

    @Override
    public List<WheelInfo> searchWheelInfoRepairCondition(SearchWheelParam param) {
        return bearingRepairDao.searchWheelInfoByconditionRepair(param.getWheelId(),
                param.getTakeInDateFrom(),
                param.getTakeInDateTo(),
                param.getAxleNumber(),param.getVehicleNumber(),
                param.getInfoTakeFinishTimeFrom(),
                param.getInfoTakeFinishTimeTo());
    }

    @Override
    public void addBearingUnCap(BearingUnCap bearingUnCap) {
        bearingRepairDao.updateBearingUnCap(bearingUnCap);
        wheelDao.finishWheelInfobearingUnCap(bearingUnCap.getWheelId());
    }

    @Override
    public void flushWheelInfo(BearingRepair bearingRepair) {
        Integer wheelId = bearingRepair.getWheelId();
        String loadindex = "-1";
        String capindex = "-1";
        String left = bearingRepair.getRepairProgressLeft();
        String right = bearingRepair.getRepairProgressRight();
        if ("0".equals(right) && !"0".equals(left)) {
            loadindex = "1";
            capindex = "0";
        }
        if (!"0".equals(right) && "0".equals(left)) {
            loadindex = "2";
            capindex = "0";
        }
        if (!"0".equals(right) && !"0".equals(left)) {
            loadindex = "3";
            capindex = "0";
        }
        bearingRepairDao.flushWheelInfoLoadFinish(Integer.parseInt(wheelId.toString()),loadindex,capindex);
    }

    public void fresh(Integer id){
        wheelDao.rollbackWheelInfoisbearingLoadFinish(id);
        wheelDao.rollbackWheelInfobearingCapFinish(id);
        wheelDao.rollbackWheelInforollTestFinish(id);
        wheelDao.rollbackWheelInfowheelDispatchFinish(id);
        wheelDao.rollbackWheelInfowheelRemeasureFinish(id);
        wheelDao.rollbackWheelInfoqualityInspectionFinish(id);
        wheelDao.rollbackWheelInfoverifyFinish(id);
        wheelDao.rollbackWheelInfoprocessFinish(id);
    }
}
