package com.springboot.Controller;

import com.springboot.dao.BearingCapDao;
import com.springboot.dao.WheelDao;
import com.springboot.domain.BearingCap;
import com.springboot.domain.Result;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import com.springboot.service.BearingCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/bearingCap")
public class BearingCapController {
    @Autowired
    private BearingCapService bearingCapService;
    @Autowired
    private BearingCapDao bearingCapDao;
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private RedisTemplate redisTemplate;
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/add")
    @ResponseBody
    public Result addbearingCap(@RequestBody BearingCap bearingCap){
        bearingCap.setFinishTime(dateFormater.format(new Date()));
        bearingCapService.addBearingCap(bearingCap);
        redisTemplate.opsForSet().add("preBearingrollTest",bearingCap.getWheelId());
        return new Result(bearingCap,"添加成功",100);
    }

    @RequestMapping("/modify")
    @ResponseBody
    public Result modifyBearingCap(@RequestBody BearingCap bearingCap){
        bearingCap.setFinishTime(dateFormater.format(new Date()));
        bearingCapService.updateBearingCap(bearingCap);
        redisTemplate.opsForSet().add("preBearingrollTest",bearingCap.getWheelId());
        redisTemplate.opsForSet().remove("preRemeasure",bearingCap.getWheelId());
        redisTemplate.opsForSet().remove("preQualityCheck",bearingCap.getWheelId());
        return new Result(bearingCap,"添加成功",100);
    }

    @RequestMapping("/unFinishBearingCap")
    @ResponseBody
    public Result unFinishBearingCap(){
        List<WheelInfo> wheelInfoList = bearingCapDao.findWheelInfoToBearingCap();
        return new Result(wheelInfoList,"添加成功",100);
    }

    @RequestMapping("/unFinish")
    @ResponseBody
    public Result unFinishBearing2(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preBearingrCap");
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
        Boolean res = redisTemplate.opsForSet().isMember("preBearingrCap",wheelId);
        if (res){
            redisTemplate.opsForSet().remove("preBearingrCap",wheelId);
            return new Result(null,"添加成功",100);
        }else {
            return new Result(null,"添加失败",101);
        }
    }

    @RequestMapping("/turnBack")
    @ResponseBody
    public Result turnBack(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preBearingrCap", wheelId);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/savedWheelInfo")
    @ResponseBody
    public Result savedWheelInfo(@RequestBody Map<String,String> map){
        String name = map.get("name");
        String data = map.get("data");
        redisTemplate.opsForValue().set(name+"savedBearingrCapInfo",data);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/getSavedWheelInfo")
    @ResponseBody
    public Result getSavedWheelInfo(String name){
        String data = (String) redisTemplate.opsForValue().get(name+"savedBearingrCapInfo");
        return new Result(data,"添加成功",100);
    }


    @RequestMapping("/findById")
    @ResponseBody
    public Result findBearingCapById(String id){
        BearingCap bearingCap = bearingCapDao.findBearingCapByWheelId(Integer.parseInt(id));
        return new Result(bearingCap,"添加成功",100);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteBearingCap(String id){
        bearingCapService.deleteBearingCap(id);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/searchBycondition")
    @ResponseBody
    public Result searchWheelInfoBycondition(@RequestBody SearchWheelParam param){
        List<WheelInfo> wheelInfoList = bearingCapService.searchWheelInfoBycondition(param);
        if (wheelInfoList==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
}
