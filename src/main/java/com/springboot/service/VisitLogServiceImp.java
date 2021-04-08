package com.springboot.service;

import com.springboot.dao.VisitLogDao;
import com.springboot.domain.Result;
import com.springboot.domain.UserLogIn;
import com.springboot.domain.VisitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VisitLogServiceImp implements VisitLogService{
    @Autowired
    private VisitLogDao visitLogDao;
    @Autowired
    private RedisTemplate redisTemplate;

    public VisitLogServiceImp() {

    }
    @Override
    public void storeLog2Db() {
        Set<String> users = redisTemplate.opsForSet().members("loginUser");
        if (users==null||users.size()== 0){
            return;
        }
        for(String name : users){
            List<VisitLog> logs = findLogs(name);
            if (logs == null||logs.size()==0) continue;
            visitLogDao.batchInsertLog(logs);
        }
    }
    List<VisitLog> findLogs(String name){
        List<String> list = null;
        list = redisTemplate.opsForList().range(name+"log",0,-1);
        if (list==null||list.size()==0){
            return null;
        }
        redisTemplate.delete(name+"log");
        List<VisitLog> logs = new ArrayList<>();
        for (String str : list){
            String date = "",url = "",ip = "";
            String[] arr = str.split("=");
            if (arr.length < 2) continue;
            for (int i=0;i<arr.length;i++){
                if (i==0){
                    url = arr[0];
                    continue;
                }
                if (i==1){
                    date = arr[1];
                    continue;
                }
                if (i==2){
                    ip = arr[2];
                }
            }
           logs.add(new VisitLog(name,date,url,ip));
        }
        return logs;
    }

    @Override
    public List<VisitLog> getLog(String name, String dateFrom, String dateTo) {
        return null;
    }

    @Override
    public List<VisitLog> getLogAll(String name) {
        System.out.println("test...");
        return null;

    }
}
