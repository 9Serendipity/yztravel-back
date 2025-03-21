package com.example.controller;

import com.example.common.Result;
import com.example.service.SpotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.tables.Spots;

@RestController
@RequestMapping("/spots")
public class SpotsController {
    @Autowired
    private SpotsService spotsService;
    
    @PostMapping("/recommend")
    public Result getRecommendations(@RequestBody Map<String, Object> request) {
        Integer usersId = (Integer) request.get("usersId");
        
        @SuppressWarnings("unchecked")
        Map<String, Object> preferences = (Map<String, Object>) request.get("preferences");
        
        @SuppressWarnings("unchecked")
        List<String> types = (List<String>) preferences.get("types");
        
        // duration 在 JSON 中是字符串，需要转换
        Integer duration = Integer.parseInt((String) preferences.get("duration"));
        
        @SuppressWarnings("unchecked")
        List<Integer> budgetList = (List<Integer>) preferences.get("budget");
        Map<String, Double> budget = new HashMap<>();
        // 将 Integer 转换为 Double
        budget.put("min", budgetList.get(0).doubleValue());
        budget.put("max", budgetList.get(1).doubleValue());
        
        return spotsService.getRecommendations(usersId, types, duration, budget);
    }

    @PostMapping("/attractions/{spotId}")
    public Result getSpotById(@PathVariable Integer spotId) {
        Result result = spotsService.getSpotById(spotId);
        
        // 添加检测代码
        System.out.println("获取到的景点数据：");
        if (result.getData() != null) {
            Spots spot = (Spots) result.getData();
            System.out.println("景点ID: " + spot.getAttractionId());
            System.out.println("景点名称: " + spot.getAttractionName());
            System.out.println("图片URLs: " + spot.getImageUrls());
        } else {
            System.out.println("未获取到景点数据");
        }
        
        return result;
    }

    @GetMapping("/attractions/name/{name}")
    public Result getSpotByName(@PathVariable String name) {
        Result result = spotsService.getSpotByName(name);
        
        // 添加日志
        System.out.println("根据名称获取景点数据：" + name);
        if (result.getData() != null) {
            System.out.println("找到景点数据");
        } else {
            System.out.println("未找到景点");
        }
        
        return result;
    }

    @GetMapping("/all")
    public Result getAllSpotsWithStats() {
        return spotsService.getAllSpotsWithStats();
    }
}