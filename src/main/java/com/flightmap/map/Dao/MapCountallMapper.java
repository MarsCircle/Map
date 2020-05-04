package com.flightmap.map.Dao;

import com.flightmap.map.Pojo.MapCountall;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.jmx.export.annotation.ManagedOperation;

@Mapper
public interface MapCountallMapper {
    int deleteByPrimaryKey(Long countallId);

    int insert(MapCountall record);

    int insertSelective(MapCountall record);

    MapCountall selectByPrimaryKey(Long countallId);

    int updateByPrimaryKeySelective(MapCountall record);

    int updateByPrimaryKey(MapCountall record);

    MapCountall selectByTime(String dateToday);


    int updateByTime(String jsonDataCurrent, String jsonDataToday, String allCountToday, String countCurrent, String dateToday);
}