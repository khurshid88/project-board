package com.example.projectboard.service;

import com.example.projectboard.model.common.Header;
import com.example.projectboard.model.dto.HashtagReq;
import com.example.projectboard.model.entity.Hashtag;
import com.example.projectboard.model.projection.HashtagProjection;
import com.example.projectboard.repository.HashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HashtagService {
    @Autowired
    HashtagRepository hashtagRepository;

    public List<Hashtag> findAll() {
        return hashtagRepository.findAll();
    }

    public Hashtag getHashtag(Long id) {
        // TODO - convert entity to dto using mapper
        Hashtag _hashtag = hashtagRepository.findById(id).orElseThrow();
        return _hashtag;
    }

    public Hashtag createHashtag(Header<HashtagReq> dto) {
        // TODO - convert entity to dto using mapper
        var hashtagDto = dto.getData();
        Hashtag hashtag = Hashtag.of(hashtagDto.getHashtagName());

        return hashtagRepository.save(hashtag);
    }

    public List<HashtagProjection> searchHashtagByKeyword(String keyword) {
        return hashtagRepository.searchHashtagByKeyword(keyword);
    }

    public void deleteHashtag(Long id) {
        Hashtag _hashtag = hashtagRepository.findById(id).orElseThrow();
        hashtagRepository.delete(_hashtag);
    }


}
