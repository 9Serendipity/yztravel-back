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
    
}