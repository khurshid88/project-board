package com.example.projectboard.repository;

import com.example.projectboard.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Post, Long> {
}