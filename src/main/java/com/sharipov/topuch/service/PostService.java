package com.sharipov.topuch.service;

import com.sharipov.topuch.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {


    List<Post> getAllPosts();

    Optional<Post> getPostById(Long id);

    Post createPost(Post post);

    Post updatePost(Post post);

    void deletePostById(Long id);

    void deleteOldPost();


}
