package com.springboot.dao;

import com.springboot.domain.BearingUnLoad;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BearingUnLoadDao {
    void insertBearingUnLoad(BearingUnLoad bearingUnLoad);
    void updateBearingUnLoad(BearingUnLoad bearingUnLoad);
    List<BearingUnLoad> findBearingUnLoad();
    List<BearingUnLoad> findBearingUnLoadById(Integer wheelId);
}
