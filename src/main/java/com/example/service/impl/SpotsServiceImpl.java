package com.example.service.impl;

import com.example.common.Result;
import com.example.mapper.SpotsMapper;
import com.example.service.SpotsService;
import com.example.tables.Spots;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SpotsServiceImpl implements SpotsService {
    
    @Autowired
    private SpotsMapper spotsMapper;

    @Override
    public Result getRecommendations(Integer usersId, List<String> types, 
                                   Integer duration, Map<String, Double> budget) {
        // 输出参数值
        System.out.println("接收到的参数：");
        System.out.println("usersId: " + usersId);
        System.out.println("types: " + types);
        System.out.println("duration: " + duration);
        System.out.println("budget: " + budget);

        // 获取所有景点
        List<Spots> allSpots = spotsMapper.selectAll();
        if (allSpots == null || allSpots.isEmpty()) {
            return Result.error("暂无景点数据");
        }
        
        // 打印每个景点的详细信息
        System.out.println("\n获取到的景点数据：");
        allSpots.forEach(spot -> {
            System.out.println("景点名称: " + spot.getAttractionName());
            System.out.println("分类: " + spot.getClassify());
            System.out.println("游玩时间: " + spot.getTravelTime());
            System.out.println("价格: " + spot.getAttractionPrice());
            System.out.println("------------------------");
        });
        
        // 根据用户偏好过滤景点
        List<Spots> filteredSpots = allSpots.stream()
            .filter(spot -> {
                if (spot == null || spot.getClassify() == null || 
                    spot.getTravelTime() == null || spot.getAttractionPrice() == null) {
                    System.out.println("景点数据不完整: " + spot.getAttractionName());
                    return false;
                }
                
                // 类型匹配
                boolean typeMatch = types.contains(spot.getClassify());
                System.out.println(spot.getAttractionName() + " 类型匹配: " + typeMatch + 
                                 " (景点类型: " + spot.getClassify() + ")");
                
                // 时长匹配
                boolean durationMatch = Math.abs(spot.getTravelTime() - duration) <= 2;
                System.out.println(spot.getAttractionName() + " 时长匹配: " + durationMatch);
                
                // 价格匹配
                boolean priceMatch = spot.getAttractionPrice() >= budget.get("min") 
                                   && spot.getAttractionPrice() <= budget.get("max");
                System.out.println(spot.getAttractionName() + " 价格匹配: " + priceMatch);
                
                return typeMatch && durationMatch && priceMatch;
            })
            .collect(Collectors.toList());

        if (filteredSpots.isEmpty()) {
            return Result.error("没有找到符合条件的景点");
        }

        // 计算推荐得分并排序
        List<Map<String, Object>> recommendations = filteredSpots.stream()
            .map(spot -> {
                // 为每个景点创建一个新的Map来存储景点信息和得分
                Map<String, Object> recommendation = new HashMap<>();
                recommendation.put("spot", spot);        // 存储景点对象
                
                // 计算并存储得分
                double score = calculateScore(spot, types, duration, budget);
                recommendation.put("score", score);      // 存储计算出的得分
                
                return recommendation;
            })
            // 根据得分降序排序（分数高的排在前面）
            .sorted((r1, r2) -> Double.compare((Double) r2.get("score"), 
                                             (Double) r1.get("score")))
            .collect(Collectors.toList());              // 收集结果到List

        // 将排序后的推荐列表包装在Result对象中返回
        return Result.success(recommendations);
    }

    private double calculateScore(Spots spot, List<String> types, 
                                Integer duration, Map<String, Double> budget) {
        double score = 0.0;
        
        // 类型匹配权重 40%
        if (types.contains(spot.getClassify())) {
            score += 0.4;
        }
        
        // 时长匹配权重 30%
        double durationDiff = Math.abs(spot.getTravelTime() - duration);
        double durationScore = Math.max(0, 1 - durationDiff / 5.0);  // 最大允许5小时差异
        score += 0.3 * durationScore;
        
        // 价格匹配权重 30%
        double priceRange = budget.get("max") - budget.get("min");
        double priceScore = 1 - Math.abs(spot.getAttractionPrice() - 
                           (budget.get("max") + budget.get("min")) / 2) / priceRange;
        score += 0.3 * Math.max(0, priceScore);
        
        return score;
    }

    @Override
        public Result getSpotById(Integer spotId) {
            if (spotId == null) {
                return Result.error("景点ID不能为空");
            }
            
            Spots spot = spotsMapper.selectById(spotId);
            if (spot == null) {
                return Result.error("未找到该景点");
            }
            
            return Result.success(spot);
        }
}