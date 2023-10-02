package com.example.projectboard.controller;

import com.example.projectboard.model.common.Header;
import com.example.projectboard.model.dto.HashtagReq;
import com.example.projectboard.model.entity.Hashtag;
import com.example.projectboard.model.projection.HashtagProjection;
import com.example.projectboard.service.HashtagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HashtagController {

    @Autowired
    HashtagService hashtagService;

    @GetMapping("/hashtags")
    public Header<?> getAllHashtags(){
        List<Hashtag> hashtags = hashtagService.findAll();
        return Header.ok(hashtags);
    }

    @GetMapping("/hashtags/{id}")
    public Header<?> getHashtag(@PathVariable("id") Long id){
        Hashtag hashtag = hashtagService.getHashtag(id);
        return Header.ok(hashtag);
    }

    @PostMapping("/hashtags")
    public Header<?> createHashtag(@Valid @RequestBody Header<HashtagReq> dto){
        Hashtag _hashtag = hashtagService.createHashtag(dto);
        return Header.ok(_hashtag);
    }

    @GetMapping("/hashtags/search")
    public Header<?> searchHashtagByKeyword(@RequestParam String keyword){

        List<HashtagProjection> hashtags = hashtagService.searchHashtagByKeyword(keyword);
        if(hashtags.isEmpty())
            return Header.error("No hashtags");
        return Header.ok(hashtags);
    }

    @DeleteMapping("/hashtags/{id}")
    public Header<?> deleteHashtag(@PathVariable("id") Long id){
        hashtagService.deleteHashtag(id);
        return Header.ok();
    }
}
