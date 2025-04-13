package com.sharipov.topuch.service;


import com.sharipov.topuch.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl {

    private final PostRepository repository;

    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }



}
