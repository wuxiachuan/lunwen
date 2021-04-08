package com.springboot.service;

import com.alibaba.fastjson.JSON;
import com.springboot.dao.UserDao;
import com.springboot.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

//@CacheConfig(cacheNames = "rights")
@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;

    private Map<String,String> rightMap;

    private SimpleDateFormat dateFormater;

    public UserServiceImp(){
         this.dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public Map<String,Object> login(String name, String password,HttpServletRequest request) {
        UserInfo user =  userDao.findUserByNameAndPassword(name,password);
        if (user == null) return null;
        if ("0".equals(user.getStatus())) return null;
        //检测是否重复登录,重复登录强制前者下线
        String oldtoken = (String) redisTemplate.opsForValue().get(name);
        if (oldtoken != null){
            if (redisTemplate.hasKey(oldtoken))          redisTemplate.delete(oldtoken);
            if (redisTemplate.hasKey(oldtoken+"token"))  redisTemplate.delete(oldtoken+"token");
        }
        String token = UUID.randomUUID().toString();
        Map<String,Object> resmap = getUserRightMap(user,token,request);
        String status = user.getStatus();
        redisTemplate.opsForValue().set(token,name);
        redisTemplate.expire(token,Duration.ofMinutes(120));
        redisTemplate.opsForValue().set(name,token);
        redisTemplate.opsForHash().put(token+"token","name",name);
        redisTemplate.opsForHash().put(token+"token","status",status);
        redisTemplate.opsForHash().put(token+"token","ip",request.getRemoteAddr());
        redisTemplate.opsForHash().put(token+"token","loginTime",dateFormater.format(new Date()));
        redisTemplate.opsForList().leftPush(name+"log","/userManage/login="+dateFormater.format(new Date())+"="+request.getRemoteAddr());
        redisTemplate.opsForSet().add("loginUser",name);
        userDao.login(name);
        return resmap;
    }

    @Override
    public Map<String,Object> mobilelogin(String name, String password,HttpServletRequest request){
        UserInfo user =  userDao.findUserByNameAndPassword(name,password);
        if (user == null) return null;
        if ("0".equals(user.getStatus())) return null;

        //检测是否重复登录,已登录无法再登录
        String oldtoken = (String) redisTemplate.opsForHash().get(name+"mobtoken","token");
        if (oldtoken != null){
            if (redisTemplate.hasKey(oldtoken))          redisTemplate.delete(oldtoken);
            if (redisTemplate.hasKey(oldtoken+"token"))  redisTemplate.delete(oldtoken+"token");
        }

        String token = UUID.randomUUID().toString();
        Map<String,Object> resmap = getUserRightMap(user,token,request);
        resmap.put("userid",user.getId());
        resmap.put("username",user.getUsername());
        resmap.put("token",token);
        String status = user.getStatus();
        redisTemplate.opsForValue().set(token,name);
        redisTemplate.opsForHash().put(name+"mobtoken","token",token);
        redisTemplate.opsForHash().put(name+"mobtoken","status",status);
        redisTemplate.opsForHash().put(name+"mobtoken","loginTime",dateFormater.format(new Date()));
        redisTemplate.opsForList().leftPush(name+"moblog","login="+dateFormater.format(new Date()));
        redisTemplate.opsForSet().add("mobloginUser",name);
        return resmap;
    }

    public Map<String,Object> getUserRightMap(UserInfo user,String token,HttpServletRequest request){
        Map<String,Object> resmap = new HashMap<>();
        List<Right> rights = new ArrayList<>();
        Set<Menu> menus = new HashSet<>();
        for (Role role : user.getRoles()){
            rights.addAll(findRoleRight(role.getId()));
        }
        for (Right right : rights){
            List<Menu> rightsub = new ArrayList<>();
            for (Right subright : right.getSubRight()){
                rightsub.add(new Menu(Integer.valueOf(subright.getId()),
                                      subright.getPermissionName(),
                                      subright.getUrl(),
                              null));
            }
            menus.add(new Menu(Integer.valueOf(right.getId()),
                    right.getPermissionName(),
                    right.getUrl(),
                    rightsub));
        }
//        String menuslist = JSON.toJSONString(menus);
//        redisTemplate.opsForValue().set(token+"right",menuslist);
        //权限存入application
        request.getServletContext().setAttribute(token,menus);
        resmap.put("menus",menus);
        resmap.put("userid",user.getId());
        resmap.put("username",user.getUsername());
        resmap.put("token",token);
        return resmap;
    }

    @Override
    public UserInfo findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<UserInfo> findUserByName(String name) {
        List<UserInfo> userInfos = userDao.findUserByName("%"+name+"%");
        //appendLogInfo(userInfo);
        return userInfos;
    }

    //查找所有用户
    @Override
    public List<UserInfo> findAllUser(String online) {
        clearUserOffLine();
        return userDao.findAllUser(online);
    }
    //查找所有在线用户带日志登录信息
    @Override
    public List<UserInfo> findAllUserLog(String online) {
        clearUserOffLine();
        List<UserInfo> list = userDao.findAllUser(online);
        for (UserInfo user : list){
            appendLogInfo(user);
        }
        return list;
    }
    //添加日志信息
    public UserInfo appendLogInfo(UserInfo user){
        String addr = null;
        String name = null;
        String logintime = null;
        name = user.getUsername();
        String token = (String) redisTemplate.opsForValue().get(name);
        addr = (String) redisTemplate.opsForHash().get(token+"token","ip");
        logintime = (String) redisTemplate.opsForHash().get(token+"token","loginTime");
        user.setIpAddr(addr);
        user.setLoginTime(logintime);
        return user;
    }
    //添加移动端日志信息
    public UserInfo appendMobileLogInfo(UserInfo user){
        String addr = null;
        String name = null;
        String logintime = null;
        name = user.getUsername();
        addr = (String) redisTemplate.opsForHash().get(name+"mobtoken","ip");
        logintime = (String) redisTemplate.opsForHash().get(name+"mobtoken","loginTime");
        user.setIpAddr(addr);
        user.setLoginTime(logintime);
        return user;
    }
    //清理超时掉线用户
    public void clearUserOffLine(){
        Set<String> loginUsers = redisTemplate.opsForSet().members("loginUser");
        for (String name : loginUsers){
            String token = (String) redisTemplate.opsForValue().get(name);
            if(token != null){
                Boolean isexist = redisTemplate.hasKey(token);
                if (!isexist) {
                    if (redisTemplate.hasKey(name))  redisTemplate.delete(name);
                    if (redisTemplate.hasKey(token+"token"))  redisTemplate.delete(token+"token");
                    userDao.logout(name);
                    redisTemplate.opsForSet().remove("loginUser",name);
                }
            }else {
                userDao.logout(name);
                redisTemplate.opsForSet().remove("loginUser",name);
            }
        }
    }

    @Override
    public void updateUserStatu(UserInfo user) {
        userDao.updateUser(user);
        String name = user.getUsername();
        String token = (String) redisTemplate.opsForValue().get(name);
        if(token != null && redisTemplate.hasKey(token)){
            redisTemplate.delete(token);
        }
    }

    @Override
    public void updateUser(UserInfo user, List<String> roles) {
        userDao.updateUser(user);
        userDao.deleteURmap(Integer.parseInt(user.getId()));
        for (String roleId : roles){
            userDao.insertURmap(Integer.parseInt(user.getId()),Integer.parseInt(roleId));
        }
    }

    @Override
    public void addUser(UserInfo user, List<String> roles) {
        userDao.addUser(user);
        for (String roleId : roles){
            userDao.insertURmap(Integer.parseInt(user.getId()),Integer.parseInt(roleId));
        }
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteUser(id);
        userDao.deleteURmap(id);
    }

    @Override
    public Role findRoleById(Integer id) {
        return userDao.findRoleById(id);
    }

    @Override
   // @Cacheable(key = "#orgrole")
    public List<Role> findAllRole() {
          List<Role> rolelist = userDao.findAllRole();
          return rolelist;
    }

    @Override
    //@Cacheable(key = "#modrole")
    public List<Role> findAllModRole() {
        List<Role> roles = userDao.findAllModRole();
        for (Role role : roles){
            List<Right> rights = userDao.findRightByRoleId(Integer.parseInt(role.getId()));
            role.setRights(rights);
            for (Right right : rights){
                List<Right> subrights = userDao.findSubRightByRightId(Integer.parseInt(role.getId()),Integer.parseInt(right.getId()));
                right.setSubRight(subrights);
            }
        }
        return roles;
    }


    @Override
   // @Cacheable(key = "#rightstree")
    public List<Right> findAllRight() {
        return userDao.findAllRight();
    }
    //@Cacheable(key = "#rightslist")
    public List<Right> findAllRightlist() {
        List<Right> list = findAllRight();
        List<Right> res = new ArrayList<>();
        for(Right right : list){
            res.add(right);
            for(Right subright : right.getSubRight()){
                res.add(subright);
            }
        }
        return res;
    }

    @Override
    public List<Right> findRoleRight(String roleid) {
        List<Right> rights = userDao.findRightByRoleId(Integer.parseInt(roleid));
        List<Right> subrights=null;
        for (Right right : rights){
            int id = Integer.parseInt(right.getId());
            subrights = userDao.findSubRightByRightId(Integer.parseInt(roleid),id);
            right.setSubRight(subrights);
        }
        return rights;
    }

    @Override
    //@CacheEvict(key = "#modrole")
    public void deleteRight(String roleid, String rightid, String subrightid) {
        if (subrightid==null){
            userDao.deleteRight(Integer.parseInt(roleid),Integer.parseInt(rightid));
            return;
        }
       userDao.deleteRight2(Integer.parseInt(roleid),Integer.parseInt(rightid),Integer.parseInt(subrightid));
    }

    @Override
    public void assignmentRight(String rid, List<String> subrightid) {
        userDao.deleteRoleAllRight(Integer.parseInt(rid));
        for (String subid : subrightid){
            insertRRmap(rid,userDao.findRightbuysubRight(subid),subid);
        }
    }
    @Override
    public void logout(String name,HttpServletRequest request){
        String token = (String) redisTemplate.opsForValue().get(name);
        String ip = request.getRemoteAddr();
        String url = request.getServletPath();
        if(token != null)   request.getServletContext().removeAttribute(token);
        if (token != null && redisTemplate.hasKey(token))  redisTemplate.delete(token);
        if (name != null && redisTemplate.hasKey(name))   redisTemplate.delete(name);
        if (redisTemplate.hasKey(token+"token"))  redisTemplate.delete(token+"token");
        if (redisTemplate.opsForSet().isMember("loginUser",name))  redisTemplate.opsForSet().remove("loginUser",name);
        redisTemplate.opsForList().leftPush(name+"log",url+"="+dateFormater.format(new Date())+"="+ip);
        userDao.logout(name);
    }
    @Override
    public void insertRRmap(String id,String rid,String sid) {
         userDao.insertRRmap(Integer.parseInt(rid),Integer.parseInt(id),Integer.parseInt(sid));
    }
    public void createRightTable(){
        List<Role> roles = findAllRole();
        List<Right> rights;
        for(Role role : roles){
            String id =role.getId();
            for(Right right: role.getRights()){
                String rid = right.getId();
                for (Right subright : right.getSubRight()){
                    String sid = subright.getId();
                    insertRRmap(id,rid,sid);
                }
            }
        }
    }

    @Override
    public List<Menu> getUsersRights(String token) {
        return null;
    }
}
