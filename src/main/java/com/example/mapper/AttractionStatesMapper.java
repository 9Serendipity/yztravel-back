package com.example.mapper;

import com.example.tables.AttractionStates;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AttractionStatesMapper {
    @Select("SELECT * FROM attraction_states WHERE attraction_id = #{attractionId}")
    AttractionStates getStatesBySpotId(Integer spotId);
}