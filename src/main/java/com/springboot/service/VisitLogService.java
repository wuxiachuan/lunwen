package com.springboot.service;

import com.springboot.domain.VisitLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VisitLogService {
    void storeLog2Db();
    List<VisitLog> getLog(String name,String dateFrom,String dateTo);
    List<VisitLog> getLogAll(String name);
}
