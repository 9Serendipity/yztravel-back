package com.example.tables;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Comments {
    private Integer id;
    private Integer attractionId;
    private Integer userId;
    private String username;
    private String content;
    private Integer rating;//评分
    private LocalDateTime createTime;
}