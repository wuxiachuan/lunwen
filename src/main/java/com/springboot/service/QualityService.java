package com.springboot.service;

public interface QualityService {
    void modifyInfo(String database,Object data);
    void finishInspection(String name,String id);
}
