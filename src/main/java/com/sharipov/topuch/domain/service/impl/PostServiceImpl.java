package com.sharipov.topuch.domain.service.impl;


import com.sharipov.topuch.common.exception.NotFoundException;
import com.sharipov.topuch.domain.entity.Post;
import com.sharipov.topuch.domain.entity.Profile;
import com.sharipov.topuch.domain.repository.PostRepository;
import com.sharipov.topuch.domain.repository.ProfileRepository;
import com.sharipov.topuch.domain.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ProfileRepository profileRepository;


    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(UUID id) {
        Post post = postRepository.findById(id).orElseThrow(NotFoundException::postNotFound);
        return post;
    }

    @Override
    public Post createPost(Post post, UUID profileId) {
        Profile seller = profileRepository.findById(profileId)
                .orElseThrow(NotFoundException::profileNotFound);
        post.setSeller(seller);
        post.setCreatedAt(Instant.now());
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(UUID postId, Post post) {
        Post exist = getPostById(postId);

        exist.setTitle(post.getTitle());
        exist.setDescription(post.getDescription());
        exist.setPrice(post.getPrice());
        exist.setImages(post.getImages());
        exist.setCondition(post.getCondition());
        exist.setCategory(post.getCategory());

        return postRepository.save(exist);
    }

    @Override
    public void deletePostById(UUID id) {
        Post exist = getPostById(id);

        postRepository.deleteById(id);

    }

    @Override
    public void deleteOldPost() {
        Instant sevenDaysAgo = Instant.now().minus(7, ChronoUnit.DAYS);

        List<UUID> oldPostsIds = postRepository.findAllIdsByCreateAtBefore(sevenDaysAgo);
        oldPostsIds.forEach(id ->  log.info("Deleting post withId: {}", id));

        postRepository.deleteAllOlderThan(sevenDaysAgo);


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

    @Override
    public void toggleLike(UUID postId, UUID profileId) {
        Post post = postRepository.findById(postId).orElseThrow(NotFoundException::postNotFound);
        Profile profile = profileRepository.findById(profileId).orElseThrow(
                NotFoundException::profileNotFound
        );

        if (post.getLikedByUser().contains(profile)){
            post.getLikedByUser().remove(profile);
        }else {
            post.getLikedByUser().add(profile);
        }

        postRepository.save(post);
    }

    @Override
    public int getLikeCount(UUID postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                NotFoundException::postNotFound
        );

        return post.getLikedByUser().size();
    }
}
