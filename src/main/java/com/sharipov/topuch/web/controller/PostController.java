package com.sharipov.topuch.web.controller;


import com.sharipov.topuch.domain.entity.Post;
import com.sharipov.topuch.domain.service.PostService;
import com.sharipov.topuch.application.dto.PostDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

//    @GetMapping
//    public ResponseEntity<List<PostDTO>> getAllPosts(){
//
//
//    }

    @GetMapping
    public Post getPostById(Long id) {
        return new Post();
    }
}
