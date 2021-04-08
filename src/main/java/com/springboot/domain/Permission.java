package com.springboot.domain;

import java.util.List;

public class Permission {
    private String id;
    private String permissionName;
    private String url;
    private Integer level;
    private List<Role> roles;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Permission() {
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id='" + id + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", url='" + url + '\'' +
                ", level=" + level +
                ", roles=" + roles +
                '}';
    }
}
