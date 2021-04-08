package com.springboot.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UserLogIn implements Serializable {
    private String id;
    private String username;
    private List<Map<String,String>> logMap;

    public UserLogIn() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Map<String, String>> getLogMap() {
        return logMap;
    }

    public void setLogMap(List<Map<String, String>> logMap) {
        this.logMap = logMap;
    }
}
