package com.springboot.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Menu implements Serializable {
    private Integer id;
    private String authName;
    private String path;
    private List<Menu> children;

    public Menu() {
    }

    public Menu(Integer id, String authName, String path, List<Menu> children) {
        this.id = id;
        this.authName = authName;
        this.path = path;
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        Menu menu = (Menu) o;
        return getAuthName().equals(menu.getAuthName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthName());
    }
}
