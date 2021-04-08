package com.springboot.domain;

import java.io.Serializable;
import java.util.List;

public class Right implements Serializable {
    private String id;
    private String permissionName;
    private String url;
    private Integer level;
    private List<Right> subRight;
    private List<Role> roles;

    public Right() {
    }

    public Right(String id, String permissionName, String url, Integer level, List<Right> subRight, List<Role> roles) {
        this.id = id;
        this.permissionName = permissionName;
        this.url = url;
        this.level = level;
        this.subRight = subRight;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<Right> getSubRight() {
        return subRight;
    }

    public void setSubRight(List<Right> subRight) {
        this.subRight = subRight;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Right{" +
                "id='" + id + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", url='" + url + '\'' +
                ", level=" + level +
                ", subRight=" + subRight +
                ", roles=" + roles +
                '}';
    }
}
