package com.springboot.Controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.dao.ProblemDao;
import com.springboot.dao.WheelDao;
import com.springboot.dao.WheelDispatchDao;
import com.springboot.domain.*;
import com.springboot.service.ManageService;
import com.springboot.service.ProblemService;
import com.springboot.service.QualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/quality")
public class QualityController {
    @Autowired
    private QualityService qualityService;
    @Autowired
    private ProblemService problemService;
    @Autowired
    private ProblemDao problemDao;
    @Autowired
    private WheelDispatchDao wheelDispatchDao;
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private ManageService manageService;

    private SimpleDateFormat dateFormater;

    public QualityController(){
       this.dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    @RequestMapping("/modify")
    @ResponseBody
    public Result modify(@RequestBody Map<String,Object> map){
        String database = (String) map.get("database");
        Object data = map.get("data");
        System.out.println(database);
        qualityService.modifyInfo(database,data);
        return  new Result(null,"添加成功",100);
    }
    @RequestMapping("/addProblem")
    @ResponseBody
    public Result addProblem(@RequestBody Problem problem){
        Integer wheelid = problem.getWheelId();
        String process = problem.getProcessBelong();
        WheelInfo wh = wheelDao.findWheelInfoById(wheelid);
        if (wh == null) return new Result(problem,"添加失败,轮对已完工!",101);
        WheelAll result = manageService.findWheelAllByWheelInfo(wh);
        String worker = null;
        if ("0".equals(process)){
            if (result.getWheelInfo() != null) worker = result.getWheelInfo().getWorker();
        }
        if ("1".equals(process)){
            if (result.getWheelMeasure() != null) worker = result.getWheelMeasure().getworker();
        }
        if ("2".equals(process)){
            if (result.getBearingRepair() != null) worker = result.getBearingRepair().getworker();
        }
        if ("3".equals(process)){
            if (result.getBearingRepair() != null) worker = result.getBearingRepair().getUnCapperLeft();
        }
        if ("4".equals(process)){
            if (result.getBearingRepair() != null) worker = result.getBearingRepair().getUnloaderLeft();
        }
        if ("5".equals(process)){
            if (result.getAxleInspection() != null) worker = result.getAxleInspection().getMagInspector();
        }
        if ("6".equals(process)){
            if (result.getAxleInspection() != null) worker = result.getAxleInspection().getWorker();
        }
        if ("7".equals(process)){
            if (result.getAxleInspection() != null) worker = result.getAxleInspection().getReultInspector();
        }
        if ("8".equals(process)){
            if (result.getWheelRound() != null) worker = result.getWheelRound().getWorker();
        }
        if ("9".equals(process)){
            if (result.getBearingLoad() != null) worker = result.getBearingLoad().getNeckMeasureWorker();
        }
        if ("10".equals(process)){
            if (result.getBearingLoad() != null) worker = result.getBearingLoad().getWorker();
        }
        if ("11".equals(process)){
            if (result.getBearingCap() != null) worker = result.getBearingCap().getworker();
        }
        if ("12".equals(process)){
            if (result.getBearingTest() != null) worker = result.getBearingTest().getworker();
        }
        if ("13".equals(process)){
            if (result.getWheelDispatch() != null) worker = result.getWheelDispatch().getWorker();
        }
        if (worker != null){
            problem.setWorker(worker);
            problemDao.insertProblem(problem);
            return  new Result(problem,"添加成功",100);
        }
        return  new Result(problem,"添加失败,未找到责任人,请检查该工序是否完工!",101);
    }
    @RequestMapping("/findProblemF")
    @ResponseBody
    public Result findProblemF(String finder){
        List<Problem> list = problemDao.findProblemByFinder(finder);
        return  new Result(list,"添加成功",100);
    }
    @RequestMapping("/findProblemCond")
    @ResponseBody
    public Result findProblemCond(@RequestBody Problem problem){
        List<Problem> list = problemService.findProblemByCond(problem);
        return  new Result(list,"添加成功",100);
    }
    @RequestMapping("/findProblemCondPage")
    @ResponseBody
    public Result findProblemCondPage(@RequestBody Problem problem,String page,String size){
        PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(size));
        List<Problem> list = problemService.findProblemByCond(problem);
        PageInfo res = new PageInfo(list);
        return  new Result(res,"添加成功",100);
    }

    @RequestMapping("/getProblems")
    @ResponseBody
    public Result getProblems( String worker){
        List<Problem> list = problemDao.findProblemByworker(worker);
        return  new Result(list,"添加成功",100);
    }
    @RequestMapping("/getProblemsById")
    @ResponseBody
    public Result getProblemsById( String id){
        List<Problem> list = problemDao.findProblemByWheelId(Integer.parseInt(id));
        return  new Result(list,"添加成功",100);
    }
    @RequestMapping("/resoveProblem")
    @ResponseBody
    public Result resoveProblem(String id){
        Date now = new Date();
        String time = dateFormater.format(now);
        problemDao.resoveProblem(Integer.parseInt(id),time);
        return  new Result(null,"整改成功",100);
    }
    @RequestMapping("/finishProblem")
    @ResponseBody
    public Result finishProblem(String id){
        Date now = new Date();
        String time = dateFormater.format(now);
        problemDao.finishProblem(Integer.parseInt(id),time);
        return  new Result(null,"整改成功",100);
    }

    @RequestMapping("/finishInspection")
    @ResponseBody
    public Result finishInspection(String name,String id){
        qualityService.finishInspection(name,id);
        return  new Result(null,"整改成功",100);
    }


}
