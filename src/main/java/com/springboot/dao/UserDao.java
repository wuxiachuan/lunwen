package com.springboot.dao;

import com.springboot.domain.Right;
import com.springboot.domain.Role;
import com.springboot.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserDao {
    UserInfo findUserById(Integer id);
    UserInfo findUserByNameAndPassword(@Param("name") String name,@Param("password") String paasword);
    List<UserInfo> findUserByName (String name);
    List<UserInfo> findAllUser(@Param("isonline") String isonline);
    void updateUser(UserInfo user);
    void addUser(UserInfo user);
    void deleteUser(Integer id);
    void login(String username);
    void logout(String username);

    Role findRoleById(Integer id);
    List<Role> findAllRole();
    List<Role> findAllModRole();

    void updateRole(Role role);
    void addRole(Role role);
    void deleteRole(Integer id);

    List<Right> findAllRight();
    List<Right> findRightByRoleId(Integer roleid);
    List<Right> findSubRightByRightId(@Param("rid") Integer roleid,@Param("id") Integer rightid);
    void deleteRoleAllRight(Integer roleid);
    void deleteRight(@Param("rid") Integer roleid,@Param("id") Integer rightid);
    void deleteRight2(@Param("rid") Integer roleid,@Param("id") Integer rightid,@Param("sid") Integer subrightid);


    void deleteURmap(Integer uid);
    void insertURmap(@Param("uid") Integer uid, @Param("rid") Integer rid);

    void deleteRPmap(Integer uid);
    void insertRPmap(@Param("pid") Integer pid,@Param("rid") Integer rid);

    void insertRRmap(@Param("rid") Integer rid,@Param("id") Integer id,@Param("sid") Integer sid);

    String findRightbuysubRight(String id);
    List<Integer> findAllSubrightid();
}
