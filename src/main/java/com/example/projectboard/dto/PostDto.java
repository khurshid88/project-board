package com.example.projectboard.dto;

import com.example.projectboard.domain.Hashtag;
import com.example.projectboard.domain.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class PostDto {
    private String title;
    private String content;
    private Set<Hashtag> hashtags = new LinkedHashSet<>();

    private PostDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    private PostDto(String title, String content, Set<Hashtag> hashtags) {
        this.title = title;
        this.content = content;
        this.hashtags = hashtags;
    }

    public static PostDto of(String title, String content, Set<Hashtag> hashtags) {
        return new PostDto(title, content, hashtags);
    }
}
