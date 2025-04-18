package com.sharipov.topuch.domain.service;

import com.sharipov.topuch.domain.entity.Post;

import java.util.List;

public interface PostService {


    List<Post> getAllPosts();

    Post getPostById(Long id);

    Post createPost(Post post);

    Post updatePost(Post post);

    void deletePostById(Long id);

    void deleteOldPost();


}
