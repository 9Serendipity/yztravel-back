package com.example.mapper;

import com.example.tables.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface CommentsMapper {
    List<Comments> getCommentsByAttractionId(Integer attractionId);
    int addComment(Comments comment);
}