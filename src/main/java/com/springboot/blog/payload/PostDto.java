package com.springboot.blog.payload;

import com.springboot.blog.entity.Comment;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Data
public class PostDto {
    private int id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments;
}
