package com.springboot.Controller;

import com.springboot.dao.WheelDao;
import com.springboot.domain.Result;
import com.springboot.domain.WheelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wheelManage")
public class WheelManageController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private WheelDao wheelDao;

    @RequestMapping("/getWheels")
    @ResponseBody
    public Result getWheelList(){
        Map<String,List<String>> wheelInfos = new HashMap<>();
        List<String> list = null;
        for(int i = 0;i<10;i++){
            list = redisTemplate.opsForList().range("qualified"+i,0,-1);
            wheelInfos.put("qualified"+i,list);
        }
        for(int i = 0;i<3;i++){
            list = redisTemplate.opsForList().range("discard"+i,0,-1);
            wheelInfos.put("discard"+i,list);
        }
        return  new Result(wheelInfos,"添加成功",100);
    }
    @RequestMapping("/getWheelsDetails")
    @ResponseBody
    public Result getWheelsDetails(String id){
        if (!"null".equals(id)){
            WheelInfo wheelInfo = wheelDao.findWheelInfoByIdAll(Integer.parseInt(id));
            return  new Result(wheelInfo,"添加成功",100);
        }
        return  new Result(null,"查询失败",101);
    }
}
