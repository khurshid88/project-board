package com.example.projectboard.service;

import com.example.projectboard.model.common.Header;
import com.example.projectboard.model.dto.CommentReq;
import com.example.projectboard.model.dto.UserAccountReq;
import com.example.projectboard.model.entity.Comment;
import com.example.projectboard.model.entity.Post;
import com.example.projectboard.model.entity.UserAccount;
import com.example.projectboard.repository.CommentRepository;
import com.example.projectboard.repository.PostRepository;
import com.example.projectboard.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    PostRepository postRepository;

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow();
    }

    public List<Comment> findAll() {
       return commentRepository.findAll();
    }
}
