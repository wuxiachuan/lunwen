package com.springboot.service;

import com.github.pagehelper.PageHelper;
import com.springboot.dao.*;
import com.springboot.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ManageServiceImp implements ManageService{
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
    private ProblemDao problemDao;
    @Autowired
    private BearingUnLoadDao bearingUnLoadDao;
    private DateFormat dateFormat;

    public ManageServiceImp(){
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public List<WheelInfo> findWheelInfoByCondition(SearchWheelParam param) {
        String id = param.getWheelId();
        String takeInDateFrom = null;
        takeInDateFrom = param.getTakeInDateFrom();
        String takeInDateTo = null;
        takeInDateTo = param.getTakeInDateTo();
        String dispatchDateFrom = null;
        dispatchDateFrom = param.getDispatchDateFrom();
        String dispatchDateTo = null;
        dispatchDateTo = param.getDispatchDateTo();

        String takeInReason = param.getTakeInReason();
        String axleNumber = param.getAxleNumber();
        String axleType = param.getAxleType();
        String vehicleNumber = param.getVehicleNumber();
        String dipatchVehicleNumber = param.getDipatchVehicleNumber();
        String infoTakeFinishTimeFrom = param.getInfoTakeFinishTimeFrom();
        String infoTakeFinishTimeTo = param.getInfoTakeFinishTimeTo();
        String status = param.getStatus();
        String isprocessFinish = param.getIsprocessFinish();
        List<WheelInfo> wheelInfoList = wheelDao.findWheelInfoByCondition(
                id,
                takeInDateFrom,
                takeInDateTo,
                axleNumber,
                vehicleNumber,
                infoTakeFinishTimeFrom,
                infoTakeFinishTimeTo,
                dispatchDateFrom,
                dispatchDateTo,
                dipatchVehicleNumber,
                status,
                axleType,
                takeInReason,
                isprocessFinish,
                param.getInfoTakeFinishTime(),
                param.getDispatchDate());
        return wheelInfoList;
    }

    @Override
    public List<WheelAll> findWheelAllByCondition(SearchWheelParam param) {
        List<WheelInfo> wheelInfoList = findWheelInfoByCondition(param);
        return findWheelAllByInfoList(wheelInfoList);
    }

    @Override
    public List<WheelInfo> findWheelInfoByCondition2Check(SearchWheelParam param) {
        String id = param.getWheelId();
        String takeInDate = param.getTakeInDate();
        String dispatchDate = param.getDispatchDate();
        String takeInReason = param.getTakeInReason();
        String axleNumber = param.getAxleNumber();
        String axleType = param.getAxleType();
        String vehicleNumber = param.getVehicleNumber();
        String dipatchVehicleNumber = param.getDipatchVehicleNumber();
        String infoTakeFinishTime = param.getInfoTakeFinishTime();
        String status = param.getStatus();
        String isprocessFinish = param.getIsprocessFinish();
        List<WheelInfo> wheelInfoList = wheelDao.findWheelInfoByCondition2Check(
                param.getWheelId(),
                param.getTakeInDateFrom(),
                param.getTakeInDateTo(),
                param.getAxleNumber(),
                param.getVehicleNumber(),
                param.getInfoTakeFinishTimeFrom(),
                param.getInfoTakeFinishTimeTo(),
                param.getDispatchDateFrom(),
                param.getDispatchDateTo(),
                dipatchVehicleNumber,
                status,
                axleType,
                takeInReason,
                isprocessFinish,
                param.getInfoTakeFinishTime(),
                param.getDispatchDate());
        return wheelInfoList;
    }

    @Override
    public List<WheelAll> findWheelAllByCondition2Check(SearchWheelParam param) {
        List<WheelInfo> wheelInfoList = findWheelInfoByCondition2Check(param);
        return findWheelAllByInfoList(wheelInfoList);
    }

    @Override
    public List<WheelAll> findAllWheelAll() {
        List<WheelInfo> wheelInfoList = wheelDao.findAllWheelInfo();
        return findWheelAllByInfoList(wheelInfoList);
    }

    public List<WheelAll> findWheelAllByInfoList(List<WheelInfo> wheelInfoList){
        List<WheelAll> wheelAllList = new ArrayList<>();
        for (WheelInfo wh : wheelInfoList){
            WheelAll wheelAll = findWheelAllByWheelInfo(wh);
            if (wheelAll != null){
                wheelAllList.add(wheelAll);
            }
        }
        return wheelAllList;
    }
    public WheelAll findWheelAllByWheelInfo(WheelInfo wh){
        WheelRound wheelRound = null;
        WheelDispatch wheelDispatch = null;
        WheelMeasure wheelMeasure = null;
        AxleInspection axleInspection = null;
        WheelDispatch wheelDispatch1 = null;
        BearingTest bearingTest = null;
        BearingLoad bearingLoad = null;
        BearingRepair bearingRepair = null;
        BearingCap bearingCap = null;
        List<BearingUnLoad> bearingUnLoads = null;
        List<Problem> problem = null;

        wheelRound = findWheelRoundById(wh);
        wheelDispatch = findWheelDispatchById(wh);
        wheelMeasure = findWheelMeasureById(wh);
        axleInspection = findAxleInspectionById(wh);
        wheelDispatch1 = findWheelDispatchById(wh);
        bearingTest = findBearingTestById(wh);
        bearingLoad = findBearingLoadById(wh);
        bearingRepair = findBearingRepairById(wh);
        bearingCap = findBearingCapById(wh);
        bearingUnLoads = findBearingUnLoadById(wh);
        problem = findProblemById(wh);

        WheelAll wheelAll = new WheelAll();
        wheelAll.setAxleInspection(axleInspection);
        wheelAll.setBearingCap(bearingCap);
        wheelAll.setBearingLoad(bearingLoad);
        wheelAll.setBearingRepair(bearingRepair);
        wheelAll.setWheelDispatch(wheelDispatch);
        wheelAll.setWheelRound(wheelRound);
        wheelAll.setWheelDispatch(wheelDispatch1);
        wheelAll.setWheelMeasure(wheelMeasure);
        wheelAll.setBearingTest(bearingTest);
        wheelAll.setWheelInfo(wh);
        wheelAll.setProblem(problem);
        wheelAll.setBearingUnLoad(bearingUnLoads);
        wheelAll.setWheelId(wh.getWheelId());

        return wheelAll;
    }

    @Override
    public WheelAll findWheelAllByWheelId(Integer id) {
        WheelInfo wh= wheelDao.findWheelInfoById(id);
        return findWheelAllByWheelInfo(wh);
    }

    private WheelRound findWheelRoundById(WheelInfo wh){
        //WheelRound wheelRound = new WheelRound();
        WheelRound wheelRound = null;
       Integer id = wh.getWheelId();
       String isFinish = wh.getIsWheelRoundingFinish();
        if ("1".equals(isFinish)){
            wheelRound = wheelRoundDao.findWheelRoundByWheelId(id);
        }
        return wheelRound;
    }
    private WheelMeasure findWheelMeasureById(WheelInfo wh){
       // WheelMeasure wheelMeasure = new WheelMeasure();
        WheelMeasure wheelMeasure = null;
        Integer id = wh.getWheelId();
        String isFinish = wh.getIsMeasureFinish();
        if ("1".equals(isFinish)){
            wheelMeasure = measureDao.findWheelMeasureByWheelId(id);
        }
        return wheelMeasure;
    }
    private AxleInspection findAxleInspectionById(WheelInfo wh){
       // AxleInspection axleInspection = new AxleInspection();
        AxleInspection axleInspection = null;
        Integer id = wh.getWheelId();
        String isFinish = wh.getIsaxleInspectionFinish();
        if ("1".equals(isFinish)){
            axleInspection = axleInspectionDao.findAxleInspectionByWheelId(id);
        }
        return axleInspection;
    }
    private BearingCap findBearingCapById(WheelInfo wh){
        //BearingCap bearingCap = new BearingCap();
        BearingCap bearingCap = null;
        Integer id = wh.getWheelId();
        String isFinish = wh.getIsbearingCapFinish();
        if ("1".equals(isFinish)){
            bearingCap = bearingCapDao.findBearingCapByWheelId(id);
        }
        return bearingCap;
    }
    private BearingRepair findBearingRepairById(WheelInfo wh){
        //BearingRepair bearingRepair = new BearingRepair();
        BearingRepair bearingRepair = null;
        Integer id = wh.getWheelId();
        String isFinish = wh.getIsbearingRepairFinish();
        if ("1".equals(isFinish)){
            bearingRepair = bearingRepairDao.findBearingRepairByWheelId(id);
        }
        return bearingRepair;
    }
    private BearingLoad findBearingLoadById(WheelInfo wh){
        //BearingLoad bearingLoad = new BearingLoad();
        BearingLoad bearingLoad = null;
        Integer id = wh.getWheelId();
        String isFinish = wh.getIsbearingLoadFinish();
        if ("1".equals(isFinish)){
            bearingLoad = bearingLoadDao.findBearingLoadByWheelId(id);
        }
        return bearingLoad;
    }
    private BearingTest findBearingTestById(WheelInfo wh){
       // BearingTest bearingTest = new BearingTest();
        BearingTest bearingTest = null;
        Integer id = wh.getWheelId();
        String isFinish = wh.getIsbearingrollTestFinish();
        if ("1".equals(isFinish)){
            bearingTest = bearingTestDao.findBearingTestByWheelId(id);
        }
        return bearingTest;
    }
    private WheelDispatch findWheelDispatchById(WheelInfo wh){
        //WheelDispatch wheelDispatch = new WheelDispatch();
        WheelDispatch wheelDispatch = null;
        Integer id = wh.getWheelId();
        String isFinish = wh.getIsreMeasureFinish();
        if ("1".equals(isFinish)){
            wheelDispatch = wheelDispatchDao.findWheelDispatchByWheelId(id);
        }
        return wheelDispatch;
    }
    private List<Problem> findProblemById(WheelInfo wh){
        List<Problem> problem = null;
        Integer id = wh.getWheelId();
        problem = problemDao.findProblemByWheelId(id);
        return problem;
    }
    private List<BearingUnLoad> findBearingUnLoadById(WheelInfo wh){
        List<BearingUnLoad> bearingUnLoads = null;
        Integer id = wh.getWheelId();
        String isFinish = wh.getIsbearingUnloadFinish();
        if ("1".equals(isFinish)){
            bearingUnLoads = bearingUnLoadDao.findBearingUnLoadById(id);
        }
        return bearingUnLoads;
    }

    @Override
    public List<WheelInfo> findWheels(String name) throws ParseException {
        List<WheelInfo> list = null;
        if ("wm".equals(name)) list = findWheelMeasure();
        if ("br".equals(name)) list = findBearingRepair();
        if ("mi".equals(name)) list = findMagInspect();
        if ("ui".equals(name)) list = wheelDao.findUtrInspect();
        if ("wr".equals(name)) list = wheelDao.findWheelRound();
        if ("bl".equals(name)) list = wheelDao.findWheelLoad();
        if ("bc".equals(name)) list = findBearingCap();
        if ("bt".equals(name)) list = findBearingTest();
        if ("rm".equals(name)) list = wheelDao.findWheelRemeasure();
        if ("qc".equals(name)) list = wheelDao.findWheelQalityInspect();
        for (WheelInfo wh : list){
            appendTime(wh);
        }
        return list;
    }

    List<WheelInfo> findWheelMeasure(){
        List<WheelInfo> list = wheelDao.findWheelMeasure();
        for (WheelInfo wh : list){
            wh.setFinishTime(wh.getInfoTakeFinishTime());
        }
        return list;
    }
    List<WheelInfo> findBearingRepair(){
        List<WheelInfo> list = wheelDao.findBearingRepair();
        return list;
    }
    List<WheelInfo> findMagInspect(){
        List<WheelInfo> list = wheelDao.findMagInspect();
        return list;
    }
    List<WheelInfo> findBearingCap() {
        List<WheelInfo> list = bearingCapDao.findWheelInfoToBearingCap();
        List<WheelInfo> res = new ArrayList<>();
        System.out.println(list);
        String isBearingLoad =  null;
        for (WheelInfo wh : list){
            isBearingLoad = wh.getIsbearingLoadFinish();
            if ("-1".equals(isBearingLoad)){
                wh = wheelDao.findBearingCap2(wh.getWheelId());
             }else {
                wh = wheelDao.findBearingCap(wh.getWheelId());
            }
            res.add(wh);
        }
        System.out.println(res);
        return res;
    }
    List<WheelInfo> findBearingTest(){
        List<WheelInfo> list = bearingTestDao.findWheelInfoToBearingTest();
        List<WheelInfo> res = new ArrayList<>();
        System.out.println(list);
        String isBearingCap =  null;
        String isUrlInspect =  null;
        for (WheelInfo wh : list){
            isBearingCap = wh.getIsbearingCapFinish();
            isUrlInspect = wh.getIsaxleInspectionFinish();
            if ("1".equals(isBearingCap)){
                wh = wheelDao.findBearingTest(wh.getWheelId());
                System.out.println(wh);
            }else if ("1".equals(isUrlInspect)){
                wh = wheelDao.findBearingTest2(wh.getWheelId());
            }else {
                wh = wheelDao.findBearingTest3(wh.getWheelId());
            }
            res.add(wh);
        }
        System.out.println(res);
        return res;
    }
    public void appendTime(WheelInfo wh) throws ParseException {
        String t1 = wh.getFinishTime();
        if (t1 == null) return;
        Date date1 = dateFormat.parse(t1);
        Date date2 = new Date();
        Long date = (date2.getTime()-date1.getTime())/1000;
        int hour = (int) (date/3600);
        int minute = (int) (date%3600/60);
        int second = (int) (date%3600%60);
        String time = new String(hour+":"+minute+":"+second);
        wh.setReserve1(time);
    }
}
