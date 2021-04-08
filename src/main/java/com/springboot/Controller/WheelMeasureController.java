package com.springboot.Controller;

import com.springboot.dao.MeasureDao;
import com.springboot.dao.WheelDao;
import com.springboot.domain.Result;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import com.springboot.domain.WheelMeasure;
import com.springboot.service.WheelMeasureService;
import com.springboot.service.WheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/measure")
public class WheelMeasureController {
    @Autowired
    private WheelMeasureService wheelMeasureService;
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private MeasureDao measureDao;
    @Autowired
    private RedisTemplate redisTemplate;

    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/add")
    @ResponseBody
    public Result addMeasure(@RequestBody WheelMeasure wheelMeasure){
        wheelMeasure.setFinishTime(dateFormater.format(new Date()));
        String repairProcess = wheelMeasure.getRepairProcess();
        if (repairProcess.equals("0")){
            //送厂
            wheelMeasureService.discardWheel(wheelMeasure.getWheelId(),wheelMeasure.getDiscardReason());
        }else if (repairProcess.equals("1")){
            //旋面
            wheelDao.setWheelInfoWheelRoundingFinish(wheelMeasure.getWheelId());
            wheelMeasureService.addMeasure(wheelMeasure);
            redisTemplate.opsForSet().add("preBearingRepair",wheelMeasure.getWheelId());
        }else {
            wheelMeasureService.addMeasure(wheelMeasure);
            redisTemplate.opsForSet().add("preBearingRepair",wheelMeasure.getWheelId());
        }
        return new Result(wheelMeasure,"添加成功",100);
    }

    @RequestMapping("/unFinishMeasure")
    @ResponseBody
    public Result findUnFinishMeasure(){
        List<WheelInfo> wheelInfoList = wheelDao.findWheelInfoToMeasure();
        return new Result(wheelInfoList,"添加成功",100);
    }

    @RequestMapping("/unFinish")
    @ResponseBody
    public Result findUnFinishMeasure2(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preMeasure");
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
        synchronized (WheelMeasureController.class){
            Boolean res = redisTemplate.opsForSet().isMember("preMeasure",wheelId);
            if (res){
                redisTemplate.opsForSet().remove("preMeasure",wheelId);
                return new Result(null,"添加成功",100);
            }else {
                return new Result(null,"添加失败",101);
            }
        }
    }

    @RequestMapping("/turnBack")
    @ResponseBody
    public Result turnBack(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preMeasure", wheelId);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/savedWheelInfo")
    @ResponseBody
    public Result savedMeasureInfo(@RequestBody Map<String,String> map){
        String name = map.get("name");
        String data = map.get("data");
        redisTemplate.opsForValue().set(name+"savedMeasureInfo",data);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/getSavedWheelInfo")
    @ResponseBody
    public Result getSavedWheelInfoFromSession(String name){
        String data = (String) redisTemplate.opsForValue().get(name+"savedMeasureInfo");
        return new Result(data,"添加成功",100);
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Result findMeasureById(String id){
        WheelMeasure wheelMeasure = wheelMeasureService.findWheelMeasureByWheelId(Integer.parseInt(id));
        if (wheelMeasure==null){
            return new Result(null,"查找失败",101);
        }
        return new Result(wheelMeasure,"查找成功",100);
    }
    //废弃
    @RequestMapping("/flushWheelInfo")
    @ResponseBody
    public Result flushWheelInfo(String id,String index){
        return new Result(null,"添加失败",101);
    }

    @RequestMapping("/modify")
    @ResponseBody
    public Result modifyMeasure(@RequestBody WheelMeasure wheelMeasure){
        wheelMeasure.setFinishTime(dateFormater.format(new Date()));
        wheelMeasureService.updateWheelMeasure(wheelMeasure);
        if (!"4".equals(wheelMeasure.getRepairProcess())){
            redisTemplate.opsForSet().add("preBearingRepair",wheelMeasure.getWheelId());
            redisTemplate.opsForSet().remove("preMagInspection",wheelMeasure.getWheelId());
            redisTemplate.opsForSet().remove("preAxleInspection",wheelMeasure.getWheelId());
            redisTemplate.opsForSet().remove("preWheelRounding",wheelMeasure.getWheelId());
            redisTemplate.opsForSet().remove("preBearingLoad",wheelMeasure.getWheelId());
            redisTemplate.opsForSet().remove("preBearingrCap",wheelMeasure.getWheelId());
            redisTemplate.opsForSet().remove("preBearingrollTest",wheelMeasure.getWheelId());
            redisTemplate.opsForSet().remove("preRemeasure",wheelMeasure.getWheelId());
            redisTemplate.opsForSet().remove("preQualityCheck",wheelMeasure.getWheelId());
        }
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteMeasure(String id){
        wheelMeasureService.deleteWheelMeasure(id);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/searchBycondition")
    @ResponseBody
    public Result searchWheelInfoBycondition(@RequestBody SearchWheelParam param){
        List<WheelInfo> wheelInfoList = wheelMeasureService.searchWheelInfoMeasure(param);
        if (wheelInfoList==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
}
