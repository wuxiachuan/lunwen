package com.springboot.service;

import com.springboot.dao.AxleInspectionDao;
import com.springboot.dao.WheelDao;
import com.springboot.domain.AxleInspection;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AxleInspectionServiceImp implements AxleInspectionService{
    @Autowired
    private AxleInspectionDao axleInspectionDao;
    @Autowired
    private WheelDao wheelDao;
    @Override
    public void addAxleInspection(AxleInspection axleInspection) {
//        Integer count =  axleInspectionDao.findWheelIdCount(axleInspection.getWheelId());
//        if (count == 1){
//            updateAxleInspection(axleInspection);
//        }else{
//            axleInspectionDao.insertAxleInspection(axleInspection);
//        }
//        flushWheelInfoAxleInspection(axleInspection);
        axleInspectionDao.updateAxleInspection(axleInspection);
        flushWheelInfoAxleInspection(axleInspection);
    }

    @Override
    public void updateAxleInspection(AxleInspection axleInspection) {
        axleInspectionDao.updateAxleInspection(axleInspection);
        fresh(axleInspection.getWheelId());
    }

    @Override
    public void flushWheelInfoAxleInspection(AxleInspection axleInspection) {
        axleInspectionDao.flushWheelInfoAxleInspectionFinish(axleInspection.getWheelId());
    }

    @Override
    public void deleteBearingAxleInspection(String wheelId) {
        Integer id =Integer.parseInt(wheelId);
        axleInspectionDao.deleteAxleInspectionByWheelId(id);
        axleInspectionDao.rollbackWheelInfoAxleInspectionFinish(id);
        fresh(id);
    }

    @Override
    public List<WheelInfo> searchWheelInfoAxleInspection(SearchWheelParam param) {
        return axleInspectionDao.searchWheelInfoByconditionAxleInspection(
                param.getWheelId(),
                param.getTakeInDateFrom(),
                param.getTakeInDateTo(),
                param.getAxleNumber(),param.getVehicleNumber(),
                param.getInfoTakeFinishTimeFrom(),
                param.getInfoTakeFinishTimeTo());
    }

    @Override
    public void addMagInspection(AxleInspection axleInspection) {
//        Integer count =  axleInspectionDao.findWheelIdCount(axleInspection.getWheelId());
//        if (count == 1){
//            updateMagInspection(axleInspection);
//        }else{
//            axleInspectionDao.insertMagInspection(axleInspection);
//        }
//        flushWheelInfoMagInspection(axleInspection);
        axleInspectionDao.insertMagInspection(axleInspection);
        wheelDao.finishWheelInfoMagInspection(axleInspection.getWheelId());
    }

    @Override
    public void updateMagInspection(AxleInspection axleInspection) {
        axleInspectionDao.updateMagInspection(axleInspection);
        axleInspectionDao.rollbackWheelInfoAxleInspectionFinish(axleInspection.getWheelId());
        fresh(axleInspection.getWheelId());
    }

    @Override
    public void flushWheelInfoMagInspection(AxleInspection axleInspection) {
        axleInspectionDao.flushWheelInfoMagInspectionFinish(axleInspection.getWheelId());
    }

    @Override
    public void deleteBearingMagInspection(String wheelId) {
        Integer id =Integer.parseInt(wheelId);
        axleInspectionDao.deleteMagInspectionByWheelId(id);
        axleInspectionDao.rollbackWheelInfoMagInspectionFinish(id);
        axleInspectionDao.rollbackWheelInfoAxleInspectionFinish(id);
        fresh(id);
    }

    @Override
    public List<WheelInfo> searchWheelInfoMagInspection(SearchWheelParam param) {
        return axleInspectionDao.searchWheelInfoByconditionMagInspection(
                param.getWheelId(),
                param.getTakeInDateFrom(),
                param.getTakeInDateTo(),
                param.getAxleNumber(),param.getVehicleNumber(),
                param.getInfoTakeFinishTimeFrom(),
                param.getInfoTakeFinishTimeTo());
    }

    @Override
    public void addReInspection(AxleInspection axleInspection) {
        axleInspectionDao.updateReInspection(axleInspection);
        axleInspectionDao.flushWheelInfoReInspectionFinish(axleInspection.getWheelId());
    }

    @Override
    public void updateReInspection(AxleInspection axleInspection) {

    }

    @Override
    public void flushWheelInfoReInspection(AxleInspection axleInspection) {

    }

    @Override
    public void deleteBearingReInspection(String id) {

    }

    @Override
    public List<WheelInfo> searchWheelInfoReInspection(SearchWheelParam param) {
        return null;
    }

    private void fresh(Integer id){
        wheelDao.rollbackWheelInforollTestFinish(id);
        wheelDao.rollbackWheelInfowheelRemeasureFinish(id);
        wheelDao.rollbackWheelInfowheelDispatchFinish(id);
        wheelDao.rollbackWheelInfoqualityInspectionFinish(id);
        wheelDao.rollbackWheelInfoverifyFinish(id);
    }
}
