package com.springboot.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.dao.*;
import com.springboot.domain.*;
import com.springboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/mob")
public class MobileController {
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private ManageService manageService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProblemDao problemDao;
    @Autowired
    private AxleInspectionDao axleInspectionDao;
    @Autowired
    private WheelRoundDao wheelRoundDao;
    @Autowired
    private BearingLoadDao bearingLoadDao;
    @Autowired
    private BearingCapDao bearingCapDao;
    @Autowired
    private BearingTestDao bearingTestDao;
    @Autowired
    private WheelDispatchDao wheelDispatchDao;
    @Autowired
    private QualityService qualityService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private WheelService wheelService;
    @Autowired
    private WheelMeasureService wheelMeasureService;
    @Autowired
    private BearingRepairService bearingRepairService;
    @Autowired
    private WheelRoundService wheelRoundService;
    @Autowired
    private AxleInspectionService axleInspectionService;
    @Autowired
    private BearingLoadService bearingLoadService;
    @Autowired
    private BearingTestService bearingTestService;
    @Autowired
    private BearingCapService bearingCapService;
    @Autowired
    private WheelDispatchService wheelDispatchService;
    @Autowired
    private BlogDao blogDao;

    private SimpleDateFormat dateFormater;

    public MobileController(){
        this.dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @RequestMapping("/getlist")
    @ResponseBody
    public Result getList(String page,String size){
        PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(size));
        List<WheelInfo> list = wheelDao.findAllWheelInfo();
        PageInfo res = new PageInfo(list);
        return new Result(res,"请求成功",100);
    }

    @RequestMapping("/query")
    @ResponseBody
    public Result findInfo(@RequestBody SearchWheelParam param){
        Integer page = Integer.parseInt(param.getPage());
        Integer size = Integer.parseInt(param.getSize());
        List<WheelInfo> data = null;
        PageHelper.startPage(page,size);
        data = manageService.findWheelInfoByCondition(param);
        PageInfo result = new PageInfo(data);
        return  new Result(result,"添加成功",100);
    }

