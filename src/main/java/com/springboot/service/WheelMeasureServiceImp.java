package com.springboot.service;

import com.springboot.dao.MeasureDao;
import com.springboot.dao.WheelDao;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import com.springboot.domain.WheelMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WheelMeasureServiceImp implements WheelMeasureService{
    @Autowired
    private MeasureDao measureDao;
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private WheelRepository wheelRepository;

    @Override
    public void addMeasure(WheelMeasure wheelMeasure) {
         measureDao.insertWheelMeasure(wheelMeasure);
         measureDao.flushWheelInfoMeasureFinish(wheelMeasure.getWheelId());
    }

    @Override
    public WheelMeasure findWheelMeasureByWheelId(Integer wheelId) {
      return measureDao.findWheelMeasureByWheelId(wheelId);
    }

    @Override
    public void updateWheelMeasure(WheelMeasure wheelMeasure) {
        measureDao.updateWheelMeasure(wheelMeasure);
        //报废轮对，解决存在多次存入问题！
        String repairProgress = wheelMeasure.getRepairProcess();
        //查找报废轮对是否已存在
        Map position = wheelRepository.getPosition(String.valueOf(wheelMeasure.getWheelId()));
        if (position != null){
            //已报废过
            if ("4".equals(repairProgress)){
                System.out.println("have discard");
            }else {
                //不再报废则取出
                wheelRepository.getDiscardAxleOutPosition(String.valueOf(wheelMeasure.getWheelId()));
            }
        }else {
            //第一次报废
            if ("4".equals(repairProgress)){
                discardWheel(wheelMeasure.getWheelId(),wheelMeasure.getDiscardReason());
            }
        }
        if ("4".equals(repairProgress)){
            wheelDao.discardWheel(wheelMeasure.getWheelId(),"3",wheelMeasure.getDiscardReason());
        }else {
            wheelDao.discardWheel(wheelMeasure.getWheelId(),"0",wheelMeasure.getDiscardReason());
        }
        fresh(wheelMeasure.getWheelId());
        flushWheelInfo(wheelMeasure);
    }

    @Override
    public void deleteWheelMeasure(String wheelId) {
        Integer id = Integer.parseInt(wheelId);
        measureDao.deleteWheelMeasure(id);
//        measureDao.rollbackWheelInfoRepairFinish(id);
//        measureDao.rollbackWheelInfoInspectionFinish(id);
//        measureDao.rollbackWheelInfoMeasureFinish(id);
//        measureDao.rollbackWheelInfoWheelRounding(id);
//        measureDao.rollbackWheelInfoMagInspectionFinish(id);
        wheelDao.robackWheelInfoMeasureFinish(id);
        fresh(id);
    }

    @Override
    public void flushWheelInfo(WheelMeasure wheelMeasure) {
        Integer wheelId = wheelMeasure.getWheelId();
        measureDao.rollbackWheelInfoInspectionFinish(wheelId);
        measureDao.rollbackWheelInfoMagInspectionFinish(wheelId);
        measureDao.rollbackWheelInfoWheelRounding(wheelId);
        String index = wheelMeasure.getRepairProcess();
        if (index.equals("1")){
            measureDao.flushWheelInfoWheelRounding(wheelId);
        }
       if (index.equals("2")){
            measureDao.flushWheelInfoInspectionFinish(wheelId);
            measureDao.flushWheelInfoMagInspectionFinish(wheelId);
       }
        if (index.equals("3")){
            measureDao.flushWheelInfoWheelRounding(wheelId);
            measureDao.flushWheelInfoInspectionFinish(wheelId);
            measureDao.flushWheelInfoMagInspectionFinish(wheelId);
        }
    }

    @Override
    public List<WheelInfo> searchWheelInfoMeasure(SearchWheelParam param) {
        return measureDao.searchWheelInfoByconditionMeasure(
                                                            param.getWheelId(),
                                                            param.getTakeInDateFrom(),
                                                            param.getTakeInDateTo(),
                                                            param.getAxleNumber(),param.getVehicleNumber(),
                                                            param.getInfoTakeFinishTimeFrom(),
                                                            param.getInfoTakeFinishTimeTo());
    }
    private void fresh(Integer id){
        wheelDao.rollbackWheelInfoRepairFinish(id);
        wheelDao.rollbackWheelInfoMagInspectionFinish(id);
        wheelDao.rollbackWheelInfoAxleInspectionFinish(id);
        wheelDao.rollbackWheelInfoWheelRoundingFinish(id);
        wheelDao.rollbackWheelInfoisbearingLoadFinish(id);
        wheelDao.rollbackWheelInfobearingCapFinish(id);
        wheelDao.rollbackWheelInforollTestFinish(id);
        wheelDao.rollbackWheelInfowheelDispatchFinish(id);
        wheelDao.rollbackWheelInfowheelRemeasureFinish(id);
        wheelDao.rollbackWheelInfoqualityInspectionFinish(id);
        wheelDao.rollbackWheelInfoverifyFinish(id);
        wheelDao.rollbackWheelInfoprocessFinish(id);
    }
    //报废轮对
    public void discardWheel(Integer wheelId,String reason){
        //修改状态
        wheelDao.discardWheel(wheelId,"3",reason);
        //存入轮场
        wheelRepository.discardAxleInPosition(String.valueOf(wheelId));
    }
}
