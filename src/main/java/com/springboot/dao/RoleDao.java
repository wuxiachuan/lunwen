package com.springboot.dao;


import com.springboot.domain.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao {
    List<Role> findAllRole();
    void insertRole(Role role);
    Role findById(int id);
    List<String> allRoleNames();
}
