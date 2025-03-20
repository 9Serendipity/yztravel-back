package com.example.controller;

import com.example.common.Result;
import com.example.service.CommentsService;
import com.example.tables.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    
    @Autowired
    private CommentsService commentsService;

    @GetMapping("/{attractionId}")
    public Result getComments(@PathVariable Integer attractionId) {
        return commentsService.getCommentsByAttractionId(attractionId);
    }

    @PostMapping("/add")
    public Result addComment(@RequestBody Comments comment) {
        return commentsService.addComment(comment);
    }
}