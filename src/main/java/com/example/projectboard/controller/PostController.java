package com.example.projectboard.controller;

import com.example.projectboard.model.common.Header;
import com.example.projectboard.model.common.PaginationData;
import com.example.projectboard.model.dto.CommentReq;
import com.example.projectboard.model.dto.PostRes;
import com.example.projectboard.model.entity.Comment;
import com.example.projectboard.model.entity.Post;
import com.example.projectboard.model.dto.PostReq;
import com.example.projectboard.model.projection.PostProjection;
import com.example.projectboard.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public Header<?> getAllPosts(){
        List<Post> posts = postService.findAll();
        return Header.ok(posts);
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
    public Header<?> getPostById(@PathVariable("id") Long id){
        Post _post = postService.findById2(id);
        return Header.ok(_post);
    }

    @PostMapping("/posts")
    public Header<?> createPost(@Valid @RequestBody Header<PostReq> dto){
        Post postRes = postService.createPost(dto);
        return Header.ok(postRes);
    }

    @PutMapping("/posts/{id}")
    public Header<?> updatePost(@PathVariable("id") Long id, @RequestBody Header<PostReq> dto){
        Post _post = postService.updatePost(id, dto);
        return Header.ok(_post);
    }

    @DeleteMapping("/posts/{id}")
    public Header<?> deletePost(@PathVariable("id") Long id){
        postService.deletePost(id);
        return Header.ok();
    }

    @GetMapping("/posts/paging")
    public Header<?> findAllByPaging(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Pageable paging = PageRequest.of(page, size);
        Page<Post> postPage = postService.findAllByPaging(paging);
        List<Post> posts = postPage.getContent();
        return Header.ok(posts, PaginationData.build(postPage));
    }

    @GetMapping("/posts/search")
    public Header<?> searchPostByKeyword(@RequestParam String keyword){

        List<Post> posts = postService.searchPostByKeyword(keyword);
        if(posts.isEmpty())
            return Header.error("No posts");
        return Header.ok(posts);
    }

    @GetMapping("/posts/load")
    public Header<?> loadPosts(){

        List<PostProjection> posts = postService.loadPosts();
        if(posts.isEmpty())
            return Header.error("No posts");
        return Header.ok(posts);
    }

    @PostMapping("/posts/{postId}/comments")
    public Header<?> createPostComment(@PathVariable Long postId, @Valid @RequestBody Header<CommentReq> dto){
        Comment _comment = postService.createPostComment(postId, dto);
        return Header.ok(_comment);
    }

    @GetMapping("/posts/{postId}/comments")
    public Header<?> getPostComments(@PathVariable Long postId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){

        Pageable paging = PageRequest.of(page, size);
        Page<Comment> commentPage = postService.getPostComments(postId, paging);
        List<Comment> comments = commentPage.getContent();
        return Header.ok(comments, PaginationData.build(commentPage));
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public Header<?> getPostComment(@PathVariable Long postId, @PathVariable Long commentId){

        Comment comment = postService.getPostComment(postId, commentId);
        return Header.ok(comment);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public Header<?> updatePostComment(@PathVariable("postId") Long postId, @PathVariable Long commentId, @RequestBody Header<CommentReq> dto){
        Comment _comment = postService.updatePostComment(postId, commentId, dto);
        return Header.ok(_comment);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public Header<?> deletePostComment(@PathVariable("postId") Long postId, @PathVariable Long commentId){
        postService.deletePostComment(postId, commentId);
        return Header.ok();
    }
}
