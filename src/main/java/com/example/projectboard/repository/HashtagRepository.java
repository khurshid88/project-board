package com.example.projectboard.repository;

import com.example.projectboard.model.entity.Hashtag;
import com.example.projectboard.model.projection.HashtagProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {


    @Query(nativeQuery = true, value = """
            select hashtag_name
            from hashtag
            where hashtag_name like concat('%',:keyword,'%')
                        """)
    List<HashtagProjection> searchHashtagByKeyword(String keyword);
}