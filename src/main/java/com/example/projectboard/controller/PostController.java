package com.example.projectboard.controller;

import com.example.projectboard.model.dto.CustomResponse;
import com.example.projectboard.model.entity.Post;
import com.example.projectboard.model.dto.PostDto;
import com.example.projectboard.model.projection.PostProjection;
import com.example.projectboard.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public CustomResponse getAllPosts(){
        List<Post> posts = postService.findAll();
        return CustomResponse.ok(posts);
    }

//    @GetMapping("/posts/{id}")
//    public ResponseEntity<?> getPostById(@PathVariable("id") Long id){
//        Post _post = postService.findById(id);
//        if(_post != null){
//            return new ResponseEntity<>(_post, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @GetMapping("/posts/{id}")
    public CustomResponse getPostById(@PathVariable("id") Long id){
        Post _post = postService.findById2(id);
        return CustomResponse.ok(_post);
    }

    @PostMapping("/posts")
    public CustomResponse createPost(@Valid @RequestBody PostDto postDto){
        Post _post = postService.createPost(postDto);
        return CustomResponse.ok(_post);
    }

    @PutMapping("/posts/{id}")
    public CustomResponse updatePost(@PathVariable("id") Long id, @RequestBody PostDto postDto){
        Post _post = postService.updatePost(id, postDto);
        return CustomResponse.ok(_post);
    }

    @DeleteMapping("/posts/{id}")
    public CustomResponse deletePost(@PathVariable("id") Long id){
        postService.deletePost(id);
        return CustomResponse.ok();
    }

    @GetMapping("/posts/paging")
    public CustomResponse findAllByPaging(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
        Pageable paging = PageRequest.of(page, size);
        Page<Post> posts = postService.findAllByPaging(paging);
        if(posts.isEmpty())
            return CustomResponse.error("No posts");
        return CustomResponse.ok(posts);
    }

    @GetMapping("/posts/search")
    public CustomResponse searchByKeyword(@RequestParam String keyword){

        List<Post> posts = postService.searchByKeyword(keyword);
        if(posts.isEmpty())
            return CustomResponse.error("No posts");
        return CustomResponse.ok(posts);
    }

    @GetMapping("/posts/load")
    public CustomResponse loadPosts(){

        List<PostProjection> posts = postService.loadPosts();
        if(posts.isEmpty())
            return CustomResponse.error("No posts");
        return CustomResponse.ok(posts);
    }

}
