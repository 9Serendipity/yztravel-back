package com.example.tables;

import lombok.Data;

@Data
public class AttractionStates {
    private Integer attractionId;
    private Integer visitCount;
    private Double totalRating;
    private Integer ratingCount;
    private Double avgRating;
    private Integer spotsId;
}