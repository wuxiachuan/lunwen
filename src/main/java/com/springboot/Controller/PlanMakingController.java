package com.springboot.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.Configration.FileUtil;
import com.springboot.dao.VehicleInfoDao;
import com.springboot.domain.Result;
import com.springboot.domain.VehicleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/plan")
public class PlanMakingController {
    @Autowired
    private VehicleInfoDao vehicleInfoDao;
    @Autowired
    private FileUtil fileUtil;
    @RequestMapping("/getVehicleInfoByCond")
    @ResponseBody
    public Result getVehicleInfoByCond(@RequestBody Map<String,String> map,String page,String size){
        String Id = map.get("Id");
        String vehicleNumber = map.get("vehicleNumber");
        String vehicleType = map.get("vehicleType");
        String RepairDateFrom= map.get("RepairDateFrom");
        String RepairDateTo= map.get("RepairDateTo");
        String finishTimeFrom= map.get("finishTimeFrom");
        String finishTimeTo= map.get("finishTimeTo");
        String isFinish= map.get("isFinish");
        PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(size));
        List<VehicleInfo> list = vehicleInfoDao.findVehicleInfoByCondition(
                                                    Id,
                                                    vehicleNumber,
                                                    vehicleType,
                                                    RepairDateFrom,
                                                    RepairDateTo,
                                                    finishTimeFrom,
                                                    finishTimeTo,
                                                    isFinish);
        PageInfo res = new PageInfo(list);
        return new Result(res,"获取成功",100);
    }
    @RequestMapping("/export")
    @ResponseBody
    public ResponseEntity<byte[]> exportVehicleInfoByCond(){
        List<VehicleInfo> list = vehicleInfoDao.findVehicleInfoAll();
        return FileUtil.exportPlanExcel(list);
    }
    @RequestMapping("/import")
    public Result importData(MultipartFile file) throws IOException {
        List<VehicleInfo> list = FileUtil.excel2VehicleInfo(file);
        addPlan(list);
        System.out.println(list);
        return new Result(list,"添加成功",100);
    }
    @RequestMapping("/addPlan")
    @ResponseBody
    public Result addPlan(@RequestBody List<VehicleInfo> vehicleList){
        System.out.println(vehicleList);
        for (VehicleInfo vehicle : vehicleList){
            if (vehicle!=null){
                vehicleInfoDao.insertVehicleInfo(vehicle);
            }
        }
        return new Result(vehicleList,"获取成功",100);
    }

    @RequestMapping("/getVehicleNum")
    @ResponseBody
    public Result getVehicleNum(String date){
        System.out.println(date);
        List<VehicleInfo> vehicles = vehicleInfoDao.findVehicleInfoByDate(date);
        List<String> numList = new ArrayList<>();
        for (VehicleInfo vehicle : vehicles){
            numList.add(vehicle.getVehicleNumber());
        }
        System.out.println(numList);
        return new Result(numList,"获取成功",100);
    }
}
