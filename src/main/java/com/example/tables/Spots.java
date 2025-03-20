package com.example.tables;

import lombok.Data;

import java.util.List;

@Data
public class Spots {
        private Integer attractionId;
        private String attractionName;
        private String attractionDescription;
        private String attractionLocal;
        private Double attractionPrice;
        private String label;
        private String classify;
        private Integer travelTime;
        private List<String> imageUrls;  // 新增图片URL列表
}
