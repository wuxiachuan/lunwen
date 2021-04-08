package com.springboot.Controller;

import com.springboot.dao.MeasureDao;
import com.springboot.dao.VehicleInfoDao;
import com.springboot.dao.WheelDao;
import com.springboot.dao.WheelDispatchDao;
import com.springboot.domain.Result;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelDispatch;
import com.springboot.domain.WheelInfo;
import com.springboot.service.WheelDispatchService;
import com.springboot.service.WheelDispatchServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/wheelReMeasure")
public class WheelReMeasureController {
    @Autowired
    private WheelDispatchService wheelDispatchService;
    @Autowired
    private WheelDispatchDao wheelDispatchDao;
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private RedisTemplate redisTemplate;
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/add")
    @ResponseBody
    public Result addWheelDispatchRemeasure(@RequestBody WheelDispatch wheelDispatch){
        wheelDispatch.setFinishTime(dateFormater.format(new Date()));
        wheelDispatchService.addWheelDispatchRemeasure(wheelDispatch);
        redisTemplate.opsForSet().add("preQualityCheck",wheelDispatch.getWheelId());
        return new Result(wheelDispatch,"添加成功",100);
    }

    @RequestMapping("/modify")
    @ResponseBody
    public Result modifyWheelDispatchRemeasure(@RequestBody WheelDispatch wheelDispatch){
        wheelDispatch.setFinishTime(dateFormater.format(new Date()));
        wheelDispatchService.updateWheelDispatchRemeasure(wheelDispatch);
        redisTemplate.opsForSet().add("preQualityCheck",wheelDispatch.getWheelId());
        return new Result(wheelDispatch,"添加成功",100);
    }

    @RequestMapping("/unFinishWheelDispatch")
    @ResponseBody
    public Result unFinishWheelDispatch(){
        List<WheelInfo> wheelInfoList = wheelDispatchDao.findWheelInfoToWheelDispatchRemeasure();
        return new Result(wheelInfoList,"添加成功",100);
    }

    @RequestMapping("/unFinish")
    @ResponseBody
    public Result unFinishBearing2(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preRemeasure");
        for (Integer id:set){
            WheelInfo wheelInfo = wheelDao.findWheelInfoById(id);
            if (wheelInfo!=null){
                wheelInfoList.add(wheelInfo);
            }
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/chooseWheel")
    @ResponseBody
    public Result chooseWheel(String id){
        Integer wheelId = Integer.parseInt(id);
        Boolean res = redisTemplate.opsForSet().isMember("preRemeasure",wheelId);
        if (res){
            redisTemplate.opsForSet().remove("preRemeasure",wheelId);
            return new Result(null,"添加成功",100);
        }else {
            return new Result(null,"添加失败",101);
        }
    }

    @RequestMapping("/turnBack")
    @ResponseBody
    public Result turnBack(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preRemeasure", wheelId);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/savedWheelInfo")
    @ResponseBody
    public Result savedWheelInfo(@RequestBody Map<String,String> map){
        String name = map.get("name");
        String data = map.get("data");
        redisTemplate.opsForValue().set(name+"savedRemeasureInfo",data);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/getSavedWheelInfo")
    @ResponseBody
    public Result getSavedWheelInfo(String name){
        String data = (String) redisTemplate.opsForValue().get(name+"savedRemeasureInfo");
        return new Result(data,"添加成功",100);
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Result findBearingCapById(String id){
        WheelDispatch wheelDispatch = wheelDispatchDao.findWheelDispatchByWheelId(Integer.parseInt(id));
        return new Result(wheelDispatch,"添加成功",100);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteBearingCap(String id){
        wheelDispatchService.deleteWheelDispatch(id);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/searchBycondition")
    @ResponseBody
    public Result searchWheelInfoBycondition(@RequestBody SearchWheelParam param){
        List<WheelInfo> wheelInfoList = wheelDispatchService.searchWheelInfoDispatch(param);
        if (wheelInfoList==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
}
