package com.sharipov.topuch.web.controller;


import com.sharipov.topuch.application.converter.PostMapper;
import com.sharipov.topuch.application.dto.request.PostRequestDTO;
import com.sharipov.topuch.application.dto.response.PostResponseDTO;
import com.sharipov.topuch.domain.entity.Post;
import com.sharipov.topuch.domain.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPosts(){
        List<Post> posts = postService.getAllPosts();

        return ResponseEntity.ok(postMapper.toDtoList(posts));

    }

    @GetMapping("{id}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable UUID id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(postMapper.toDto(post));
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO requestDTO){
        Post post = postMapper.toEntity(requestDTO);
        Post savedPost = postService.createPost(post, requestDTO.profileId());
        return ResponseEntity.status(HttpStatus.CREATED).body(postMapper.toDto(savedPost));

    }

    @PutMapping("{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable UUID id,
            @RequestBody PostRequestDTO postRequestDTO){

        Post post = postService.updatePost(id, postMapper.toEntity(postRequestDTO));

        return ResponseEntity.ok(postMapper.toDto(post));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id){
        postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }



    @PostMapping("/{postId}/like")
    public ResponseEntity<Void> likeOrUnlike(@PathVariable UUID postId, @RequestParam UUID userId) {
        postService.toggleLike(postId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}/likes")
    public ResponseEntity<Integer> getLikes(@PathVariable UUID postId) {
        int count = postService.getLikeCount(postId);
        return ResponseEntity.ok(count);
    }

}
