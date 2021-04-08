package com.springboot.Controller;

import com.springboot.dao.BearingRepairDao;
import com.springboot.dao.WheelDao;
import com.springboot.domain.BearingRepair;
import com.springboot.domain.Result;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import com.springboot.service.BearingRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/bearingRepair")
public class BearingRepairController {
    @Autowired
    private BearingRepairService bearingRepairService;
    @Autowired
    private BearingRepairDao bearingRepairDao;
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private RedisTemplate redisTemplate;
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/add")
    @ResponseBody
   public Result addBearing(@RequestBody BearingRepair bearingRepair){
        bearingRepair.setFinishTime(dateFormater.format(new Date()));
        BearingRepair result = bearingRepairService.addBearingRepair(bearingRepair);
        String repairProgressLeft = bearingRepair.getRepairProgressLeft();
        String repairProgressRight = bearingRepair.getRepairProgressRight();
        if ("0".equals(repairProgressLeft)&&"0".equals(repairProgressRight)){

        }else if (!"0".equals(repairProgressLeft)&&"0".equals(repairProgressRight)){
            wheelDao.setWheelInfoisbearingUnLoadFinish(bearingRepair.getWheelId(),"2");
        }else if(!"0".equals(repairProgressRight)&&"0".equals(repairProgressLeft)){
            wheelDao.setWheelInfoisbearingUnLoadFinish(bearingRepair.getWheelId(),"3");
        }else {
            wheelDao.setWheelInfoisbearingUnLoadFinish(bearingRepair.getWheelId(),"5");
        }

        WheelInfo wheelInfo = wheelDao.findWheelInfoById(result.getWheelId());

        if("0".equals(wheelInfo.getIsbearingUnCapFinish())){
            redisTemplate.opsForSet().add("preBearingUnCap",wheelInfo.getWheelId());
        }else {
            redisTemplate.opsForSet().add("preMagInspection",wheelInfo.getWheelId());
        }
        return new Result(result,"添加成功",100);
    };

   @RequestMapping("/unFinishBearing")
   @ResponseBody
    public Result unFinishBearing(){
       List<WheelInfo> wheelInfoList = wheelDao.findWheelInfoToBearing();
       return new Result(wheelInfoList,"添加成功",100);
   }

    @RequestMapping("/unFinish")
    @ResponseBody
    public Result unFinishBearing2(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preBearingRepair");
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
        Boolean res = redisTemplate.opsForSet().isMember("preBearingRepair",wheelId);
        if (res){
            redisTemplate.opsForSet().remove("preBearingRepair",wheelId);
            return new Result(null,"添加成功",100);
        }else {
            return new Result(null,"添加失败",101);
        }
    }

    @RequestMapping("/turnBack")
    @ResponseBody
    public Result turnBack(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preBearingRepair", wheelId);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/savedWheelInfo")
    @ResponseBody
    public Result savedWheelInfo(@RequestBody Map<String,String> map){
        String name = map.get("name");
        String data = map.get("data");
        redisTemplate.opsForValue().set(name+"savedBearingRepairInfo",data);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/getSavedWheelInfo")
    @ResponseBody
    public Result getSavedWheelInfo(String name){
        String data = (String) redisTemplate.opsForValue().get(name+"savedBearingRepairInfo");
        return new Result(data,"添加成功",100);
    }


   @RequestMapping("/modify")
   @ResponseBody
   public Result modifyBearing(@RequestBody BearingRepair bearingRepair){
       bearingRepair.setFinishTime(dateFormater.format(new Date()));
       bearingRepairService.updateBearingRepair(bearingRepair);
       WheelInfo wheelInfo = wheelDao.findWheelInfoById(bearingRepair.getWheelId());
       if("0".equals(wheelInfo.getIsmagnetInspectionFinish())){
           redisTemplate.opsForSet().add("preMagInspection",wheelInfo.getWheelId());
           redisTemplate.opsForSet().remove("preAxleInspection",wheelInfo.getWheelId());
           redisTemplate.opsForSet().remove("preWheelRounding",wheelInfo.getWheelId());
           redisTemplate.opsForSet().remove("preBearingLoad",wheelInfo.getWheelId());
           redisTemplate.opsForSet().remove("preBearingrCap",wheelInfo.getWheelId());
           redisTemplate.opsForSet().remove("preBearingrollTest",wheelInfo.getWheelId());
           redisTemplate.opsForSet().remove("preRemeasure",wheelInfo.getWheelId());
           redisTemplate.opsForSet().remove("preQualityCheck",wheelInfo.getWheelId());
       }else if("0".equals(wheelInfo.getIsWheelRoundingFinish())){
           redisTemplate.opsForSet().add("preWheelRounding",wheelInfo.getWheelId());
           redisTemplate.opsForSet().remove("preBearingLoad",wheelInfo.getWheelId());
           redisTemplate.opsForSet().remove("preBearingrCap",wheelInfo.getWheelId());
           redisTemplate.opsForSet().remove("preBearingrollTest",wheelInfo.getWheelId());
           redisTemplate.opsForSet().remove("preRemeasure",wheelInfo.getWheelId());
           redisTemplate.opsForSet().remove("preQualityCheck",wheelInfo.getWheelId());
       }else if("1".equals(wheelInfo.getIsbearingLoadFinish())||"2".equals(wheelInfo.getIsbearingLoadFinish())||"3".equals(wheelInfo.getIsbearingLoadFinish())){
           redisTemplate.opsForSet().add("preBearingLoad",wheelInfo.getWheelId());
           redisTemplate.opsForSet().remove("preBearingrCap",wheelInfo.getWheelId());
           redisTemplate.opsForSet().remove("preBearingrollTest",wheelInfo.getWheelId());
           redisTemplate.opsForSet().remove("preRemeasure",wheelInfo.getWheelId());
           redisTemplate.opsForSet().remove("preQualityCheck",wheelInfo.getWheelId());
       }else {
           redisTemplate.opsForSet().add("preBearingrollTest",wheelInfo.getWheelId());
           redisTemplate.opsForSet().remove("preRemeasure",wheelInfo.getWheelId());
           redisTemplate.opsForSet().remove("preQualityCheck",wheelInfo.getWheelId());
       }
       return new Result(bearingRepair,"添加成功",100);
   }

   @RequestMapping("/findById")
    @ResponseBody
    public Result findBearingById(String id){
       BearingRepair bearingRepair = bearingRepairDao.findBearingRepairByWheelId(Integer.parseInt(id));
      if (bearingRepair == null){
          return new Result(null,"查找",101);
      }
       return new Result(bearingRepair,"添加成功",100);
   }

   @RequestMapping("/delete")
    @ResponseBody
    public Result deleteBearing(String id){
        bearingRepairService.deleteBearing(id);
        return new Result(null,"删除成功",100);
   }
    //废弃
    @RequestMapping("/flushWheelInfo")
    @ResponseBody
    public Result flushWheelInfo(String id,String index){
        return new Result(null,"添加失败",101);
    }

    @RequestMapping("/searchBycondition")
    @ResponseBody
    public Result searchWheelInfoByconditionRepair(@RequestBody SearchWheelParam param){
        List<WheelInfo> wheelInfoList = bearingRepairService.searchWheelInfoRepairCondition(param);
        if (wheelInfoList==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
}
