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
@RequestMapping("/magInspection")
public class MagInspectionController {
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

    //磁探
    @RequestMapping("/add")
    @ResponseBody
    public Result addMagInspection(@RequestBody AxleInspection axleInspection){
        axleInspection.setMagfinishTime(dateFormater.format(new Date()));
        String repairProcess = axleInspection.getRepairProcess();
        if (repairProcess.equals("1")){
            //送厂
            wheelMeasureService.discardWheel(axleInspection.getWheelId(),axleInspection.getDiscardReason());
        }else{
            axleInspectionService.addMagInspection(axleInspection);
            WheelInfo wheelInfo = wheelDao.findWheelInfoById(axleInspection.getWheelId());
            if ("0".equals(wheelInfo.getIsaxleInspectionFinish())){
                redisTemplate.opsForSet().add("preAxleInspection",axleInspection.getWheelId());
            }else if("0".equals(wheelInfo.getIsWheelRoundingFinish())){
                redisTemplate.opsForSet().add("preWheelRounding",axleInspection.getWheelId());
            }else if ("2".equals(wheelInfo.getIsbearingLoadFinish())
                    ||"3".equals(wheelInfo.getIsbearingLoadFinish())
                    ||"5".equals(wheelInfo.getIsbearingLoadFinish())){
                redisTemplate.opsForSet().add("preNeckMeasure",axleInspection.getWheelId());
            }else if("0".equals(wheelInfo.getIsbearingCapFinish())){
                redisTemplate.opsForSet().add("preBearingrCap",wheelInfo.getWheelId());
            }else {
                redisTemplate.opsForSet().add("preBearingrollTest",axleInspection.getWheelId());
            }

        }
        return new Result(axleInspection,"添加成功",100);
    }

    @RequestMapping("/modify")
    @ResponseBody
    public Result modifyMagInspection(@RequestBody AxleInspection axleInspection){
        axleInspection.setMagfinishTime(dateFormater.format(new Date()));
        axleInspectionService.updateMagInspection(axleInspection);
        redisTemplate.opsForSet().add("preAxleInspection",axleInspection.getWheelId());
        redisTemplate.opsForSet().remove("preWheelRounding",axleInspection.getWheelId());
        redisTemplate.opsForSet().remove("preBearingLoad",axleInspection.getWheelId());
        redisTemplate.opsForSet().remove("preBearingrCap",axleInspection.getWheelId());
        redisTemplate.opsForSet().remove("preBearingrollTest",axleInspection.getWheelId());
        redisTemplate.opsForSet().remove("preRemeasure",axleInspection.getWheelId());
        redisTemplate.opsForSet().remove("preQualityCheck",axleInspection.getWheelId());
        return new Result(axleInspection,"添加成功",100);
    }

    @RequestMapping("/unFinishMagInspection")
    @ResponseBody
    public Result unFinishMagInspection(){
        List<WheelInfo> wheelInfoList = axleInspectionDao.findWheelInfoToMagInspection();
        return new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/unFinish")
    @ResponseBody
    public Result unFinishMagInspection2(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preMagInspection");
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
    public Result chooseWheelM(String id){
        Integer wheelId = Integer.parseInt(id);
        Boolean res = redisTemplate.opsForSet().isMember("preMagInspection",wheelId);
        if (res){
            redisTemplate.opsForSet().remove("preMagInspection",wheelId);
            return new Result(null,"添加成功",100);
        }else {
            return new Result(null,"添加失败",101);
        }
    }

    @RequestMapping("/turnBack")
    @ResponseBody
    public Result turnBackM(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preMagInspection", wheelId);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/savedWheelInfo")
    @ResponseBody
    public Result savedWheelInfoM(@RequestBody Map<String,String> map){
        String name = map.get("name");
        String data = map.get("data");
        redisTemplate.opsForValue().set(name+"savedMagInspectionInfo",data);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/getSavedWheelInfo")
    @ResponseBody
    public Result getSavedWheelInfoM(String name){
        String data = (String) redisTemplate.opsForValue().get(name+"savedMagInspectionInfo");
        return new Result(data,"添加成功",100);
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Result findMagInspectionById(String id){
        AxleInspection axleInspection = axleInspectionDao.findMagInspectionByWheelId(Integer.parseInt(id));
        return new Result(axleInspection,"添加成功",100);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteMagInspection(String id){
        axleInspectionService.deleteBearingMagInspection(id);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/searchBycondition")
    @ResponseBody
    public Result searchWheelInfoByconditionMagInspection(@RequestBody SearchWheelParam param){
        List<WheelInfo> wheelInfoList = axleInspectionService.searchWheelInfoMagInspection(param);
        if (wheelInfoList==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }

}
