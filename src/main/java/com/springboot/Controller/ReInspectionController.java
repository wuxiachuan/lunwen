package com.springboot.Controller;

import com.springboot.dao.AxleInspectionDao;
import com.springboot.dao.WheelDao;
import com.springboot.domain.AxleInspection;
import com.springboot.domain.Result;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import com.springboot.service.AxleInspectionService;
import com.springboot.service.WheelMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/reInspection")
public class ReInspectionController {
    @Autowired
    private WheelMeasureService wheelMeasureService;
    @Autowired
    private AxleInspectionDao axleInspectionDao;
    @Autowired
    private AxleInspectionService axleInspectionService;
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private RedisTemplate redisTemplate;
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/add")
    @ResponseBody
    public Result reInspection(@RequestBody AxleInspection axleInspection){
        axleInspection.setReultfinishTime(dateFormater.format(new Date()));
        String repairProcess = axleInspection.getRepairProcess();
        if (repairProcess.equals("1")){
            //送厂
            wheelMeasureService.discardWheel(axleInspection.getWheelId(),axleInspection.getDiscardReason());
            return new Result(axleInspection,"添加成功",100);
        }
        axleInspectionService.addReInspection(axleInspection);
        WheelInfo wheelInfo = wheelDao.findWheelInfoById(axleInspection.getWheelId());
        if("0".equals(wheelInfo.getIsWheelRoundingFinish())){
            redisTemplate.opsForSet().add("preWheelRounding",wheelInfo.getWheelId());
        }else if("2".equals(wheelInfo.getIsbearingLoadFinish())
                ||"3".equals(wheelInfo.getIsbearingLoadFinish())
                ||"5".equals(wheelInfo.getIsbearingLoadFinish())){
            redisTemplate.opsForSet().add("preNeckMeasure",wheelInfo.getWheelId());
        }else if("0".equals(wheelInfo.getIsbearingCapFinish())){
            redisTemplate.opsForSet().add("preBearingrCap",wheelInfo.getWheelId());
        }else {
            redisTemplate.opsForSet().add("preBearingrollTest",wheelInfo.getWheelId());
        }
        return new Result(axleInspection,"添加成功",100);
    }

    @RequestMapping("/modify")
    @ResponseBody
    public Result modifyAxleInspection(@RequestBody AxleInspection axleInspection){
        axleInspection.setUltfinishTime(dateFormater.format(new Date()));
        axleInspection.setReultfinishTime(dateFormater.format(new Date()));
        axleInspectionService.updateAxleInspection(axleInspection);
        WheelInfo wheelInfo = wheelDao.findWheelInfoById(axleInspection.getWheelId());
        if("0".equals(wheelInfo.getIsWheelRoundingFinish())){
            redisTemplate.opsForSet().add("preWheelRounding",wheelInfo.getWheelId());
            redisTemplate.opsForSet().remove("preBearingLoad",axleInspection.getWheelId());
            redisTemplate.opsForSet().remove("preBearingrCap",axleInspection.getWheelId());
            redisTemplate.opsForSet().remove("preBearingrollTest",axleInspection.getWheelId());
            redisTemplate.opsForSet().remove("preRemeasure",axleInspection.getWheelId());
            redisTemplate.opsForSet().remove("preQualityCheck",axleInspection.getWheelId());
        }else if("1".equals(wheelInfo.getIsbearingLoadFinish())||"2".equals(wheelInfo.getIsbearingLoadFinish())||"3".equals(wheelInfo.getIsbearingLoadFinish())){
            redisTemplate.opsForSet().add("preBearingLoad",wheelInfo.getWheelId());
            redisTemplate.opsForSet().remove("preBearingrCap",axleInspection.getWheelId());
            redisTemplate.opsForSet().remove("preBearingrollTest",axleInspection.getWheelId());
            redisTemplate.opsForSet().remove("preRemeasure",axleInspection.getWheelId());
            redisTemplate.opsForSet().remove("preQualityCheck",axleInspection.getWheelId());
        }else {
            redisTemplate.opsForSet().add("preBearingrollTest",wheelInfo.getWheelId());
            redisTemplate.opsForSet().remove("preRemeasure",axleInspection.getWheelId());
            redisTemplate.opsForSet().remove("preQualityCheck",axleInspection.getWheelId());
        }
        return new Result(axleInspection,"添加成功",100);
    }

    @RequestMapping("/unFinishAxleInspection")
    @ResponseBody
    public Result unFinishAxleInspection(){
        List<WheelInfo> wheelInfoList = axleInspectionDao.findWheelInfoToAxleInspection();
        return new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/unFinish")
    @ResponseBody
    public Result unFinishAxleInspection2(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preReInspection");
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
    public Result chooseWheelA(String id){
        Integer wheelId = Integer.parseInt(id);
        Boolean res = redisTemplate.opsForSet().isMember("preReInspection",wheelId);
        if (res){
            redisTemplate.opsForSet().remove("preReInspection",wheelId);
            return new Result(null,"添加成功",100);
        }else {
            return new Result(null,"添加失败",101);
        }
    }

    @RequestMapping("/turnBack")
    @ResponseBody
    public Result turnBackA(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preReInspection", wheelId);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/savedWheelInfo")
    @ResponseBody
    public Result savedWheelInfoA(@RequestBody Map<String,String> map){
        String name = map.get("name");
        String data = map.get("data");
        redisTemplate.opsForValue().set(name+"savedAxleInspectionInfo",data);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/getSavedWheelInfo")
    @ResponseBody
    public Result getSavedWheelInfoA(String name){
        String data = (String) redisTemplate.opsForValue().get(name+"savedAxleInspectionInfo");
        return new Result(data,"添加成功",100);
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Result findAxleInspectionById(String id){
        AxleInspection axleInspection = axleInspectionDao.findAxleInspectionByWheelId(Integer.parseInt(id));
        return new Result(axleInspection,"添加成功",100);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteAxleInspection(String id){
        axleInspectionService.deleteBearingAxleInspection(id);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/searchBycondition")
    @ResponseBody
    public Result searchWheelInfoBycondition(@RequestBody SearchWheelParam param){
        List<WheelInfo> wheelInfoList = axleInspectionService.searchWheelInfoAxleInspection(param);
        if (wheelInfoList==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
}
