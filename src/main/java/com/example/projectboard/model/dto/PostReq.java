package com.example.projectboard.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class PostReq {
    @NotBlank(message = "title is required")
    @Size(min = 5, max = 20)
    private String title;

    @NotBlank(message = "content is required")
    @Size(min = 5, max = 500)
    private String content;

    private Set<Long> hashtag_ids = new LinkedHashSet<>();

    protected PostReq(){}

    private PostReq(String title, String content) {
        this.title = title;
        this.content = content;
    }

    private PostReq(String title, String content, Set<Long> hashtag_ids) {
        this.title = title;
        this.content = content;
        this.hashtag_ids = hashtag_ids;
    }

    public static PostReq of(String title, String content, Set<Long> hashtag_ids) {
        return new PostReq(title, content, hashtag_ids);
    }
}
