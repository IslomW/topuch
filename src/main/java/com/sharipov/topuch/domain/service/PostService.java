package com.sharipov.topuch.domain.service;

import com.sharipov.topuch.domain.entity.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {


    List<Post> getAllPosts();

    Post getPostById(UUID id);

    Post createPost(Post post, UUID profileId);

    Post updatePost(UUID postId, Post post);

    void deletePostById(UUID id);

    void deleteOldPost();

    void toggleLike(UUID postId, UUID profileId);
    int getLikeCount(UUID postId);


}
