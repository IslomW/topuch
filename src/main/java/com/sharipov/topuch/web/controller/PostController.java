package com.sharipov.topuch.web.controller;


import com.sharipov.topuch.application.converter.PostMapper;
import com.sharipov.topuch.application.dto.request.PostRequestDTO;
import com.sharipov.topuch.application.dto.request.ProfileRequestDTO;
import com.sharipov.topuch.application.dto.response.PostResponseDTO;
import com.sharipov.topuch.domain.entity.Post;
import com.sharipov.topuch.domain.service.PostService;
import org.springframework.boot.convert.PeriodUnit;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/posts")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPosts(){
        List<Post> posts = postService.getAllPosts();

        return ResponseEntity.ok(postMapper.toDtoList(posts));

    }

    @GetMapping("{id}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(postMapper.toDto(post));
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO requestDTO){
        Post post = postMapper.toEntity(requestDTO);
        Post savedPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(postMapper.toDto(savedPost));

    }

    @PutMapping("{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable Long id,
            @RequestBody PostRequestDTO postRequestDTO){

        Post post = postService.updatePost(id, postMapper.toEntity(postRequestDTO));

        return ResponseEntity.ok(postMapper.toDto(post));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id){
        postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }



    @PostMapping("/{postId}/like")
    public ResponseEntity<Void> likeOrUnlike(@PathVariable Long postId, @RequestParam Long userId) {
        postService.toggleLike(postId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}/likes")
    public ResponseEntity<Integer> getLikes(@PathVariable Long postId) {
        int count = postService.getLikeCount(postId);
        return ResponseEntity.ok(count);
    }

}
