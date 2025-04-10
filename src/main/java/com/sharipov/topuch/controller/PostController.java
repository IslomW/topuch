package com.sharipov.topuch.controller;


import com.sharipov.topuch.entity.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/post")


public class PostController {
@GetMapping
    public Post getPostById() {
        return new Post();
    }
}
