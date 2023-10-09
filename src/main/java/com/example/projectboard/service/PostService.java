package com.example.projectboard.service;

import com.example.projectboard.model.common.Header;
import com.example.projectboard.model.dto.CommentReq;
import com.example.projectboard.model.dto.HashtagRes;
import com.example.projectboard.model.dto.PostRes;
import com.example.projectboard.model.entity.Comment;
import com.example.projectboard.model.entity.Hashtag;
import com.example.projectboard.model.entity.Post;
import com.example.projectboard.model.dto.PostReq;
import com.example.projectboard.exception.ResourceNotFoundException;
import com.example.projectboard.model.entity.UserAccount;
import com.example.projectboard.model.projection.PostProjection;
import com.example.projectboard.repository.CommentRepository;
import com.example.projectboard.repository.PostRepository;
import com.example.projectboard.repository.UserAccountRepository;
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

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    public Post findById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }

    public Post findById2(Long id) {
        // TODO - convert entity to dto using mapper
        Post _post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Post with id = " + id));
        return _post;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post createPost(Header<PostReq> dto) {
        var postDto = dto.getData();
        // TODO - convert entity to dto using mapper
        Post _post = Post.of(postDto.getTitle(), postDto.getContent());

        for (Long hashtag_id : postDto.getHashtag_ids()) {
            Hashtag hashtag = hashtagService.getHashtag(hashtag_id);
            _post.addHashtag(hashtag);
        }

        return postRepository.save(_post);
    }

    public Post updatePost(Long id, Header<PostReq> dto) {
        // TODO - convert entity to dto using mapper
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
        // TODO - convert entity to dto using mapper
        List<Post> posts = postRepository.searchPostByKeyword(keyword);
        return posts;
    }

    public List<PostProjection> loadPosts() {
        List<PostProjection> titles = postRepository.loadPosts();
        return titles;
    }

    public Comment createPostComment(Long postId, Header<CommentReq> dto) {
        var commentDto = dto.getData();
        UserAccount userAccount = userAccountRepository.findByUserId(commentDto.getUserId()).orElseThrow();
        Post post = postRepository.findById(postId).orElseThrow();

        Comment comment = Comment.of(commentDto.getContent());
        comment.setPost(post);
        comment.setUserAccount(userAccount);
        return commentRepository.save(comment);
    }

    public Page<Comment> getPostComments(Long postId, Pageable paging) {
        Post post = postRepository.findById(postId).orElseThrow();
        return commentRepository.findByPostId(post.getId(), paging);
    }

    public Comment getPostComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow();
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        return comment;
    }

    public Comment updatePostComment(Long postId, Long commentId, Header<CommentReq> dto) {
        Post post = postRepository.findById(postId).orElseThrow();
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        comment.setContent(dto.getData().getContent());
        return commentRepository.save(comment);
    }

    public void deletePostComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow();
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        commentRepository.delete(comment);
    }

}
