package com.springboot.service;

import com.springboot.dao.QRcodeDao;
import com.springboot.dao.WheelDao;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import com.springboot.qrcode.QRCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WheelServiceImp implements WheelService{
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private QRcodeDao qRcodeDao;
    @Override
    public WheelInfo insertWheelInfo(WheelInfo wheelInfo) {
        wheelDao.insertWheelInfo(wheelInfo);
        return wheelInfo;
    }

    @Override
    public List<WheelInfo> findWheelInfo(SearchWheelParam param) {
        return wheelDao.findWheelInfo(  param.getWheelId(),
                                        param.getTakeInDateFrom(),
                                        param.getTakeInDateTo(),
                                        param.getAxleNumber(),
                                        param.getVehicleNumber(),
                                        param.getInfoTakeFinishTimeFrom(),
                                        param.getInfoTakeFinishTimeTo());
    }

    @Override
    public WheelInfo updateWheelInfo(WheelInfo wheelInfo) {
        wheelDao.updateWheelInfo(wheelInfo);
        return wheelInfo;
    }

    @Override
    public void deleteWheelInfo(String id) {
        wheelDao.deleteWheelInfo(Integer.parseInt(id));
    }

    @Override
    public String generateQRcode(String wheelId) throws Exception {
        // 存放在二维码中的内容
        String text = wheelId;
        //二维码名称
        String name = "QR"+wheelId+".jpg";
        // 生成的二维码的路径及名称
        String destPath = "F:/wheelqrcode/"+name;
        //生成二维码
        QRCodeUtil.encode(text,null, destPath, true);
        //插入数据库
        qRcodeDao.insertQRcode(Integer.parseInt(wheelId),name,destPath);
        return name;
    }
}
