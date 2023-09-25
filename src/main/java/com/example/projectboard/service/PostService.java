package com.example.projectboard.service;

import com.example.projectboard.domain.Post;
import com.example.projectboard.dto.PostDto;
import com.example.projectboard.exception.PostNotFoundException;
import com.example.projectboard.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public Post findById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if(optionalPost.isPresent()){
            return optionalPost.get();
        }
        return null;
    }

    public Post findById2(Long id) {
        Post _post = postRepository.findById(id)
                .orElseThrow(()-> new PostNotFoundException("Not found Post with id = " + id));
        return _post;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post createPost(PostDto postDto) {
        Post _post = Post.of(postDto.getTitle(), postDto.getContent());
        postRepository.save(_post);
        return _post;
    }

    public Post updatePost(Long id, PostDto postDto) {
        Post _post = postRepository.findById(id).orElseThrow();
        _post.setTitle(postDto.getTitle());
        _post.setContent(postDto.getContent());

        postRepository.save(_post);
        return _post;
    }

    public void deletePost(Long id) {
        Post _post = postRepository.findById(id).orElseThrow();
        postRepository.delete(_post);
    }

    public void deleteAll() {
        postRepository.deleteAll();
    }

    public Page<Post> paging(Pageable paging) {
        return postRepository.findAll(paging);
    }
}
