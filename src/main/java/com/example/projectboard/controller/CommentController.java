package com.example.projectboard.controller;

import com.example.projectboard.model.common.Header;
import com.example.projectboard.model.dto.CommentReq;
import com.example.projectboard.model.dto.UserAccountReq;
import com.example.projectboard.model.entity.Comment;
import com.example.projectboard.model.entity.Post;
import com.example.projectboard.model.entity.UserAccount;
import com.example.projectboard.service.CommentService;
import com.example.projectboard.service.UserAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/comments")
    public Header<?> getComments(){
        List<Comment> _comments = commentService.findAll();
        return Header.ok(_comments);
    }

    @GetMapping("/comments/{id}")
    public Header<?> getCommentById(@PathVariable("id") Long id){
        Comment _comment = commentService.getCommentById(id);
        return Header.ok(_comment);
    }

}
