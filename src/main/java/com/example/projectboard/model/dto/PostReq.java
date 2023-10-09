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


}
