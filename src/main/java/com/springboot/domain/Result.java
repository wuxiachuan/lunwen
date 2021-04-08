package com.springboot.domain;

import java.io.Serializable;

public class Result implements Serializable {
    private Object object;
    private String message;
    private Integer code;

    public Result() {
    }

    public Result(Object object, String message, Integer code) {
        this.object = object;
        this.message = message;
        this.code = code;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
