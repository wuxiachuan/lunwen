package com.springboot.Controller;

import com.springboot.dao.BearingLoadDao;
import com.springboot.dao.WheelDao;
import com.springboot.domain.BearingLoad;
import com.springboot.domain.Result;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import com.springboot.service.BearingLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/neckMeasure")
public class BearingNeckMeasureController {
    @Autowired
    private BearingLoadService bearingLoadService;
    @Autowired
    private BearingLoadDao bearingLoadDao;
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private RedisTemplate redisTemplate;
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/add")
    @ResponseBody
    public Result neckMeasure(@RequestBody BearingLoad bearingLoad){
        bearingLoad.setNeckMeasureFinishTime(dateFormater.format(new Date()));
        bearingLoad.setNeckMeasureWorker(bearingLoad.getWorker());
        bearingLoad.setIsneckMeasureFinish("1");
        System.out.println(bearingLoad);
        bearingLoadService.addBearingNeckMeasure(bearingLoad);
        redisTemplate.opsForSet().add("preBearingLoad",bearingLoad.getWheelId());
        return new Result(bearingLoad,"添加成功",100);
    }
    @RequestMapping("/modify")
    @ResponseBody
    public Result updateBearingLoad(@RequestBody BearingLoad bearingLoad ){
        bearingLoad.setFinishTime(dateFormater.format(new Date()));
        bearingLoadService.updateBearingLoad(bearingLoad);
        redisTemplate.opsForSet().add("preBearingrCap",bearingLoad.getWheelId());
        redisTemplate.opsForSet().remove("preBearingrollTest",bearingLoad.getWheelId());
        redisTemplate.opsForSet().remove("preRemeasure",bearingLoad.getWheelId());
        redisTemplate.opsForSet().remove("preQualityCheck",bearingLoad.getWheelId());
        return new Result(bearingLoad,"添加成功",100);
    }

    @RequestMapping("/unFinishBearingLoad")
    @ResponseBody
    public Result unFinishBearingLoad(){
        List<WheelInfo> wheelInfoList = bearingLoadDao.findWheelInfoToBearingLoad();
        return new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/unFinish")
    @ResponseBody
    public Result unFinishBearing2(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preNeckMeasure");
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
        Boolean res = redisTemplate.opsForSet().isMember("preNeckMeasure",wheelId);
        if (res){
            redisTemplate.opsForSet().remove("preNeckMeasure",wheelId);
            return new Result(null,"添加成功",100);
        }else {
            return new Result(null,"添加失败",101);
        }
    }

    @RequestMapping("/turnBack")
    @ResponseBody
    public Result turnBack(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preNeckMeasure", wheelId);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/savedWheelInfo")
    @ResponseBody
    public Result savedWheelInfo(@RequestBody Map<String,String> map){
        String name = map.get("name");
        String data = map.get("data");
        redisTemplate.opsForValue().set(name+"savedBearingLoadInfo",data);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/getSavedWheelInfo")
    @ResponseBody
    public Result getSavedWheelInfo(String name){
        String data = (String) redisTemplate.opsForValue().get(name+"savedBearingLoadInfo");
        return new Result(data,"添加成功",100);
    }


    @RequestMapping("/searchBycondition")
    @ResponseBody
    public Result searchWheelInfoBycondition(@RequestBody SearchWheelParam param){
        List<WheelInfo> wheelInfoList = bearingLoadService.searchWheelInfoBycondition(param);
        if (wheelInfoList==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }


    @RequestMapping("/findById")
    @ResponseBody
    public Result findBearingById(String id){
        BearingLoad bearingLoad = bearingLoadDao.findBearingLoadByWheelId(Integer.parseInt(id));
        if (bearingLoad == null){
            return new Result(null,"查找",101);
        }
        return new Result(bearingLoad,"添加成功",100);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteBearingLoad(String id,String index){
        bearingLoadService.deleteBearing(id,index);
        return new Result(null,"删除成功",100);
    }
    @RequestMapping("/test")
    @ResponseBody
    public Result deleteBearingLoad(){
        BearingLoad bearingLoad = new BearingLoad();
        bearingLoad.setRepairProgress("222");
        bearingLoadDao.addBearingLoad(bearingLoad);
        return new Result(bearingLoad,"删除成功",100);
    }
}