    @RequestMapping("/queryAll")
    @ResponseBody
    public Result queryAll(String id){
        WheelInfo wh = wheelDao.findWheelInfoById(Integer.parseInt(id));
        if (wh==null) {
            return  new Result(null,"查询失败",100);
        }
        WheelAll result = manageService.findWheelAllByWheelInfo(wh);
        return  new Result(result,"添加成功",100);
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public Result getUserInfo(String id){
        UserInfo user = userDao.findUserById(Integer.parseInt(id));
        return  new Result(user,"添加成功",100);
    }

    @RequestMapping("/getWorkerProblems")
    @ResponseBody
    public Result getWorkerProblems(String name){
        List<Problem> problems = problemDao.findProblemByworkerUnFinish(name);
        return  new Result(problems,"添加成功",100);
    }

    @RequestMapping("/getFinderProblems")
    @ResponseBody
    public Result getFinderProblems(String name){
        List<Problem> problems = problemDao.findProblemByFinderUnFinish(name);
        return  new Result(problems,"添加成功",100);
    }

    @RequestMapping("/correctProblems")
    @ResponseBody
    public Result correctProblems(String id){
        Date now = new Date();
        String time = dateFormater.format(now);
        problemDao.resoveProblem(Integer.parseInt(id),time);
        return  new Result(null,"添加成功",100);
    }

    @RequestMapping("/confirmProblems")
    @ResponseBody
    public Result confirmProblems(String id){
        Date now = new Date();
        String time = dateFormater.format(now);
        problemDao.finishProblem(Integer.parseInt(id),time);
        return  new Result(null,"添加成功",100);
    }

    @RequestMapping("/getWheelsToMeasure")
    @ResponseBody
    public Result getWheelsToMeasure(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preMeasure");
        for (Integer id:set){
            WheelInfo wheelInfo = wheelDao.findWheelInfoById(id);
            wheelInfoList.add(wheelInfo);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/getWheelsToMeasure2")
    @ResponseBody
    public Result getWheelsToMeasure2(){
        List<WheelInfo> wheelInfoList = wheelDao.findWheelInfoToMeasure();
        return  new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/chooseWheelM")
    @ResponseBody
    public Result chooseWheelM(String id){
        Integer wheelId = Integer.parseInt(id);
        Boolean res = redisTemplate.opsForSet().isMember("preMeasure",wheelId);
        if (res){
            redisTemplate.opsForSet().remove("preMeasure",wheelId);
            return new Result(null,"添加成功",100);
        }else {
            return new Result(null,"添加失败",101);
        }
    }
    @RequestMapping("/turnBackM")
    @ResponseBody
    public Result turnBackM(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preMeasure", wheelId);
        return new Result(null,"添加成功",100);
    }


    @RequestMapping("/getWheelsToBearingRepair")
    @ResponseBody
    public Result getWheelsToBearingRepair(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preBearingRepair");
        for (Integer id:set){
            WheelInfo wheelInfo = wheelDao.findWheelInfoById(id);
            wheelInfoList.add(wheelInfo);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/getWheelsToBearingRepair2")
    @ResponseBody
    public Result getWheelsToBearingRepair2(){
        List<WheelInfo> wheelInfoList = wheelDao.findWheelInfoToBearing();
        return  new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/chooseWheelBR")
    @ResponseBody
    public Result chooseWheelBR(String id){
        Integer wheelId = Integer.parseInt(id);
        Boolean res = redisTemplate.opsForSet().isMember("preBearingRepair",wheelId);
        if (res){
            redisTemplate.opsForSet().remove("preBearingRepair",wheelId);
            return new Result(null,"添加成功",100);
        }else {
            return new Result(null,"添加失败",101);
        }
    }

    @RequestMapping("/turnBackBR")
    @ResponseBody
    public Result turnBackBR(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preBearingRepair", wheelId);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/getWheelsToMagInspection")
    @ResponseBody
    public Result getWheelsToMagInspection(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preMagInspection");
        for (Integer id:set){
            WheelInfo wheelInfo = wheelDao.findWheelInfoById(id);
            wheelInfoList.add(wheelInfo);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/getWheelsToMagInspection2")
    @ResponseBody
    public Result getWheelsToMagInspection2(){
        List<WheelInfo> wheelInfoList = axleInspectionDao.findWheelInfoToMagInspection();
        return  new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/chooseWheelMI")
    @ResponseBody
    public Result chooseWheelMI(String id){
        Integer wheelId = Integer.parseInt(id);
        Boolean res = redisTemplate.opsForSet().isMember("preMagInspection",wheelId);
        if (res){
            redisTemplate.opsForSet().remove("preMagInspection",wheelId);
            return new Result(null,"添加成功",100);
        }else {
            return new Result(null,"添加失败",101);
        }
    }
    @RequestMapping("/turnBackMI")
    @ResponseBody
    public Result turnBackMI(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preMagInspection", wheelId);
        return new Result(null,"添加成功",100);
    }


    @RequestMapping("/getWheelsToAxleInspection2")
    @ResponseBody
    public Result getWheelsToAxleInspection2(){
        List<WheelInfo> wheelInfoList = axleInspectionDao.findWheelInfoToAxleInspection();
        return  new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/getWheelsToAxleInspection")
    @ResponseBody
    public Result getWheelsToAxleInspection(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preAxleInspection");
        for (Integer id:set){
            WheelInfo wheelInfo = wheelDao.findWheelInfoById(id);
            wheelInfoList.add(wheelInfo);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/chooseWheelAI")
    @ResponseBody
    public Result chooseWheelAI(String id){
        Integer wheelId = Integer.parseInt(id);
        Boolean res = redisTemplate.opsForSet().isMember("preAxleInspection",wheelId);
        if (res){
            redisTemplate.opsForSet().remove("preAxleInspection",wheelId);
            return new Result(null,"添加成功",100);
        }else {
            return new Result(null,"添加失败",101);
        }
    }

    @RequestMapping("/turnBackAI")
    @ResponseBody
    public Result turnBackAI(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preAxleInspection", wheelId);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/getWheelsToWheelRound2")
    @ResponseBody
    public Result getWheelsToWheelRound2(){
        List<WheelInfo> wheelInfoList = wheelRoundDao.findWheelInfoToWheelRound();
        return  new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/getWheelsToWheelRound")
    @ResponseBody
    public Result getWheelsToWheelRound(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preWheelRounding");
        for (Integer id:set){
            WheelInfo wheelInfo = wheelDao.findWheelInfoById(id);
            wheelInfoList.add(wheelInfo);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/chooseWheelWR")
    @ResponseBody
    public Result chooseWheelWR(String id){
        Integer wheelId = Integer.parseInt(id);
        Boolean res = redisTemplate.opsForSet().isMember("preWheelRounding",wheelId);
        if (res){
            redisTemplate.opsForSet().remove("preWheelRounding",wheelId);
            return new Result(null,"添加成功",100);
        }else {
            return new Result(null,"添加失败",101);
        }
    }

    @RequestMapping("/turnBackWR")
    @ResponseBody
    public Result turnBackWR(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preWheelRounding", wheelId);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/getWheelsToBearingLoad2")
    @ResponseBody
    public Result getWheelsToBearingLoad2(){
        List<WheelInfo> wheelInfoList = bearingLoadDao.findWheelInfoToBearingLoad();
        return  new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/getWheelsToBearingLoad")
    @ResponseBody
    public Result getWheelsToBearingLoad(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preBearingLoad");
        for (Integer id:set){
            WheelInfo wheelInfo = wheelDao.findWheelInfoById(id);
            wheelInfoList.add(wheelInfo);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/chooseWheelBL")
    @ResponseBody
    public Result chooseWheelBL(String id){
        Integer wheelId = Integer.parseInt(id);
        Boolean res = redisTemplate.opsForSet().isMember("preBearingLoad",wheelId);
        if (res){
            redisTemplate.opsForSet().remove("preBearingLoad",wheelId);
            return new Result(null,"添加成功",100);
        }else {
            return new Result(null,"添加失败",101);
        }
    }
    @RequestMapping("/turnBackBL")
    @ResponseBody
    public Result turnBackBL(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preBearingLoad", wheelId);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/getWheelsToBearingCap2")
    @ResponseBody
    public Result getWheelsToBearingCap2(){
        List<WheelInfo> wheelInfoList = bearingCapDao.findWheelInfoToBearingCap();
        return  new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/getWheelsToBearingCap")
    @ResponseBody
    public Result getWheelsToBearingCap(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preBearingrCap");
        for (Integer id:set){
            WheelInfo wheelInfo = wheelDao.findWheelInfoById(id);
            wheelInfoList.add(wheelInfo);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/chooseWheelBC")
    @ResponseBody
    public Result chooseWheelBC(String id){
        Integer wheelId = Integer.parseInt(id);
        Boolean res = redisTemplate.opsForSet().isMember("preBearingrCap",wheelId);
        if (res){
            redisTemplate.opsForSet().remove("preBearingrCap",wheelId);
            return new Result(null,"添加成功",100);
        }else {
            return new Result(null,"添加失败",101);
        }
    }

    @RequestMapping("/turnBackBC")
    @ResponseBody
    public Result turnBackBC(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preBearingrCap", wheelId);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/getWheelsToBearingTest2")
    @ResponseBody
    public Result getWheelsToBearingTest2(){
        List<WheelInfo> wheelInfoList = bearingTestDao.findWheelInfoToBearingTest();
        return  new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/getWheelsToBearingTest")
    @ResponseBody
    public Result getWheelsToBearingTest(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preBearingrollTest");
        for (Integer id:set){
            WheelInfo wheelInfo = wheelDao.findWheelInfoById(id);
            wheelInfoList.add(wheelInfo);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/chooseWheelBT")
    @ResponseBody
    public Result chooseWheelBT(String id){
        Integer wheelId = Integer.parseInt(id);
        Boolean res = redisTemplate.opsForSet().isMember("preBearingrollTest",wheelId);
        if (res){
            redisTemplate.opsForSet().remove("preBearingrollTest",wheelId);
            return new Result(null,"添加成功",100);
        }else {
            return new Result(null,"添加失败",101);
        }
    }

    @RequestMapping("/turnBackBT")
    @ResponseBody
    public Result turnBackBT(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preBearingrollTest", wheelId);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/getWheelsToRemeasure2")
    @ResponseBody
    public Result getWheelsToRemeasure2(){
        List<WheelInfo> wheelInfoList = wheelDispatchDao.findWheelInfoToWheelDispatchRemeasure();
        return  new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/getWheelsToRemeasure")
    @ResponseBody
    public Result getWheelsToRemeasure(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preRemeasure");
        for (Integer id:set){
            WheelInfo wheelInfo = wheelDao.findWheelInfoById(id);
            wheelInfoList.add(wheelInfo);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/chooseWheelRM")
    @ResponseBody
    public Result chooseWheelRM(String id){
        Integer wheelId = Integer.parseInt(id);
        Boolean res = redisTemplate.opsForSet().isMember("preRemeasure",wheelId);
        if (res){
            redisTemplate.opsForSet().remove("preRemeasure",wheelId);
            return new Result(null,"添加成功",100);
        }else {
            return new Result(null,"添加失败",101);
        }
    }
    @RequestMapping("/turnBackRM")
    @ResponseBody
    public Result turnBackRM(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preRemeasure", wheelId);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/getWheelsToQualityCheck")
    @ResponseBody
    public Result getWheelsToQualityCheck(){
        List<WheelInfo> wheelInfoList = manageService.findWheelInfoByCondition2Check(new SearchWheelParam());
        return  new Result(wheelInfoList,"添加成功",100);
    }

    @RequestMapping("/addProblem")
    @ResponseBody
    public Result addProblem(@RequestBody Problem problem){
        Integer wheelid = problem.getWheelId();
        String process = problem.getProcessBelong();
        WheelInfo wh = wheelDao.findWheelInfoById(wheelid);
        WheelAll result = manageService.findWheelAllByWheelInfo(wh);
        String worker = "";
        if ("0".equals(process)){
            worker = result.getWheelInfo().getWorker();
        }
        if ("1".equals(process)){
            worker = result.getWheelMeasure().getworker();
        }
        if ("2".equals(process)){
            worker = result.getBearingRepair().getworker();
        }
        if ("3".equals(process)){
            worker = result.getAxleInspection().getMagInspector();
        }
        if ("4".equals(process)){
            worker = result.getAxleInspection().getWorker();
        }
        if ("5".equals(process)){
            worker = result.getWheelRound().getWorker();
        }
        if ("6".equals(process)){
            worker = result.getBearingLoad().getWorker();
        }
        if ("7".equals(process)){
            worker = result.getBearingCap().getworker();
        }
        if ("8".equals(process)){
            worker = result.getBearingTest().getworker();
        }
        if ("9".equals(process)){
            worker = result.getWheelDispatch().getWorker();
        }
        problem.setWorker(worker);
        problemDao.insertProblem(problem);
        return  new Result(problem,"添加成功",100);
    }

    @RequestMapping("/finishInspection")
    @ResponseBody
    public Result finishInspection(String name,String id){
        System.out.println(name);
        System.out.println(id);
        qualityService.finishInspection(name,id);
        return  new Result(null,"添加成功",100);
    }

    @RequestMapping("/scanquery")
    @ResponseBody
    public Result scanquery(String id){
        System.out.println(id);
        WheelInfo wheelInfo = wheelDao.findWheelInfoById(Integer.parseInt(id));
        return  new Result(wheelInfo,"添加成功",100);
    }

    @RequestMapping("/login")
    @ResponseBody
    public Result login(String name, String password, HttpServletRequest request){
        System.out.println(name);
        System.out.println(password);
        Map<String,Object> resmap = userService.mobilelogin(name,password,request);
        if (resmap==null){
            return new Result(null,"用户名或密码错误",101);
        }
        List<Integer> titles = new ArrayList<>();
        Set<String> rights = (Set<String>) resmap.get("subrights");
        List<String> orgright = new ArrayList<>();
        orgright.add("/information");
        orgright.add("/measure");
        orgright.add("/repairProcess");
        orgright.add("/wheelRounding");
        orgright.add("/magneticInspection");
        orgright.add("/ultrasonicInspection");
        orgright.add("/load");
        orgright.add("/capping");
        orgright.add("/rollTest");
        orgright.add("/remeasure");
        orgright.add("/dispatchCheck");

        for(int i=0;i<orgright.size();i++){
            String val = orgright.get(i);
            if (rights.contains(val)){
                titles.add(i);
            }
        }
        resmap.put("titles",titles);
        return new Result(resmap,"登录成功",100);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Result logout(String name){
        String token = (String) redisTemplate.opsForHash().get(name+"mobtoken","token");
        if (token==null){
            return new Result(null,"登录成功",100);
        }
        redisTemplate.delete(token);
        redisTemplate.delete(name+"mobtoken");
        redisTemplate.opsForList().leftPush(name+"moblog","logout="+dateFormater.format(new Date()));
        redisTemplate.opsForSet().remove("mobloginUser",name);
        return new Result(null,"登录成功",100);
    }

    @RequestMapping("/addWheel")
    @ResponseBody
    public Result addWheel(@RequestBody WheelInfo wheelInfo){
        wheelInfo.setInfoTakeFinishTime(dateFormater.format(new Date()));
        WheelInfo result = null;
        String path = null;
        try{
            result = wheelService.insertWheelInfo(wheelInfo);
            redisTemplate.opsForSet().add("preMeasure",result.getWheelId());
            path = wheelService.generateQRcode(result.getWheelId().toString());
        }catch(Exception e){
            System.out.println(e);
            return new Result(null,"添加失败",101);
        }
        return new Result(result,"添加成功",100);
    }

    @RequestMapping("/addMeasure")
    @ResponseBody
    public Result addMeasure(@RequestBody WheelMeasure wheelMeasure){
        wheelMeasure.setFinishTime(dateFormater.format(new Date()));
        wheelMeasureService.addMeasure(wheelMeasure);
        if (!"4".equals(wheelMeasure.getRepairProcess())){
            redisTemplate.opsForSet().add("preBearingRepair",wheelMeasure.getWheelId());
        }
        return new Result(wheelMeasure,"添加成功",100);
    }

    @RequestMapping("/addBearing")
    @ResponseBody
    public Result addBearing(@RequestBody BearingRepair bearingRepair){
        bearingRepair.setFinishTime(dateFormater.format(new Date()));
        BearingRepair result = bearingRepairService.addBearingRepair(bearingRepair);
        WheelInfo wheelInfo = wheelDao.findWheelInfoById(result.getWheelId());
        if("0".equals(wheelInfo.getIsmagnetInspectionFinish())){
            redisTemplate.opsForSet().add("preMagInspection",wheelInfo.getWheelId());
        }else if("0".equals(wheelInfo.getIsWheelRoundingFinish())){
            redisTemplate.opsForSet().add("preWheelRounding",wheelInfo.getWheelId());
        }else if("1".equals(wheelInfo.getIsbearingLoadFinish())||"2".equals(wheelInfo.getIsbearingLoadFinish())||"3".equals(wheelInfo.getIsbearingLoadFinish())){
            redisTemplate.opsForSet().add("preBearingLoad",wheelInfo.getWheelId());
        }else {
            redisTemplate.opsForSet().add("preBearingrollTest",wheelInfo.getWheelId());
        }
        return new Result(result,"添加成功",100);
    };

    @RequestMapping("/addWheelRound")
    @ResponseBody
    public Result addbearingCap(@RequestBody WheelRound wheelRound){
        wheelRound.setFinishTime(dateFormater.format(new Date()));
        wheelRoundService.addWheelRound(wheelRound);
        WheelInfo wheelInfo = wheelDao.findWheelInfoById(wheelRound.getWheelId());
        if("1".equals(wheelInfo.getIsbearingLoadFinish())||"2".equals(wheelInfo.getIsbearingLoadFinish())||"3".equals(wheelInfo.getIsbearingLoadFinish())){
            redisTemplate.opsForSet().add("preBearingLoad",wheelInfo.getWheelId());
        }else {
            redisTemplate.opsForSet().add("preBearingrCap",wheelInfo.getWheelId());
        }
        return new Result(wheelRound,"添加成功",100);
    }

    //磁探
    @RequestMapping("/addMagInspection")
    @ResponseBody
    public Result addMagInspection(@RequestBody AxleInspection axleInspection){
        axleInspection.setMagfinishTime(dateFormater.format(new Date()));
        axleInspectionService.addMagInspection(axleInspection);
        redisTemplate.opsForSet().add("preAxleInspection",axleInspection.getWheelId());
        return new Result(axleInspection,"添加成功",100);
    }

    //超探
    @RequestMapping("/addaxleInspection")
    @ResponseBody
    public Result magneticInspection(@RequestBody AxleInspection axleInspection){
        axleInspection.setUltfinishTime(dateFormater.format(new Date()));
        axleInspection.setReultfinishTime(dateFormater.format(new Date()));
        axleInspectionService.addAxleInspection(axleInspection);

        WheelInfo wheelInfo = wheelDao.findWheelInfoById(axleInspection.getWheelId());
        if("0".equals(wheelInfo.getIsWheelRoundingFinish())){
            redisTemplate.opsForSet().add("preWheelRounding",wheelInfo.getWheelId());
        }else if("1".equals(wheelInfo.getIsbearingLoadFinish())||"2".equals(wheelInfo.getIsbearingLoadFinish())||"3".equals(wheelInfo.getIsbearingLoadFinish())){
            redisTemplate.opsForSet().add("preBearingLoad",wheelInfo.getWheelId());
        }else {
            redisTemplate.opsForSet().add("preBearingrollTest",wheelInfo.getWheelId());
        }
        return new Result(axleInspection,"添加成功",100);
    }
    //压装
    @RequestMapping("/addBearingLoad")
    @ResponseBody
    public Result addBearingLoad(@RequestBody BearingLoad bearingLoad){
        bearingLoad.setFinishTime(dateFormater.format(new Date()));
        bearingLoadService.addBearingLoad(bearingLoad);
        redisTemplate.opsForSet().add("preBearingrCap",bearingLoad.getWheelId());
        return new Result(bearingLoad,"添加成功",100);
    }
    //磨合
    @RequestMapping("/addbearingTest")
    @ResponseBody
    public Result addbearingCap(@RequestBody BearingTest bearingTest){
        bearingTest.setFinishTime(dateFormater.format(new Date()));
        bearingTestService.addBearingTest(bearingTest);
        redisTemplate.opsForSet().add("preRemeasure",bearingTest.getWheelId());
        return new Result(bearingTest,"添加成功",100);
    }
    //关盖
    @RequestMapping("/addbearingCap")
    @ResponseBody
    public Result addbearingCap(@RequestBody BearingCap bearingCap){
        bearingCap.setFinishTime(dateFormater.format(new Date()));
        bearingCapService.addBearingCap(bearingCap);
        redisTemplate.opsForSet().add("preBearingrollTest",bearingCap.getWheelId());
        return new Result(bearingCap,"添加成功",100);
    }
    //复测
    @RequestMapping("/addwheelRemeasure")
    @ResponseBody
    public Result addwheelRemeasure(@RequestBody WheelDispatch wheelDispatch){
        wheelDispatch.setFinishTime(dateFormater.format(new Date()));
        wheelDispatchService.addWheelDispatchRemeasure(wheelDispatch);
        redisTemplate.opsForSet().add("preQualityCheck",wheelDispatch.getWheelId());
        return new Result(wheelDispatch,"添加成功",100);
    }
    //修改
    @RequestMapping("/modifyWheel")
    @ResponseBody
    public Result modifyWheel(@RequestBody WheelInfo wheelInfo){
        wheelInfo.setInfoTakeFinishTime(dateFormater.format(new Date()));
        System.out.println(wheelInfo);
        WheelInfo result = null;
        result = wheelService.updateWheelInfo(wheelInfo);
        redisTemplate.opsForSet().add("preMeasure",result.getWheelId());
        redisTemplate.opsForSet().remove("preBearingRepair",result.getWheelId());
        redisTemplate.opsForSet().remove("preMagInspection",result.getWheelId());
        redisTemplate.opsForSet().remove("preAxleInspection",result.getWheelId());
        redisTemplate.opsForSet().remove("preWheelRounding",result.getWheelId());
        redisTemplate.opsForSet().remove("preBearingLoad",result.getWheelId());
        redisTemplate.opsForSet().remove("preBearingrCap",result.getWheelId());
        redisTemplate.opsForSet().remove("preBearingrollTest",result.getWheelId());
        redisTemplate.opsForSet().remove("preRemeasure",result.getWheelId());
        redisTemplate.opsForSet().remove("preQualityCheck",result.getWheelId());
        return new Result(result,"修改成功",100);
    }

    @RequestMapping("/modifyMeasure")
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

    @RequestMapping("/modifyWheelRound")
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

    @RequestMapping("/modifyBearing")
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

    @RequestMapping("/modifyMagInspection")
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

    @RequestMapping("/modifyAxleInspection")
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

    @RequestMapping("/modifyBearingLoad")
    @ResponseBody
    public Result updateBearingLoad(@RequestBody BearingLoad bearingLoad ){
        bearingLoad.setFinishTime(dateFormater.format(new Date()));
        bearingLoadService.updateBearingLoad(bearingLoad);
        redisTemplate.opsForSet().add("preBearingrCap",bearingLoad.getWheelId());
        redisTemplate.opsForSet().remove("preBearingrollTest",bearingLoad.getWheelId());
        redisTemplate.opsForSet().remove("preRemeasure",bearingLoad.getWheelId());
        redisTemplate.opsForSet().remove("preQualityCheck",bearingLoad.getWheelId());
        return new Result(bearingLoad,"添加成功",100);
    }

    @RequestMapping("/modifyBearingCap")
    @ResponseBody
    public Result modifyBearingCap(@RequestBody BearingCap bearingCap){
        bearingCap.setFinishTime(dateFormater.format(new Date()));
        bearingCapService.updateBearingCap(bearingCap);
        redisTemplate.opsForSet().add("preBearingrollTest",bearingCap.getWheelId());
        redisTemplate.opsForSet().remove("preRemeasure",bearingCap.getWheelId());
        redisTemplate.opsForSet().remove("preQualityCheck",bearingCap.getWheelId());
        return new Result(bearingCap,"添加成功",100);
    }

    @RequestMapping("/modifyBearingTest")
    @ResponseBody
    public Result modifyBearingCap(@RequestBody BearingTest bearingTest){
        bearingTest.setFinishTime(dateFormater.format(new Date()));
        bearingTestService.updateBearingTest(bearingTest);
        redisTemplate.opsForSet().add("preRemeasure",bearingTest.getWheelId());
        redisTemplate.opsForSet().remove("preQualityCheck",bearingTest.getWheelId());
        return new Result(bearingTest,"添加成功",100);
    }

    @RequestMapping("/modifyWheelRemeasure")
    @ResponseBody
    public Result modifyWheelRemeasure(@RequestBody WheelDispatch wheelDispatch){
        wheelDispatch.setFinishTime(dateFormater.format(new Date()));
        wheelDispatchService.updateWheelDispatchRemeasure(wheelDispatch);
        redisTemplate.opsForSet().add("preQualityCheck",wheelDispatch.getWheelId());
        return new Result(wheelDispatch,"添加成功",100);
    }

    @RequestMapping("/findBlog")
    @ResponseBody
    public Result findBlog(@RequestBody Map<String,String> param){
        Integer page = Integer.parseInt(param.get("page"));
        Integer size = Integer.parseInt(param.get("size"));
        PageHelper.startPage(page,size);
        List<Blog> blogs = blogDao.findAllBlogs();
        for (Blog blog : blogs){
            String id = blog.getId().toString();
            Integer like = (Integer) redisTemplate.opsForHash().get("Blog"+id,"like");
            Integer dislike = (Integer) redisTemplate.opsForHash().get("Blog"+id,"dislike");
            Integer commentnum = (Integer) redisTemplate.opsForHash().get("Blog"+id,"commentnum");
            blog.setLike(like);
            blog.setDislike(dislike);
            blog.setCommentnum(commentnum);
        }
        PageInfo result = new PageInfo(blogs);
        return new Result(result,"查询成功",100);
    }

    @RequestMapping("/findBlogById")
    @ResponseBody
    public Result findBlogById(String id){
        Blog blog = blogDao.findBlogById(Integer.parseInt(id));
        Integer like = (Integer) redisTemplate.opsForHash().get("Blog"+id,"like");
        Integer dislike = (Integer) redisTemplate.opsForHash().get("Blog"+id,"dislike");
        Integer commentnum = (Integer) redisTemplate.opsForHash().get("Blog"+id,"commentnum");
        blog.setLike(like);
        blog.setDislike(dislike);
        blog.setCommentnum(commentnum);
        blog.setComments((List<Blog>) findComment(id).getObject());
        return new Result(blog,"查询成功",100);
    }

    @RequestMapping("/addBlog")
    @ResponseBody
    public Result addBlog(@RequestBody Map<String,String> map){
        String content = map.get("blog");
        String publisher = map.get("publisher");
        String father = map.get("id");
        String replyto = map.get("replyto");
        Blog blog = new Blog();
        blog.setContent(content);
        blog.setPublisher(publisher);
        blog.setReplyto(replyto);
        blog.setPublishTime(dateFormater.format(new Date()));
        if (!"-1".equals(father)){
            blog.setFather(Integer.parseInt(father));
            redisTemplate.opsForHash().increment("Blog"+father,"commentnum",1);
        }
        blogDao.insertBlog(blog);
        String id = blog.getId().toString();
        redisTemplate.opsForHash().put("Blog"+id,"like",0);
        redisTemplate.opsForHash().put("Blog"+id,"dislike",0);
        redisTemplate.opsForHash().put("Blog"+id,"commentnum",0);
        return new Result(blog,"添加成功",100);
    }
    @RequestMapping("/findComment")
    @ResponseBody
    public Result findComment(String id){
        List<Blog> blogs = blogDao.findCommentBlog(Integer.parseInt(id));
        for (Blog blog : blogs){
            id = blog.getId().toString();
            Integer like = (Integer) redisTemplate.opsForHash().get("Blog"+id,"like");
            Integer dislike = (Integer) redisTemplate.opsForHash().get("Blog"+id,"dislike");
            Integer commentnum = (Integer) redisTemplate.opsForHash().get("Blog"+id,"commentnum");
            blog.setLike(like);
            blog.setDislike(dislike);
            blog.setCommentnum(commentnum);
        }
        return new Result(blogs,"添加成功",100);
    }
    @RequestMapping("/likeBlog")
    @ResponseBody
    public Result likeBlog(String id,String name){
        Boolean res = redisTemplate.opsForSet().isMember("Blog" + id + "like", name);
        if (res){
            redisTemplate.opsForSet().remove("Blog"+id+"like",name);
            redisTemplate.opsForHash().increment("Blog"+id,"like",-1);
            redisTemplate.opsForSet().remove(name+"likeBlog",id);
        }else {
            redisTemplate.opsForSet().add("Blog"+id+"like",name);
            redisTemplate.opsForHash().increment("Blog"+id,"like",1);
            redisTemplate.opsForSet().add(name+"likeBlog",id);
        }
        return new Result(null,"添加成功",100);
    }
    @RequestMapping("/dislikeBlog")
    @ResponseBody
    public Result dislikeBlog(String id,String name){
        Boolean res = redisTemplate.opsForSet().isMember("Blog" + id + "dislike", name);
        if (res){
            redisTemplate.opsForSet().remove("Blog"+id+"dislike",name);
            redisTemplate.opsForHash().increment("Blog"+id,"dislike",-1);
            redisTemplate.opsForSet().remove(name+"dislikeBlog",id);
        }else {
            redisTemplate.opsForSet().add("Blog"+id+"dislike",name);
            redisTemplate.opsForHash().increment("Blog"+id,"dislike",1);
            redisTemplate.opsForSet().add(name+"dislikeBlog",id);
        }
        return new Result(null,"添加成功",100);
    }
    @RequestMapping("/deleteBlog")
    @ResponseBody
    public Result deleteBlog(String id,String name){
        Blog blog = blogDao.findBlogById(Integer.parseInt(id));
        Integer father = blog.getFather();
        if (father!=-1){
            redisTemplate.opsForHash().increment("Blog"+father,"commentnum",-1);
        }
        blogDao.deleteBlog(Integer.parseInt(id));
        blogDao.deleteBlogComment(Integer.parseInt(id));
        redisTemplate.delete("Blog"+id);
        redisTemplate.delete("Blog"+id+"like");
        redisTemplate.delete("Blog"+id+"dislike");
        return new Result(null,"添加成功",100);
    }
    @RequestMapping("/myBlog")
    @ResponseBody
    public Result myBlog(String name){
        List<Blog> blogs = blogDao.findBlog(name);
        for (Blog blog : blogs){
            String id = blog.getId().toString();
            Integer like = (Integer) redisTemplate.opsForHash().get("Blog"+id,"like");
            Integer dislike = (Integer) redisTemplate.opsForHash().get("Blog"+id,"dislike");
            Integer commentnum = (Integer) redisTemplate.opsForHash().get("Blog"+id,"commentnum");
            blog.setLike(like);
            blog.setDislike(dislike);
            blog.setCommentnum(commentnum);
            blog.setComments((List<Blog>) findComment(id).getObject());
        }
        return new Result(blogs,"添加成功",100);
    }

    @RequestMapping("/myBlog2")
    @ResponseBody
    public Result myBlog2(@RequestBody Map<String,String> param){
        Integer page = Integer.parseInt(param.get("page"));
        Integer size = Integer.parseInt(param.get("size"));
        String name = param.get("name");
        PageHelper.startPage(page,size);
        List<Blog> blogs = blogDao.findBlog(name);
        for (Blog blog : blogs){
            String id = blog.getId().toString();
            Integer like = (Integer) redisTemplate.opsForHash().get("Blog"+id,"like");
            Integer dislike = (Integer) redisTemplate.opsForHash().get("Blog"+id,"dislike");
            Integer commentnum = (Integer) redisTemplate.opsForHash().get("Blog"+id,"commentnum");
            blog.setLike(like);
            blog.setDislike(dislike);
            blog.setCommentnum(commentnum);
            blog.setComments((List<Blog>) findComment(id).getObject());
        }
        PageInfo result = new PageInfo(blogs);
        return new Result(result,"添加成功",100);
    }

    @RequestMapping("/myBloglike")
    @ResponseBody
    public Result myBloglike(String id){
        Set<String> set = redisTemplate.opsForSet().members("Blog" + id + "like");
        return new Result(set,"添加成功",100);
    }
    @RequestMapping("/myBlogDislike")
    @ResponseBody
    public Result myBlogDislike(String id){
        Set<String> set = redisTemplate.opsForSet().members("Blog"+id+"dislike");
        return new Result(set,"添加成功",100);
    }
    @RequestMapping("/addFollow")
    @ResponseBody
    public Result addFollow(@RequestBody Map<String,String> map){
        String name = map.get("name");
        String follower = map.get("follower");
        redisTemplate.opsForSet().add(name+"follower",follower);
        redisTemplate.opsForSet().add(follower+"followTo",name);
        return new Result(null,"添加成功",100);
    }
    @RequestMapping("/notFollow")
    @ResponseBody
    public Result notFollow(@RequestBody Map<String,String> map){
        String name = map.get("name");
        String follower = map.get("follower");
        redisTemplate.opsForSet().remove(name+"follower",follower);
        redisTemplate.opsForSet().remove(follower+"followTo",name);
        return new Result(null,"添加成功",100);
    }
    @RequestMapping("/getFollows")
    @ResponseBody
    public Result getFollows(String name){
        Set<String> myfollowers = redisTemplate.opsForSet().members(name+"follower");
        Set<String> myfollowTo = redisTemplate.opsForSet().members(name+"followTo");
        Map<String,Set<String>> follow = new HashMap<>();
        follow.put("myfollowers",myfollowers);
        follow.put("myfollowTo",myfollowTo);
        return new Result(follow,"添加成功",100);
    }
    @RequestMapping("/getLikeBlog")
    @ResponseBody
    public Result getLikeBlog(String name){
        Set<String> likes = redisTemplate.opsForSet().members(name+"likeBlog");
        List<Blog> blogs = new ArrayList<>();
        for (String id : likes){
          Blog blog = (Blog) findBlogById(id).getObject();
          blogs.add(blog);
        }
        return new Result(blogs,"添加成功",100);
    }

    @RequestMapping("/getDislikeBlog")
    @ResponseBody
    public Result getDislikeBlog(String name){
        Set<String> dislikes = redisTemplate.opsForSet().members(name+"dislikeBlog");
        List<Blog> blogs = new ArrayList<>();
        for (String id : dislikes){
            Blog blog = (Blog) findBlogById(id).getObject();
            blogs.add(blog);
        }
        return new Result(blogs,"添加成功",100);
    }
}
