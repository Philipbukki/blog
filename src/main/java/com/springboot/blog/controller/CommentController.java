package com.springboot.blog.controller;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{post_id}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable("post_id") int postId,
                                     @RequestBody CommentDto commentDto){

       return  new ResponseEntity<>(commentService.addComment(postId,commentDto), HttpStatus.CREATED);
    }
    @GetMapping("/posts/{post_id}/comments")
    public List<CommentDto> getAllCommentsByPost(@PathVariable("post_id") int postId){
        return commentService.getAllCommentsByPost(postId);
    }

    @GetMapping("/posts/{post_id}/comments/{comment_id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("post_id") int postId,
                                                        @PathVariable("comment_id") int comment_id){
        return ResponseEntity.ok(commentService.getCommentById(postId,comment_id));
    }

    @PutMapping("/posts/{post_id}/comments/{comment_id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("post_id") int postId, @PathVariable("comment_id") int comment_id,
    @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.updateComment(postId,comment_id,commentDto));
    }

    @DeleteMapping("/posts/{post_id}/comments/{comment_id}")
    public String deleteComment(@PathVariable("post_id") int postId,
                                                    @PathVariable("comment_id") int comment_id){
        return (commentService.deleteComment(postId,comment_id));
    }

}
