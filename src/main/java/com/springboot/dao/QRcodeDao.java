package com.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QRcodeDao {
   void insertQRcode(@Param("wheelId") Integer wheelId, @Param("name") String name,@Param("path") String path);
   String findQRcode(Integer wheelId);
   void deleteQRcode(String wheelId);
}
