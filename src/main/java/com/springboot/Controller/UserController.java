package com.springboot.Controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.dao.UserDao;
import com.springboot.domain.*;
import com.springboot.service.RoleService;
import com.springboot.service.UserService;
import com.springboot.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

@Controller
@RequestMapping("/userManage")
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserServiceImp userServiceImp;

    private SimpleDateFormat dateFormater;

    public UserController(){
        this.dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @RequestMapping("/getall")
    @ResponseBody
    public List<Role> test(){
        List<Role> roles = roleService.findAllRole();
        return roles;
    }

    @RequestMapping("/add")
    @ResponseBody
    public String insert(Role role){
        roleService.insertRole(role);
        return null;
    }

    @RequestMapping("/byid")
    @ResponseBody
    public UserInfo getbyid(Integer id){
        return userService.findUserById(id);
    }



    //登录
    @RequestMapping(value = "/login",produces = "application/json")
    @ResponseBody
    public Result logincheck(@RequestBody Map<String,Object> map,HttpServletRequest request){
        String name = (String) map.get("name");
        String password = (String) map.get("password");
        System.out.println(name);
        Map<String,Object> resmap = userService.login(name,password,request);
        if (resmap==null){
            return new Result(null,"用户名或密码错误",101);
        }
        return new Result(resmap,"登录成功",100);
    }
    //退出
    @RequestMapping( "/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String name = (String) redisTemplate.opsForValue().get(token);
        userService.logout(name,request);
        return new Result(null,"退出成功",100);
    }
    //获取菜单
    @RequestMapping("/getmenus")
    @ResponseBody
    public Result getmenus(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Set<Menu> menus = (Set<Menu>) request.getServletContext().getAttribute(token);
        //List<Menu> menus = roleService.getmenus(token);
        if (menus != null){
            return new Result(menus,"菜单获取成功",100);
        }
        return new Result(null,"菜单获取失败",101);
    }
    //获取用户列表
    @RequestMapping("/getusers")
    @ResponseBody
    public Result getusers(@RequestBody Map<String,Object> map){
        Integer page = (Integer) map.get("currentPage");
        Integer size = (Integer) map.get("pagesize");
        String online = (String) map.get("isonline");
        PageHelper.startPage(page,size);
        List<UserInfo> list =  userService.findAllUser(online);
        if (list != null){
            PageInfo pageInfo = new PageInfo(list);
            return new Result(pageInfo,"用户获取成功",100);
        }
        return new Result(null,"用户获取失败",101);
    }
    //获取用户，带登录基本信息
    @RequestMapping("/getusersWithLog")
    @ResponseBody
    public Result getusersWithLog(@RequestBody Map<String,Object> map){
        Integer page = (Integer) map.get("currentPage");
        Integer size = (Integer) map.get("pagesize");
        String online = (String) map.get("isonline");
        String ismobile = (String) map.get("ismobile");
        if ("0".equals(ismobile)&&"1".equals(online)){
            Set<String> set = redisTemplate.opsForSet().members("mobloginUser");
            PageHelper.startPage(page,size);
            List<UserInfo> list =  new ArrayList<>();
            for (String name : set){
                List<UserInfo> userInfos = userService.findUserByName(name);
                for(UserInfo userInfo : userInfos){
                    userInfo = userServiceImp.appendMobileLogInfo(userInfo);
                    userInfo.setIsOnline("1");
                    list.add(userInfo);
                }
            }
            if (list != null){
                PageInfo pageInfo = new PageInfo(list);
                return new Result(pageInfo,"用户获取成功",100);
            }
        }else {
            PageHelper.startPage(page,size);
            List<UserInfo> list =  userService.findAllUserLog(online);
            if (list != null){
                PageInfo pageInfo = new PageInfo(list);
                return new Result(pageInfo,"用户获取成功",100);
            }
        }
        return new Result(null,"用户获取失败",101);
    }
    //获取用户登录日志
    @RequestMapping("/getusersLog")
    @ResponseBody
    public Result getusersLog(@RequestBody UserInfo userInfo,String ismobile,String isonline,HttpServletRequest request){
        String name = userInfo.getUsername();
        List<String> list = null;
        if ("1".equals(isonline)&&"0".equals(ismobile)){
            list = redisTemplate.opsForList().range(name+"moblog",0,-1);
        }else {
            list = redisTemplate.opsForList().range(name+"log",0,-1);
        }
        if (list==null){
            return new Result(null,"日志获取失败",101);
        }
        List<Map<String,String>> logmap = new ArrayList<>();
        for (String str : list){
            String[] arr = str.split("=");
            HashMap<String,String> map = new HashMap<>();
            for (int i=0;i<arr.length;i++){
                if (i==0){
                    map.put("url",arr[0]);
                    continue;
                }
                if (i==1){
                    map.put("time",arr[1]);
                    continue;
                }
                if (i==2){
                    map.put("ip",arr[2]);
                }
            }
            logmap.add(map);
        }
        UserLogIn userLogIn = new UserLogIn();
        userLogIn.setUsername(name);
        userLogIn.setLogMap(logmap);
        return new Result(userLogIn,"日志获取成功",100);
    }
    //强制下线用户
    @RequestMapping("/onLineChange")
    @ResponseBody
    public Result onLineChange(@RequestBody UserInfo userInfo,HttpServletRequest request){
        String name = userInfo.getUsername();
        userService.logout(name,request);
        return new Result(null,"强制下线成功",100);
    }
    //搜索用户
    @RequestMapping("/searchusers")
    @ResponseBody
    public Result searchusers(@RequestBody Map<String,Object> map){
        String username = (String) map.get("username");
        System.out.println(username);
        List<UserInfo> userInfo =  userService.findUserByName(username);
        System.out.println(userInfo);
        if (userInfo != null){
            return new Result(userInfo,"用户查询成功",100);
        }
        return new Result(null,"用户查询失败",101);
    }
    //获取用户
    @RequestMapping("/getuid")
    @ResponseBody
    public Result getusersbyid(int id){
        UserInfo user = userService.findUserById(id);
        if (user != null){
            return new Result(user,"用户获取成功",100);
        }
        return new Result(null,"用户获取失败",101);
    }
    //修改用户状态
    @RequestMapping(value = "/moduser",produces = "application/json")
    @ResponseBody
    public Result moduser(@RequestBody UserInfo userInfo){
        userService.updateUserStatu(userInfo);
        return new Result(userInfo,"用户状态已更新",100);
    }
    //获取全部角色信息
    @RequestMapping("/rolenames")
    @ResponseBody
    public Result allRoleNames(){
        List<Role> names = roleService.findAllRole();
        if (names!=null){
            return new Result(names,"角色获取成功",100);
        }
        return new Result(null,"角色获取失败",101);
    }
    //添加用户
    @RequestMapping(value = "/adduser",produces = "application/json")
    @ResponseBody
    public Result addUser(@RequestBody Map<String,Object> map){
        Map<String,Object> map1 = null;
        List<String> roles = null;
        UserInfo userInfo = null;
        try{
            map1 = (Map) map.get("userinfo");
            roles = (List) map1.get("role");
            userInfo = JSON.parseObject(JSON.toJSONString(map1),UserInfo.class);
            userService.addUser(userInfo,roles);
        }catch (Exception e){
            return new Result(null,"添加成功失败",101);
        }
        return new Result(userInfo,"用户添加成功",100);
    }
    //修改用户
    @RequestMapping(value = "/modyuser",produces = "application/json")
    @ResponseBody
    public Result modyUser(@RequestBody Map<String,Object> map){
        Map<String,Object> map1 = null;
        List<String> roles = null;
        UserInfo userInfo = null;
        try{
            map1 = (Map) map.get("userinfo");
            roles = (List) map1.get("role");
            userInfo = JSON.parseObject(JSON.toJSONString(map1),UserInfo.class);
            userService.updateUser(userInfo,roles);
        }catch (Exception e){
            return new Result(null,"用户修改失败",101);
        }
        return new Result(userInfo,"用户修改成功",100);
    }
    //删除用户
    @RequestMapping("/deleteuser")
    @ResponseBody
    public Result deleteUser(@RequestBody Map<String,Object> map){
        String id = (String) map.get("id");
        try {
            userService.deleteUser(Integer.parseInt(id));
        }catch (Exception e){
            return new Result(null,"删除失败",101);
        }
        return new Result(null,"删除成功",100);
    }
    //获取用户权限
    @RequestMapping("/getRights")
    @ResponseBody
    public Result getUsersRights(HttpServletRequest request){
        return getmenus(request);
    }
}
