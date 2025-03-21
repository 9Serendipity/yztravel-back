package com.example.service;

import com.example.common.Result;
import java.util.List;
import java.util.Map;

public interface SpotsService {
    Result getRecommendations(Integer usersId, List<String> types, 
                            Integer duration, Map<String, Double> budget);

    Result getSpotById(Integer spotId);
    
    // 添加根据名称获取景点的方法
    Result getSpotByName(String name);

    Result getAllSpotsWithStats();
}