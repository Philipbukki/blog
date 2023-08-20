package com.springboot.blog.repository;

import com.springboot.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

//    find all comments belonging to a post;
    public List<Comment> findAllByPost_id(int postId);

    public Comment findCommentByPost_idAndId(int postId, int commentId);



}
