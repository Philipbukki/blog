package com.springboot.blog.service;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogErrorException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto addComment(int postId, CommentDto commentDto) {

        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post","id",postId));

        Comment comment = DtoToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);

        return CommentToDto(comment);

    }

    @Override
    public List<CommentDto> getAllCommentsByPost(int postId) {

        List<Comment> comments = commentRepository.findAllByPost_id(postId);

        return comments.stream().map(this::CommentToDto).collect(Collectors.toList());

    }

    @Override
    public CommentDto getCommentById(int postId, int commentId) {
        Comment comment = commentRepository.findCommentByPost_idAndId(postId,commentId);
        return CommentToDto(comment);
    }

    @Override
    public CommentDto updateComment(int postId, int commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findCommentByPost_idAndId(postId,commentId);
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        commentRepository.save(comment);
        return CommentToDto(comment);
    }

    @Override
    public String deleteComment(int postId, int commentId) {


        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post","id",postId));


        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("Comment","id",commentId));

        if (comment.getPost().getId() != (post.getId())) {
            throw new BlogErrorException(HttpStatus.BAD_REQUEST, String.format("No such comment for post with id %d", postId));

        } else {
            commentRepository.delete(comment);

            return String.format("Comment  with id %s deleted successfully ", commentId);
        }

    }

    private CommentDto CommentToDto(Comment comment){

        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setBody(comment.getBody());
        commentDto.setEmail(comment.getEmail());
        return commentDto;

    }

    private Comment DtoToComment(CommentDto commentDto){

        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());

        return comment;

    }
}
