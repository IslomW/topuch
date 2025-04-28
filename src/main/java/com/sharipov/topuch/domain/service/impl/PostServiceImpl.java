package com.sharipov.topuch.domain.service.impl;


import com.sharipov.topuch.domain.entity.Post;
import com.sharipov.topuch.domain.service.PostService;
import com.sharipov.topuch.domain.repository.PostRepository;
import com.sharipov.topuch.domain.exception.PostNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class PostServiceImpl implements PostService {
    private final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

    private final PostRepository repository;

    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        Post post = repository.findById(id).orElseThrow(()-> new PostNotFound(id));
        return post;
    }

    @Override
    public Post createPost(Post post) {
        post.setCreated_at(LocalDateTime.now());
        Post result = repository.save(post);
        return result;
    }

    @Override
    public Post updatePost(Long postId, Post post) {
        Post exist = getPostById(postId);

        if (exist.equals(post)) {
            return exist;
        }

        return repository.save(post);
    }

    @Override
    public void deletePostById(Long id) {
        Post exist = getPostById(id);

        repository.deleteById(id);

    }

    @Override
    public void deleteOldPost() {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);

        List<Long> oldPostsIds = repository.findAllIdsByCreateAtBefore(sevenDaysAgo);
        oldPostsIds.forEach(id ->  log.info("Deleting post withId: {}", id));

        repository.deleteAllOlderThan(sevenDaysAgo);


//        List<Post> posts = getAllPosts();
//
//        if (posts.isEmpty()){
//            throw  new PostNotFound("Posts not found");
//        }
//
//        List<Post> oldPosts = posts.stream()
//                .filter(post -> post.getCreated_at().isBefore(sevenDaysAgo))
//                .collect(Collectors.toList());
//
//        if (!oldPosts.isEmpty()){
//            for (Post post : oldPosts){
//                deletePostById(post.getPostId());
//            }
//        }

    }
}
