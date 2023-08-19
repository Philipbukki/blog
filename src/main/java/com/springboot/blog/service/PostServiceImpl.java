package com.springboot.blog.service;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto save(PostDto postDto) {
//        convert Dto to entity
        Post post = mapDtoToPost(postDto);
        Post newPost = postRepository.save(post);
        return mapPostToDto(newPost);
    }

    private Post mapDtoToPost(PostDto postDto){
        Post post = new Post();
        post.setId(post.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;

    }

    private PostDto mapPostToDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    @Override
    public PostResponse findAll(int pageSize, int pageNo, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> allPosts= posts.getContent();

        List<PostDto> content = allPosts.stream().map(this::mapPostToDto).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements(posts.getNumberOfElements());
        postResponse.setLast(posts.isLast());
        postResponse.setPageSize(posts.getSize());

        return postResponse;

    }

    @Override
    public PostDto updatePost (PostDto postDto, int postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);

        return mapPostToDto(updatedPost);

    }
    @Override
    public PostDto getPost(int postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        return mapPostToDto(post);
    }

    @Override
    public String deletePost(int postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        postRepository.delete(post);
        return "Post deleted successfully";
    }
}
