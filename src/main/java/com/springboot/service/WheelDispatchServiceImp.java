package com.springboot.service;

import com.springboot.Controller.WheelDispatchController;
import com.springboot.Controller.WheelMeasureController;
import com.springboot.dao.MeasureDao;
import com.springboot.dao.VehicleInfoDao;
import com.springboot.dao.WheelDao;
import com.springboot.dao.WheelDispatchDao;
import com.springboot.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class WheelDispatchServiceImp implements WheelDispatchService{
    @Autowired
    private WheelDispatchDao wheelDispatchDao;
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private MeasureDao measureDao;
    @Autowired
    private WheelRepository wheelRepository;
    @Autowired
    private VehicleInfoDao vehicleInfoDao;

    private SimpleDateFormat dateFormater;
    private SimpleDateFormat dateFormater2;
    private Random random ;

    public WheelDispatchServiceImp(){
        this.dateFormater = new SimpleDateFormat("yyyy-M-d");
        this.dateFormater2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.random = new Random(47);
    }
    @Override
    //添加复测
    public void addWheelDispatchRemeasure(WheelDispatch wheelDispatch) {

//        Integer count =  wheelDispatchDao.findWheelIdCount(wheelDispatch.getWheelId());
//        if (count == 1){
//            updateWheelDispatchRemeasure(wheelDispatch);
//        }else{
//            wheelDispatchDao.insertWheelDispatch(wheelDispatch);
//        }
        wheelDispatchDao.insertWheelDispatch(wheelDispatch);
        flushWheelInfoRemeasure(wheelDispatch);
    }

    @Override
    //更新复测
    public void updateWheelDispatchRemeasure(WheelDispatch wheelDispatch) {
        wheelDispatchDao.updateWheelDispatch(wheelDispatch);
        fresh(wheelDispatch.getWheelId());
    }

    @Override
    public void flushWheelInfoRemeasure(WheelDispatch wheelDispatch) {
        wheelDispatchDao.flushWheelInfowheelRemeasureFinish(wheelDispatch.getWheelId());
    }

    @Override
    public void deleteWheelDispatch(String wheelId) {
        Integer id = Integer.parseInt(wheelId);
        wheelDispatchDao.deleteWheelDispatchByWheelId(id);
        wheelDispatchDao.rollbackWheelInfoWheelRemeasureFinish(id);
        fresh(id);
    }

    @Override
    public List<WheelInfo> searchWheelInfoDispatch(SearchWheelParam param) {
        return wheelDispatchDao.searchWheelInfoByconditionWheelDispatch(param.getWheelId(),
                param.getTakeInDateFrom(),
                param.getTakeInDateTo(),
                param.getAxleNumber(),param.getVehicleNumber(),
                param.getInfoTakeFinishTimeFrom(),
                param.getInfoTakeFinishTimeTo());
    }



    public void fresh(Integer id){
        //回滚质检
        wheelDao.rollbackWheelInfoqualityInspectionFinish(id);
        //回滚验收
        wheelDao.rollbackWheelInfoverifyFinish(id);
        //回滚完工
        wheelDao.rollbackWheelInfoprocessFinish(id);
    }


    @Override
    public Boolean receiveResult(List<VehicleInfo> resultlist,String matcher) {
            String dispatchFinishTime = dateFormater2.format(new Date());
            for (VehicleInfo vec : resultlist){
                if (vec==null) continue;
                vec.setFinishTime(dispatchFinishTime);
                vec.setIsFinish("1");
                vehicleInfoDao.updateVehicleInfo(vec);
                //重设选择标记
                List<Integer> ids = new ArrayList<>();
                for (WheelDispatch des : vec.getAxleOut()){
                    if (des!=null){
                        ids.add(des.getWheelId());
                    }else {
                        throw new RuntimeException("null pointer");
                    }
                }
                synchronized (WheelDispatchController.class){
                    wheelDispatchDao.flushchooseMark(vec.getAxleOut1(),vec.getAxleOut2(),vec.getAxleOut3(),vec.getAxleOut4());
                    wheelDispatchDao.setchooseMark(ids.get(0),ids.get(1),ids.get(2),ids.get(3));
                }

                int axlepos = 0;
                 for (WheelDispatch des : vec.getAxleOut()){
                     wheelDispatchDao.updateDispatchStatus(
                            des.getWheelId(),
                            vec.getVehicleType(),
                            vec.getVehicleNumber(),
                            ++axlepos,
                            dispatchFinishTime,
                            matcher);
                    wheelDispatchDao.flushWheelInfoWheelDispatchFinish(
                            des.getWheelId(),
                            vec.getVehicleType(),
                            vec.getVehicleNumber(),
                            axlepos,
                            dispatchFinishTime);
                    wheelRepository.getAxleOutPosition(des.getWheelId().toString());
                }
            }
            return true;
    }

    @Override
    public List<VehicleInfo> dispatch(List<VehicleInfo> veclist) {
        List<VehicleInfo> res = new ArrayList<>();
        for (VehicleInfo num : veclist){
            res.add(doDispatch(num));
        }
        clearChooseMark();
        System.out.println(res);
        return res;
    }

    public List<VehicleInfo> preparVehicle2Match(List<VehicleInfo> list){
        for(VehicleInfo vehicle : list){
            String ax1 = vehicle.getAxleIn1();
            String ax2 = vehicle.getAxleIn2();
            String ax3 = vehicle.getAxleIn3();
            String ax4 = vehicle.getAxleIn4();
            List<WheelMeasure> axleIn = new ArrayList<>();
            vehicle.setAxleIn(axleIn);
            if (ax1!=null){
                vehicle.setIn1(wheelDao.findWheelInfoById(Integer.parseInt(ax1)));
                axleIn.add(measureDao.findWheelMeasureByWheelId(Integer.parseInt(ax1)));
            }else {
                vehicle.setIn1(null);
                axleIn.add(null);
            }
            if (ax2!=null){
                vehicle.setIn2(wheelDao.findWheelInfoById(Integer.parseInt(ax2)));
                axleIn.add(measureDao.findWheelMeasureByWheelId(Integer.parseInt(ax2)));
            }else {
                vehicle.setIn2(null);
                axleIn.add(null);
            }
            if (ax3!=null){
                vehicle.setIn3(wheelDao.findWheelInfoById(Integer.parseInt(ax3)));
                axleIn.add(measureDao.findWheelMeasureByWheelId(Integer.parseInt(ax3)));
            }else {
                vehicle.setIn3(null);
                axleIn.add(null);
            }
            if (ax4!=null){
                vehicle.setIn4(wheelDao.findWheelInfoById(Integer.parseInt(ax4)));
                axleIn.add(measureDao.findWheelMeasureByWheelId(Integer.parseInt(ax4)));
            }else {
                vehicle.setIn4(null);
                axleIn.add(null);
            }

            String out1 = vehicle.getAxleOut1();
            String out2 = vehicle.getAxleOut2();
            String out3 = vehicle.getAxleOut3();
            String out4 = vehicle.getAxleOut4();
            List<WheelDispatch> axleOut = new ArrayList<>();
            vehicle.setAxleOut(axleOut);
            WheelDispatch ax = null;
            if (out1!=null){
                ax = wheelDispatchDao.findWheelDispatchByWheelId(Integer.parseInt(out1));
                vehicle.setOut1(ax);
                axleOut.add(ax);
            }else {
                vehicle.setOut1(null);
                axleOut.add(null);
            }
            if (out2!=null){
                ax = wheelDispatchDao.findWheelDispatchByWheelId(Integer.parseInt(out2));
                vehicle.setOut2(ax);
                axleOut.add(ax);
            }else {
                vehicle.setOut2(null);
                axleOut.add(null);
            }
            if (out3!=null){
                ax = wheelDispatchDao.findWheelDispatchByWheelId(Integer.parseInt(out3));
                vehicle.setOut3(ax);
                axleOut.add(ax);
            }else {
                vehicle.setOut3(null);
                axleOut.add(null);
            }
            if (out4!=null){
                ax = wheelDispatchDao.findWheelDispatchByWheelId(Integer.parseInt(out4));
                vehicle.setOut4(ax);
                axleOut.add(ax);
            }else {
                vehicle.setOut4(null);
                axleOut.add(null);
            }
            setVehicleInfo(vehicle);
        }
        return list;
    }

    //匹配
    public VehicleInfo doDispatch(VehicleInfo vehicleInfo) {
        //准备车辆信息
        if (vehicleInfo==null) return null;
        preparVehicleInfo(vehicleInfo);
        //查找
        return match(vehicleInfo);
    }

    public VehicleInfo  match(VehicleInfo vehicleInfo) {
        List<WheelDispatch> axleOut = new ArrayList<>();
        WheelDispatch choose = null;
        //匹配时加锁
        synchronized (WheelDispatchController.class){
            //查找轮对，匹配轮对
            List<WheelDispatch> res = find2match(vehicleInfo);
            if (res != null && res.size() >= 4){
                int size = res.size()/2-2;
                //1位轴
                axleOut.add(choose = res.get(size));
                vehicleInfo.setOut1(choose);
                Integer id1 = choose.getWheelId();
                vehicleInfo.setAxleOut1(id1.toString());
                //2位轴
                axleOut.add(choose = res.get(size+1));
                vehicleInfo.setOut2(choose);
                Integer id2 = choose.getWheelId();
                vehicleInfo.setAxleOut2(id2.toString());
                //3位轴
                axleOut.add(choose = res.get(size+2));
                vehicleInfo.setOut3(choose);
                Integer id3 = choose.getWheelId();
                vehicleInfo.setAxleOut3(id3.toString());
                //4位轴
                axleOut.add(choose = res.get(size+3));
                vehicleInfo.setOut4(choose);
                Integer id4 = choose.getWheelId();
                vehicleInfo.setAxleOut4(id4.toString());

                vehicleInfo.setAxleOut(axleOut);
                //匹配标记
                setChooseMark(id1,id2,id3,id4);
            }else {
                vehicleInfo.setAxleOut(null);
            }
        }
        return vehicleInfo;
    }
    @Override
    public List<WheelDispatch> find2match(VehicleInfo vehicleInfo) {
        double low = vehicleInfo.getLow();
        double high = vehicleInfo.getHigh();
        String axleLife = vehicleInfo.getAxleLife();
        String bearingLife = vehicleInfo.getBearingLife();
        String axleType = vehicleInfo.getAxleType();
        List<WheelDispatch> res = findMatchedWheel(String.valueOf(low),String.valueOf(high),axleLife,bearingLife,axleType);
        return res;
    }

    //按条件查找轮对
    public List<WheelDispatch> findMatchedWheel(String low,String high,String axleLife,String bearingLife,String axleType){
        List<WheelDispatch> wheelDispatchList = null;
        wheelDispatchList = wheelDispatchDao.findTarget(low,high,axleLife,bearingLife,axleType);
        return wheelDispatchList;
    }
    //准备车辆车型、轮径、轴承寿命、轴寿命
    public void preparVehicleInfo(VehicleInfo vehicleInfo){
            flushchooseMark(vehicleInfo);
            setVehicleInfo(vehicleInfo);
    }
    //释放原来的轮对
    public void flushchooseMark(VehicleInfo vehicleInfo){
        wheelDispatchDao.flushchooseMark(
                vehicleInfo.getAxleOut1(),
                vehicleInfo.getAxleOut2(),
                vehicleInfo.getAxleOut3(),
                vehicleInfo.getAxleOut4());
    }
    //准备轮对匹配信息
    public void setVehicleInfo(VehicleInfo vehicleInfo){
        List<WheelMeasure> axleIn = vehicleInfo.getAxleIn();
        System.out.println(axleIn);
        int avgDiameter = 0;
        int sum = 0;
        int num = 0;
        for(WheelMeasure w : axleIn){
            if (w!=null){
                String wheelDiameter;
                sum += Integer.parseInt(wheelDiameter = w.getWheelDiameterLeft()==null?"800":w.getWheelDiameterLeft());
                num++;
                sum += Integer.parseInt(wheelDiameter = w.getWheelDiameterRight()==null?"800":w.getWheelDiameterRight());
                num++;
            }
        }
        if(num!=0){
            //理想轮径=平均轮径+调整高度
            avgDiameter = sum/num + vehicleInfo.getOffset();
            if (avgDiameter>850||avgDiameter<750) avgDiameter = 800;
        }else {
            avgDiameter = 800;
        }
        int low = avgDiameter - 15;
        int high = avgDiameter + 15;
        String axleLife = dateFormate(0,"axle");
        String bearingLife = dateFormate(0,"bearing");
        vehicleInfo.setAxleIn(axleIn);
        vehicleInfo.setAxleLife(axleLife);
        vehicleInfo.setBearingLife(bearingLife);
        vehicleInfo.setLow(low);
        vehicleInfo.setHigh(high);
        System.out.println(vehicleInfo);
    }
    //选中标记
    public void setChooseMark(Integer id1,Integer id2,Integer id3,Integer id4){
        wheelDispatchDao.setchooseMark(id1,id2,id3,id4);
    }
    public void clearChooseMark(){
        wheelDispatchDao.resetchooseMark();
    }
    //生成合法日期字符串
    public String dateFormate(int d,String flag){

        String[] date = dateFormater.format(new Date()).split("-");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int ty = 0;
        int tm = 0;
        if ("axle".equals(flag)){
            tm = (year-1990)*12+month-22*12+d;
        }else if("bearing".equals(flag)){
            tm = (year-1990)*12+month-6*12+d;
        }else {

        }
        ty = 1990+tm/12;
        tm = tm%12;
        String res = "";
        if (tm<10) {
            res = ty+"-0"+tm +"-01";
        }else {
            res = ty+"-"+tm +"-01";
        }
        return  res;
    }


    @Override
    //数据库内添加测试数据
    public void adddata() {
        WheelInfo wheelInfo = null;
        WheelDispatch wheelDispatch = null;
        WheelMeasure wheelMeasure = null;
        String vtype = null;
        int vnum = 4567110;
        int anum = 10040;
        int wid = 154;
        int wheelDiam = 780;
        String axlemadeDate = null;
        String bearingdate = null;

        for (int i = 0;i < 100 ; i++){
            for (int j = 1 ; j <= 4 ;j++){
                wheelInfo =  new WheelInfo();
                wheelDispatch = new WheelDispatch();
                wheelMeasure = new WheelMeasure();

                wheelInfo.setVehicleNumber(String.valueOf(vnum+i));

                wheelInfo.setWheelId(wid);
                wheelDispatch.setWheelId(wid);
                wheelMeasure.setWheelId(wid++);

                wheelInfo.setAxleNumber(String.valueOf(anum++));
                wheelInfo.setAxlePosition(j+"");

                wheelMeasure.setWheelDiameterLeft(generatewheelDiam());
                wheelMeasure.setWheelDiameterRight(generatewheelDiam());

                wheelDispatch.setAxleMadeDate(generateAxledate());
                wheelDispatch.setBearingAssembleDateLeft(generateBearingdate());
                wheelDispatch.setBearingAssembleDateRight(generateBearingdate());
                wheelDispatch.setWheelDiameterLeft(generatewheelDiam());
                wheelDispatch.setWheelDiameterRight(generatewheelDiam());

                if (i<5){
                    wheelInfo.setAxleType("RD2");
                }else {
                    wheelInfo.setAxleType("RE2B");
                }

                wheelDao.insertWheelInfo(wheelInfo);
                wheelDispatchDao.insertWheelDispatch(wheelDispatch);
                measureDao.insertWheelMeasure(wheelMeasure);
            }
        }
    }

    public String generateAxledate(){
        String axlemadeDate = null;
        int y = 1998;
        int m = 1;
        int d = 1;
        y = y + random.nextInt(22);
        m = m + random.nextInt(11);
        d = d + random.nextInt(30);
        if (m<10){
            if (d<10){
                axlemadeDate = y+"-0"+m +"-0"+d;
            }else {
                axlemadeDate = y+"-0"+m +"-"+d;
            }
        }else {
            if (d<10){
                axlemadeDate = y+"-"+ m +"-0"+d;
            }else {
                axlemadeDate = y+"-"+ m +"-"+d;
            }
        }
        return axlemadeDate;
    }

    public String generateBearingdate(){
        String axlemadeDate = null;
        int y = 2014;
        int m = 1;
        int d = 1;
        y = y + random.nextInt(6);
        m = m + random.nextInt(11);
        d = d + random.nextInt(30);
        if (m<10){
            if (d<10){
                axlemadeDate = y+"-0"+m +"-0"+d;
            }else {
                axlemadeDate = y+"-0"+m +"-"+d;
            }
        }else {
            if (d<10){
                axlemadeDate = y+"-"+ m +"-0"+d;
            }else {
                axlemadeDate = y+"-"+ m +"-"+d;
            }
        }
        return axlemadeDate;
    }
    public String generatewheelDiam(){
        String axlemadeDate = null;
        int y = 780;
        y = y + random.nextInt(40);
        axlemadeDate = y + "";
        return axlemadeDate;
    }
}
