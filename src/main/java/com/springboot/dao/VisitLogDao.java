package com.springboot.dao;

import com.springboot.domain.VisitLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface VisitLogDao {
    void addLog(VisitLog log);
    void batchInsertLog(List<VisitLog> logs);
    List<VisitLog> getLog(@Param("name") String name, @Param("dateFrom")String dateFrom, @Param("dateTo")String dateTo);
    List<VisitLog> getLogAll(String name);
}
