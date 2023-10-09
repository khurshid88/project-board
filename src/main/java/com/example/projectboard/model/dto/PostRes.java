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

}
