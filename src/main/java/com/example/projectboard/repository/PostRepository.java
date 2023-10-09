package com.example.projectboard.repository;

import com.example.projectboard.model.entity.Post;
import com.example.projectboard.model.projection.PostProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Override
    Page<Post> findAll(Pageable pageable);

    @Query(nativeQuery = true, value = """
            select *
            from post
            where title like concat('%',:keyword,'%')
                        """)
    Page<Post> searchPostByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query(nativeQuery = true, value = """
            select title,content
            from post
            """)
    List<PostProjection> loadPosts();

}