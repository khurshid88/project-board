package com.example.projectboard.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HashtagReq {
    @NotBlank(message = "hashtag is required")
    @Size(min = 3, max = 20)
    private String hashtagName;

}
