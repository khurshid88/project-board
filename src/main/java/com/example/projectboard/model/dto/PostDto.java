package com.example.projectboard.model.dto;

import com.example.projectboard.model.entity.Hashtag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class PostDto {
    @NotBlank(message = "title is required")
    @Size(min = 5, max = 20)
    private String title;

    @NotBlank(message = "content is required")
    @Size(min = 5, max = 500)
    private String content;
    private Set<Hashtag> hashtags = new LinkedHashSet<>();

    protected PostDto(){}

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
