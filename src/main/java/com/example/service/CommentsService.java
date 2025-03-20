package com.example.service;

import com.example.common.Result;
import com.example.tables.Comments;

public interface CommentsService {
    Result getCommentsByAttractionId(Integer attractionId);
    Result addComment(Comments comment);
}