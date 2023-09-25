package com.example.projectboard.repository;

import com.example.projectboard.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Override
    Page<Post> findAll(Pageable pageable);

    @Query(nativeQuery = true, value = """
            select *
            from post
            where title = :keyword;
            """)
    List<Post> searchAll(@Param("keyword") String keyword);


}