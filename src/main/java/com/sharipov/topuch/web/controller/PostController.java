package com.sharipov.topuch.web.controller;


import com.sharipov.topuch.domain.entity.Post;
import com.sharipov.topuch.domain.entity.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/post")


public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Post getPostById(Long id) {

//        return postService.getPostById(id);
//

        return new Post();
    }
}
