package com.example.service.impl;

import com.example.common.Result;
import com.example.mapper.CommentsMapper;
import com.example.service.CommentsService;
import com.example.tables.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    
    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    public Result getCommentsByAttractionId(Integer attractionId) {
        if (attractionId == null) {
            return Result.error("景点ID不能为空");
        }
        List<Comments> comments = commentsMapper.getCommentsByAttractionId(attractionId);
        return Result.success(comments);
    }

    @Override
    public Result addComment(Comments comment) {
        // 参数验证
        if (comment.getContent() == null || comment.getContent().trim().isEmpty()) {
            return Result.error("评论内容不能为空");
        }
        if (comment.getRating() == null || comment.getRating() < 1 || comment.getRating() > 5) {
            return Result.error("评分必须在1-5之间");
        }
        if (comment.getUserId() == null) {
            return Result.error("用户未登录");
        }
        if (comment.getAttractionId() == null) {
            return Result.error("景点ID不能为空");
        }

        // 添加评论
        int rows = commentsMapper.addComment(comment);
        if (rows > 0) {
            return Result.success(comment);
        }
        return Result.error("评论发表失败");
    }
}