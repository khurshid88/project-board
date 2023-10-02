package com.example.projectboard.service;

import com.example.projectboard.model.common.Header;
import com.example.projectboard.model.dto.HashtagRes;
import com.example.projectboard.model.dto.PostRes;
import com.example.projectboard.model.entity.Hashtag;
import com.example.projectboard.model.entity.Post;
import com.example.projectboard.model.dto.PostReq;
import com.example.projectboard.exception.ResourceNotFoundException;
import com.example.projectboard.model.projection.PostProjection;
import com.example.projectboard.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    HashtagService hashtagService;

    public Post findById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }

    public Post findById2(Long id) {
        Post _post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Post with id = " + id));
        return _post;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post createPost(Header<PostReq> dto) {
        var postDto = dto.getData();
        // TODO: mapper should be implement to convert PostReq to Post
        Post _post = Post.of(postDto.getTitle(), postDto.getContent());

        for (Long hashtag_id : postDto.getHashtag_ids()) {
            Hashtag hashtag = hashtagService.getHashtag(hashtag_id);
            _post.addHashtag(hashtag);
        }

        return postRepository.save(_post);
    }

    public Post updatePost(Long id, Header<PostReq> dto) {
        Post _post = postRepository.findById(id).orElseThrow();
        var postDto = dto.getData();
        _post.setTitle(postDto.getTitle());
        _post.setContent(postDto.getContent());

        postRepository.save(_post);
        return _post;
    }

    public void deletePost(Long id) {
        Post _post = postRepository.findById(id).orElseThrow();
        postRepository.delete(_post);
    }

    public Page<Post> findAllByPaging(Pageable paging) {
        return postRepository.findAll(paging);
    }

    public List<Post> searchPostByKeyword(String keyword) {
        List<Post> posts = postRepository.searchPostByKeyword(keyword);
        return posts;
    }

    public List<PostProjection> loadPosts() {
        List<PostProjection> titles = postRepository.loadPosts();
        return titles;
    }
}
