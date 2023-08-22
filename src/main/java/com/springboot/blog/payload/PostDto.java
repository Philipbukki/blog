package com.springboot.blog.payload;

import com.springboot.blog.entity.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Data
public class PostDto {
    private int id;
    @NotEmpty
    @Size(min=2,message = "title should have at least 2 characters")
    private String title;
    @NotEmpty
    @Size(min=10,message = "description should have at least 10 characters")
    private String description;
    @NotEmpty
    @Size(min=10,message = "content should have at least 10 characters")
    private String content;
    private Set<CommentDto> comments;
}
