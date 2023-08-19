package com.springboot.blog.controller;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {
    private final PostService postService;

    @Autowired
    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public PostResponse getPosts(
            @RequestParam(value="pageSize", defaultValue = "3", required = false) int pageSize,
            @RequestParam(value="pageNo", defaultValue = "0", required = false) int pageNo
            ){
        return postService.findAll(pageSize, pageNo);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.save(postDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable("id") int postId){
        return ResponseEntity.ok(postService.getPost(postId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") int postId,@RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.updatePost(postDto,postId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") int postId){
        return new ResponseEntity<>(postService.deletePost(postId),HttpStatus.OK);
    }

}
