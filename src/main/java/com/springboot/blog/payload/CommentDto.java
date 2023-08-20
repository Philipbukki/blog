package com.springboot.blog.payload;

import com.springboot.blog.entity.Post;
import lombok.Data;

@Data
public class CommentDto {
    private int id;
    private String body;
    private String email;
    private String name;
}
