package com.springboot.Controller;

import com.springboot.dao.BearingTestDao;
import com.springboot.dao.WheelDao;
import com.springboot.domain.*;
import com.springboot.service.BearingTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/bearingTest")
public class BearingTestController {
    @Autowired
    private BearingTestService bearingTestService;
    @Autowired
    private BearingTestDao bearingTestDao;
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private RedisTemplate redisTemplate;
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/add")
    @ResponseBody
    public Result addbearingCap(@RequestBody BearingTest bearingTest){
        bearingTest.setFinishTime(dateFormater.format(new Date()));
        bearingTestService.addBearingTest(bearingTest);
        redisTemplate.opsForSet().add("preRemeasure",bearingTest.getWheelId());
        return new Result(bearingTest,"添加成功",100);
    }

    @RequestMapping("/modify")
    @ResponseBody
    public Result modifyBearingCap(@RequestBody BearingTest bearingTest){
        bearingTest.setFinishTime(dateFormater.format(new Date()));
        bearingTestService.updateBearingTest(bearingTest);
        redisTemplate.opsForSet().add("preRemeasure",bearingTest.getWheelId());
        redisTemplate.opsForSet().remove("preQualityCheck",bearingTest.getWheelId());
        return new Result(bearingTest,"添加成功",100);
    }

    @RequestMapping("/unFinishBearingTest")
    @ResponseBody
    public Result unFinishBearingCap(){
        List<WheelInfo> wheelInfoList = bearingTestDao.findWheelInfoToBearingTest();
        return new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/unFinish")
    @ResponseBody
    public Result unFinishBearing2(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preBearingrollTest");
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
        Boolean res = redisTemplate.opsForSet().isMember("preBearingrollTest",wheelId);
        if (res){
            redisTemplate.opsForSet().remove("preBearingrollTest",wheelId);
            return new Result(null,"添加成功",100);
        }else {
            return new Result(null,"添加失败",101);
        }
    }

    @RequestMapping("/turnBack")
    @ResponseBody
    public Result turnBack(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preBearingrollTest", wheelId);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/savedWheelInfo")
    @ResponseBody
    public Result savedWheelInfo(@RequestBody Map<String,String> map){
        String name = map.get("name");
        String data = map.get("data");
        redisTemplate.opsForValue().set(name+"savedBearingrollTestInfo",data);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/getSavedWheelInfo")
    @ResponseBody
    public Result getSavedWheelInfo(String name){
        String data = (String) redisTemplate.opsForValue().get(name+"savedBearingrollTestInfo");
        return new Result(data,"添加成功",100);
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Result findBearingCapById(String id){
        BearingTest bearingTest = bearingTestDao.findBearingTestByWheelId(Integer.parseInt(id));
        return new Result(bearingTest,"添加成功",100);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteBearingCap(String id){
        bearingTestService.deleteBearingTest(id);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/searchBycondition")
    @ResponseBody
    public Result searchWheelInfoBycondition(@RequestBody SearchWheelParam param){
        List<WheelInfo> wheelInfoList = bearingTestService.searchWheelInfoTest(param);
        if (wheelInfoList==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
}
