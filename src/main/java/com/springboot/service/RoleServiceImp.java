package com.springboot.service;

import com.alibaba.fastjson.JSON;
import com.springboot.dao.RoleDao;
import com.springboot.domain.Menu;
import com.springboot.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
@CacheConfig(cacheNames = "roles")
@Service
public class RoleServiceImp implements RoleService{
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Cacheable(key = "'allrole'")
    @Override
    public List<Role> findAllRole() {
        System.out.println("find all roles");
        return roleDao.findAllRole();
    }

    @CacheEvict(value = "roles",key = "'allrole'")
    @Override
    public void insertRole(Role role) {
        roleDao.insertRole(role);
    }

    @Cacheable(key = "#id")
    @Override
    public Role findById(int id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Menu> getmenus(String token){
        String rights = (String) redisTemplate.opsForValue().get(token+"right");
        List<Menu> menus = JSON.parseArray(rights,Menu.class);
        return menus;
    }
    //@Override
    public List<Menu> getmenus1(String token) {
        Menu addusere= new Menu(101,"添加用户","/adduser",null);
        Menu deleteuser = new Menu(102,"删除用户","/deleteuser",null);
        Menu modifyuser = new Menu(103,"修改用户","/modifyuser",null);
        List<Menu> usersub = new ArrayList<>();
        usersub.add(addusere);
        usersub.add(deleteuser);
        usersub.add(modifyuser);
        Menu usermanage = new Menu(100,"用户管理","",usersub);

        Menu addright= new Menu(201,"添加权限","/addAuthority",null);
        Menu deleteright = new Menu(202,"删除权限","/deleteAuthority",null);
        Menu modifyright = new Menu(203,"修改权限","/modifyAuthority",null);
        List<Menu> rightsub = new ArrayList<>();
        rightsub.add(addright);
        rightsub.add(deleteright);
        rightsub.add(modifyright);
        Menu rightmanage = new Menu(200,"权限管理","",rightsub);

        Menu information= new Menu(301,"信息采集","/information",null);
        Menu measure = new Menu(302,"轮对测量","/measure",null);
        Menu repairProcess = new Menu(303,"修程判断","/repairProcess",null);
        List<Menu> wheelsub = new ArrayList<>();
        wheelsub.add(information);
        wheelsub.add(measure);
//        wheelsub.add(repairProcess);
        Menu wheelTakein = new Menu(300,"轮对收入","",wheelsub);

        Menu appearanceInspection= new Menu(401,"外观检查","/appearanceInspection",null);
        Menu bearingUnCap = new Menu(402,"轴承开盖","/uncap",null);
        Menu bearingUnLoad = new Menu(403,"轴承退卸","/unload",null);
        Menu bearingNeck = new Menu(404,"轴颈测量","/neckDiameter",null);
        Menu bearingLoad = new Menu(406,"轴承压装","/load",null);
        Menu bearingCap = new Menu(407,"轴承关盖","/capping",null);
        Menu bearingTest = new Menu(408,"轴承磨合","/rollTest",null);
        List<Menu> bearingRepairsub = new ArrayList<>();
        bearingRepairsub.add(appearanceInspection);
        bearingRepairsub.add(bearingUnCap);
        bearingRepairsub.add(bearingUnLoad);
        bearingRepairsub.add(bearingNeck);
        bearingRepairsub.add(bearingLoad);
        bearingRepairsub.add(bearingCap);
        bearingRepairsub.add(bearingTest);
        Menu bearingRepair = new Menu(400,"轴承检修","",bearingRepairsub);

        Menu wheelRounding= new Menu(501,"车轮旋面","/wheelRounding",null);
        List<Menu> wheelRoundingsub = new ArrayList<>();
        wheelRoundingsub.add(wheelRounding);
        Menu wheelRound = new Menu(500,"车轮旋面","",wheelRoundingsub);

        Menu wheelpolish= new Menu(601,"轮对除锈","/wheelpolish",null);
        Menu magneticInspection= new Menu(602,"磁粉探伤","/magneticInspection",null);
        Menu ultrasonicInspection= new Menu(603,"超声波探伤","/ultrasonicInspection",null);
        Menu reinspection= new Menu(604,"人工复探","/reinspection",null);
        List<Menu> inspectionsub = new ArrayList<>();
        inspectionsub.add(wheelpolish);
        inspectionsub.add(magneticInspection);
        inspectionsub.add(ultrasonicInspection);
        inspectionsub.add(reinspection);
        Menu inspection = new Menu(600,"轮对探伤","",inspectionsub);

        Menu qualityInspection= new Menu(701,"质量检查","/qualityInspection",null);
        Menu verify= new Menu(702,"验收审核","/verify",null);
        Menu qualityFeedback= new Menu(703,"故障反馈","/qualityFeedback",null);
        List<Menu> qualitysub = new ArrayList<>();
        qualitysub.add(qualityInspection);
        qualitysub.add(verify);
        qualitysub.add(qualityFeedback);
        Menu quality = new Menu(700,"质检验收","",qualitysub);

        Menu remeasure= new Menu(801,"支出测量","/remeasure",null);
        Menu matching = new Menu(802,"轮对选配","/matching",null);
        List<Menu> wheelDispatchsub = new ArrayList<>();
        wheelDispatchsub.add(remeasure);
        wheelDispatchsub.add(matching);
        Menu wheelDispatch = new Menu(800,"轮对支出","",wheelDispatchsub);

        Menu wheel= new Menu(901,"轮对管理","/wheel",null);
        Menu query = new Menu(902,"统计查询","/query",null);
        Menu monitor = new Menu(903,"生产监控","/monitor",null);
        Menu sheet = new Menu(904,"报表打印","/sheet",null);
        List<Menu> managesub = new ArrayList<>();
        managesub.add(wheel);
        managesub.add(query);
        managesub.add(monitor);
        managesub.add(sheet);
        Menu manage = new Menu(900,"综合管理","",managesub);

        Menu plan= new Menu(1001,"制定计划","/planMaking",null);
        List<Menu> plansub = new ArrayList<>();
        plansub.add(plan);
        Menu planmaking = new Menu(1000,"生产计划","",plansub);

        List<Menu> menus = new ArrayList<>();
        menus.add(usermanage);
        menus.add(rightmanage);
        menus.add(planmaking);
        menus.add(wheelTakein);
        menus.add(bearingRepair);
        menus.add(inspection);
        menus.add(wheelRound);
        menus.add(wheelDispatch);
        menus.add(quality);
        menus.add(manage);
        return menus;
    }

    @Override
    public List<String> allRoleNames() {
        return roleDao.allRoleNames();
    }

}
