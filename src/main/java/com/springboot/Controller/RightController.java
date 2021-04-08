package com.springboot.Controller;

import com.springboot.domain.Result;
import com.springboot.domain.Right;
import com.springboot.domain.Role;
import com.springboot.service.RoleService;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rightManage")
public class RightController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    //获取全部权限（树形格式）
    @RequestMapping("/allright")
    @ResponseBody
    public Result getallrights(){
        List<Right> rights;
        try{
            rights = userService.findAllRight();
        }catch (Exception e){
            return new Result(null,"权限列表获取失败",101) ;
        }
        return new Result(rights,"权限列表获取成功",100) ;
    }
    //获取全部权限（列表格式）
    @RequestMapping("/rightlist")
    @ResponseBody
    public Result allrightlist(){
        List<Right> rights;
        try{
            rights = userService.findAllRightlist();
        }catch (Exception e){
            return new Result(null,"权限列表获取失败",101) ;
        }
        return new Result(rights,"权限列表获取成功",100) ;
    }

    //获取原始角色和权限
    @RequestMapping("/rolerights")
    @ResponseBody
    public Result roleRights(){
        List<Role> roles;
       try{
           roles = userService.findAllRole();
       }catch (Exception e){
           return new Result(null,"角色获取失败",101);
       }
        return new Result(roles,"角色获取成功",100);
    }
    //获取修改用角色和权限
    @RequestMapping("/modrolerights")
    @ResponseBody
    public Result modroleRights(){
        List<Role> roles;
        try{
            roles = userService.findAllModRole();
        }catch (Exception e){
            return new Result(null,"角色获取失败",101);
        }
        return new Result(roles,"角色获取成功",100);
    }

    @RequestMapping("/insertright")
    @ResponseBody
    public Result insertright(String id,String rid,String sid){
        try {
            userService.insertRRmap(id,rid,sid);
        }catch (Exception e){
            return new Result(null,"添加失败",101);
        }
        return new Result(null,"添加成功",100);
    }
    @RequestMapping("/delright")
    @ResponseBody
    public Result deleteright(@RequestBody Map<String,Object> map){
        String rid = (String) map.get("roleid");
        String id =(String) map.get("rightid");
        String sid =(String) map.get("subrightid");
        List<Right> rightList = null;
        try {
            userService.deleteRight(rid,id,sid);
            rightList = userService.findRoleRight(rid);
        }catch (Exception e){
            return new Result(null,"删除失败",101);
        }
        return new Result(rightList,"删除成功",100);
    }
    @RequestMapping("/createtab")
    @ResponseBody
    public Result insertright2(){
        try {
            userService.createRightTable();
        }catch (Exception e){
            return new Result(null,"添加失败",101);
        }
        return new Result(null,"添加成功",100);
    }

    //分配权限
    @RequestMapping("/assignmentRight")
    @ResponseBody
    public Result assignmentRight(@RequestBody Map<String,Object> map){
        String rid = (String) map.get("roleid");
        List rights = (List) map.get("rights");
        List<Right> rightList = null;
        try{
            userService.assignmentRight(rid,rights);
            rightList = userService.findRoleRight(rid);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(null,"添加失败",101);
        }
        return new Result(rightList,"添加成功",100);
    }

}
