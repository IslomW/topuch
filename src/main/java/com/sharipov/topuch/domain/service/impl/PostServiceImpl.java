package com.sharipov.topuch.domain.entity.service.impl;


import com.sharipov.topuch.domain.entity.Post;
import com.sharipov.topuch.domain.entity.service.PostService;
import com.sharipov.topuch.exception.PostNotFound;
import com.sharipov.topuch.exception.PostUnchangedException;
import com.sharipov.topuch.domain.entity.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


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
    public Optional<Post> getPostById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Post createPost(Post post) {
        Post result = repository.save(post);
        return result;
    }

    @Override
    public Post updatePost(Post post) {
        Post exist = getPostById(post.getPostId())
                .orElseThrow(() -> new PostNotFound("Post with Id:" + post.getPostId() + " not found"));

        if (exist.equals(post)) {
            throw new PostUnchangedException("Post with Id:" + post.getPostId() + " has not been changed");
        }

        return repository.save(post);
    }

    @Override
    public void deletePostById(Long id) {
        Post exist = getPostById(id)
                .orElseThrow(() -> new PostNotFound("Post with Id:" + id + " not found"));

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
