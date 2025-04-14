package com.sharipov.topuch.domain.service.impl;

import com.sharipov.topuch.domain.entity.Post;
import com.sharipov.topuch.domain.service.PostService;

import java.util.List;
import java.util.Optional;

public class PostExample implements PostService {
    @Override
    public List<Post> getAllPosts() {
        return null;
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return Optional.empty();
    }

    @Override
    public Post createPost(Post post) {
        return null;
    }

    @Override
    public Post updatePost(Post post) {
        return null;
    }

    @Override
    public void deletePostById(Long id) {

    }

    @Override
    public void deleteOldPost() {

    }
}
