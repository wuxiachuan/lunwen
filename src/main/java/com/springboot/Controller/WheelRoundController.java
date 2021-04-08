package com.springboot.Controller;

import com.springboot.dao.WheelDao;
import com.springboot.dao.WheelRoundDao;
import com.springboot.domain.*;
import com.springboot.service.WheelMeasureService;
import com.springboot.service.WheelRoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/wheelRound")
public class WheelRoundController {
    @Autowired
    private WheelMeasureService wheelMeasureService;
    @Autowired
    private WheelRoundDao wheelRoundDao;
    @Autowired
    private WheelRoundService wheelRoundService;
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private RedisTemplate redisTemplate;
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/add")
    @ResponseBody
    public Result addbearingCap(@RequestBody WheelRound wheelRound){
        wheelRound.setFinishTime(dateFormater.format(new Date()));
        String repairProcess = wheelRound.getRepairProcess();
        if (repairProcess.equals("1")){
            //送厂
            wheelMeasureService.discardWheel(wheelRound.getWheelId(),wheelRound.getDiscardReason());
            return new Result(wheelRound,"添加成功",100);
        }
        wheelRoundService.addWheelRound(wheelRound);
        WheelInfo wheelInfo = wheelDao.findWheelInfoById(wheelRound.getWheelId());
        if("2".equals(wheelInfo.getIsbearingLoadFinish())||"3".equals(wheelInfo.getIsbearingLoadFinish())||"5".equals(wheelInfo.getIsbearingLoadFinish())){
            redisTemplate.opsForSet().add("preNeckMeasure",wheelInfo.getWheelId());
        }else {
            redisTemplate.opsForSet().add("preBearingrCap",wheelInfo.getWheelId());
        }
        return new Result(wheelRound,"添加成功",100);
    }

    @RequestMapping("/modify")
    @ResponseBody
    public Result modifyBearingCap(@RequestBody WheelRound wheelRound){
        wheelRound.setFinishTime(dateFormater.format(new Date()));
        wheelRoundService.updateWheelRound(wheelRound);
        WheelInfo wheelInfo = wheelDao.findWheelInfoById(wheelRound.getWheelId());
        if("1".equals(wheelInfo.getIsbearingLoadFinish())||"2".equals(wheelInfo.getIsbearingLoadFinish())||"3".equals(wheelInfo.getIsbearingLoadFinish())){
            redisTemplate.opsForSet().add("preBearingLoad",wheelInfo.getWheelId());
            redisTemplate.opsForSet().remove("preBearingrCap",wheelInfo.getWheelId());
            redisTemplate.opsForSet().remove("preBearingrollTest",wheelInfo.getWheelId());
            redisTemplate.opsForSet().remove("preRemeasure",wheelInfo.getWheelId());
            redisTemplate.opsForSet().remove("preQualityCheck",wheelInfo.getWheelId());
        }else {
            redisTemplate.opsForSet().add("preBearingrCap",wheelInfo.getWheelId());
            redisTemplate.opsForSet().remove("preBearingrollTest",wheelInfo.getWheelId());
            redisTemplate.opsForSet().remove("preRemeasure",wheelInfo.getWheelId());
            redisTemplate.opsForSet().remove("preQualityCheck",wheelInfo.getWheelId());
        }
        return new Result(wheelRound,"添加成功",100);
    }

    @RequestMapping("/unFinishWheelRound")
    @ResponseBody
    public Result unFinishBearingCap(){
        List<WheelInfo> wheelInfoList = wheelRoundDao.findWheelInfoToWheelRound();
        return new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/unFinish")
    @ResponseBody
    public Result unFinishBearing2(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preWheelRounding");
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
        Boolean res = redisTemplate.opsForSet().isMember("preWheelRounding",wheelId);
        if (res){
            redisTemplate.opsForSet().remove("preWheelRounding",wheelId);
            return new Result(null,"添加成功",100);
        }else {
            return new Result(null,"添加失败",101);
        }
    }

    @RequestMapping("/turnBack")
    @ResponseBody
    public Result turnBack(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preWheelRounding", wheelId);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/savedWheelInfo")
    @ResponseBody
    public Result savedWheelInfo(@RequestBody Map<String,String> map){
        String name = map.get("name");
        String data = map.get("data");
        redisTemplate.opsForValue().set(name+"savedWheelRoundingInfo",data);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/getSavedWheelInfo")
    @ResponseBody
    public Result getSavedWheelInfo(String name){
        String data = (String) redisTemplate.opsForValue().get(name+"savedWheelRoundingInfo");
        return new Result(data,"添加成功",100);
    }


    @RequestMapping("/findById")
    @ResponseBody
    public Result findWheelRoundById(String id){
        WheelRound wheelRound = wheelRoundDao.findWheelRoundByWheelId(Integer.parseInt(id));
        return new Result(wheelRound,"添加成功",100);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteBearingCap(String id){
        wheelRoundService.deleteWheelRound(id);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/searchBycondition")
    @ResponseBody
    public Result searchWheelInfoBycondition(@RequestBody SearchWheelParam param){
        List<WheelInfo> wheelInfoList = wheelRoundService.searchWheelInfoWheelRound(param);
        if (wheelInfoList==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }

    @RequestMapping("/getoriginwheelround")
    @ResponseBody
    public Result getoriginwheelround(String id){
        System.out.println(id);
        WheelMeasure wheelMeasure = wheelRoundDao.getoriginwheelround(Integer.parseInt(id));
        if (wheelMeasure==null){
            return new Result(new WheelMeasure(),"添加失败",101);
        }
        return new Result(wheelMeasure,"添加成功",100);
    }
}
