package com.example.projectboard.repository;

import com.example.projectboard.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<Comment, Long> {
}