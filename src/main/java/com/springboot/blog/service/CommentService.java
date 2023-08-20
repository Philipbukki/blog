package com.springboot.blog.service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.payload.PostDto;

import java.util.List;
import java.util.Set;

public interface CommentService {
    public CommentDto addComment(int postId, CommentDto commentDto);
    public List<CommentDto> getAllCommentsByPost(int postId);

    public CommentDto getCommentById(int postId,int commentId);
    public CommentDto updateComment(int postId,int commentId, CommentDto commentDto);
    public String deleteComment(int postId,int commentId);
}
