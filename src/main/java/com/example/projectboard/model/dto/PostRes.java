package com.example.projectboard.model.dto;

import com.example.projectboard.model.entity.Hashtag;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class PostRes {

    private String title;

    private String content;

    private Set<HashtagRes> hashtags = new LinkedHashSet<>();

    protected PostRes(){}

    private PostRes(String title, String content) {
        this.title = title;
        this.content = content;
    }

    private PostRes(String title, String content, Set<HashtagRes> hashtags) {
        this.title = title;
        this.content = content;
        this.hashtags = hashtags;
    }

    public static PostRes of(String title, String content, Set<HashtagRes> hashtags) {
        return new PostRes(title, content, hashtags);
    }
}
