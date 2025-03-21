package com.example.mapper;

import com.example.tables.Spots;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SpotsMapper {
    @Select("SELECT * FROM spots")
    List<Spots> selectAll();

    Spots selectById(Integer spotId);
    
    // 添加根据名称查询的方法
    @Select("SELECT * FROM spots WHERE attractionName = #{name}")
    Spots selectByName(String name);
}