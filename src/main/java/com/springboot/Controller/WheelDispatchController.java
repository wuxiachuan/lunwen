package com.springboot.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.dao.*;
import com.springboot.domain.*;
import com.springboot.service.BearingTestService;
import com.springboot.service.WheelDispatchService;
import com.springboot.service.WheelDispatchServiceImp;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/wheelDispatch")
public class WheelDispatchController {
    @Autowired
    private WheelDispatchService wheelDispatchService;
    @Autowired
    private WheelDispatchDao wheelDispatchDao;
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private MeasureDao measureDao;
    @Autowired
    private VehicleInfoDao vehicleInfoDao;
    @Autowired
    private WheelDispatchServiceImp wheelDispatchServiceImp;
    @Autowired
    private RedisTemplate redisTemplate;
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/dispatch")
    @ResponseBody
    public Result dodispatch(@RequestBody List<VehicleInfo> numlist){
        List<VehicleInfo> res = wheelDispatchService.dispatch(numlist);
        return new Result(res,"添加成功",100);
    }

    @RequestMapping("/getvehicleNum")
    @ResponseBody
    public Result getvehicleNum(@RequestBody Map<String,String> map){
        String vnum = map.get("vehicleNum");
        String takeInDateFrom = map.get("takeInDateFrom");
        String takeInDateTo = map.get("takeInDateTo");
        List<VehicleInfo> list = vehicleInfoDao.findVehicleInfoByCondition("",vnum,"",takeInDateFrom,takeInDateTo,"","","");
        return new Result(list,"添加成功",100);
    }

    @RequestMapping("/find2match")
    @ResponseBody
    public Result find2match(@RequestBody VehicleInfo vehicleInfo){
        List<WheelDispatch> list = wheelDispatchService.find2match(vehicleInfo);
        return new Result(list,"添加成功",100);
    }

    @RequestMapping("/findVehicle2match")
    @ResponseBody
    public Result findVehicle2match(@RequestBody Map<String,String> map,String page,String size) {
        String id = map.get("Id");
        String vehicleNumber = map.get("vehicleNumber");
        String vehicleType = map.get("vehicleType");
        String repairDate = map.get("RepairDate");
        PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(size));
        List<VehicleInfo> list = vehicleInfoDao.findVehicleToMatch(id,vehicleNumber,vehicleType,repairDate);
        PageInfo res = new PageInfo(wheelDispatchServiceImp.preparVehicle2Match(list));
        return new Result(res,"添加成功",100);
    }

    @RequestMapping("/receiveResult")
    @ResponseBody
    public Result receiveResult(@RequestBody List<VehicleInfo> resultlist,String matcher){
        System.out.println(resultlist);
        System.out.println(matcher);
        if (resultlist==null) return new Result(null,"添加失败",101);
        Boolean res = wheelDispatchService.receiveResult(resultlist,matcher);
        if (res){
            return new Result(null,"添加成功",100);
        }
        return new Result(null,"添加失败",101);
    }

    @RequestMapping("/adddata")
    @ResponseBody
    public Result adddata(){
        wheelDispatchService.adddata();
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/wheels")
    @ResponseBody
    public Result findwheels(@RequestBody Map<String,String> map){
        String axleNumber = map.get("axleNumber");
        String axleType = map.get("axleType");
        String axleMaterial = map.get("axleMaterial");
        String axleMadeIn = map.get("axleMadeIn");
        String wheelDiameterLow = map.get("wheelDiameterLow");
        String wheelDiameterHigh = map.get("wheelDiameterHigh");
        String bearingAssembleDateLeft = map.get("bearingAssembleDateLeft");
        String bearingAssembleDateRight = map.get("bearingAssembleDateRight");
        String axleMadeDate = map.get("axleMadeDate");
        String takeInDate = map.get("takeInDate");
        List<WheelDispatch> list = null;
        list = wheelDispatchDao.find2match(
                axleNumber,
                axleType,
                axleMaterial,
                axleMadeIn,
                wheelDiameterLow,
                wheelDiameterHigh,
                bearingAssembleDateLeft,
                bearingAssembleDateRight,
                axleMadeDate,
                takeInDate);
        System.out.println(map);
        System.out.println(list);
        return new Result(list,"添加成功",100);
    }
}
