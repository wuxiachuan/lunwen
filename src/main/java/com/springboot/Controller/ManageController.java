package com.springboot.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.domain.Result;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelAll;
import com.springboot.domain.WheelInfo;
import com.springboot.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    private ManageService manageService;

    @RequestMapping("/query")
    @ResponseBody
    public Result findInfo(@RequestBody SearchWheelParam param){
        Integer page = Integer.parseInt(param.getPage());
        Integer size = Integer.parseInt(param.getSize());
        List<WheelInfo> data = null;
        PageHelper.startPage(page,size);
        data = manageService.findWheelInfoByCondition(param);
        PageInfo result = new PageInfo(data);
        return  new Result(result,"查询成功",100);
    }

    @RequestMapping("/queryWithOutPage")
    @ResponseBody
    public Result findInfoWithOutPage(@RequestBody SearchWheelParam param){
        List<WheelInfo> data = manageService.findWheelInfoByCondition(param);
        return  new Result(data,"查询成功",100);
    }

    @RequestMapping("/queryAll")
    @ResponseBody
   public Result findInfoAll(@RequestBody WheelInfo wh){
        WheelAll data = manageService.findWheelAllByWheelInfo(wh);
        return  new Result(data,"查询成功",100);
    }
    @RequestMapping("/getInfo2check")
    @ResponseBody
    public Result getInfo2check(@RequestBody SearchWheelParam param){
        Integer page = Integer.parseInt(param.getPage());
        Integer size = Integer.parseInt(param.getSize());
        List<WheelInfo> data = null;
        PageHelper.startPage(page,size);
        data = manageService.findWheelInfoByCondition2Check(param);
        PageInfo result = new PageInfo(data);
        return  new Result(result,"查询成功",100);
    }

    @RequestMapping("/getWheelList")
    @ResponseBody
    public Result getWheelList(String name){
        List<WheelInfo> data = null;
        try {
            data = manageService.findWheels(name);
        } catch (ParseException e) {
            e.printStackTrace();
            return  new Result(null,"添加失败！",101);
        }
        return  new Result(data,"添加成功",100);
    }

    @RequestMapping("/test")
    @ResponseBody
    public Result findINfo(Integer id){
        WheelAll data = manageService.findWheelAllByWheelId(id);
        return  new Result(data,"添加成功！",100);
    }
}
