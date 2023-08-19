package com.springboot.blog.service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;

import java.util.List;
import java.util.Optional;

public interface PostService {
    public PostDto save(PostDto postDto);
    public PostResponse findAll(int pageSize, int pageNo);
    public PostDto updatePost(PostDto postDto, int postId);
    public PostDto getPost(int postId);
    public String deletePost(int postId);
}
