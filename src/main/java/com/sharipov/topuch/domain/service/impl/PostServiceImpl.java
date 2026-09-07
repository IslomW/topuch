package com.sharipov.topuch.domain.service.impl;


import com.sharipov.topuch.domain.entity.Post;
import com.sharipov.topuch.domain.entity.Profile;
import com.sharipov.topuch.domain.exception.PostNotFound;
import com.sharipov.topuch.domain.exception.ProfileNotFound;
import com.sharipov.topuch.domain.repository.PostRepository;
import com.sharipov.topuch.domain.repository.ProfileRepository;
import com.sharipov.topuch.domain.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        Post post = postRepository.findById(id).orElseThrow(()-> new PostNotFound(id));
        return post;
    }

    @Override
    public Post createPost(Post post) {
        post.setCreated_at(LocalDateTime.now());
        Post result = postRepository.save(post);
        return result;
    }

    @Override
    public Post updatePost(UUID postId, Post post) {
        Post exist = getPostById(postId);

        if (exist.equals(post)) {
            return exist;
        }

        return postRepository.save(post);
    }

    @Override
    public void deletePostById(UUID id) {
        Post exist = getPostById(id);

        postRepository.deleteById(id);

    }

    @Override
    public void deleteOldPost() {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);

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
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostNotFound(postId));
        Profile profile = profileRepository.findById(profileId).orElseThrow(()-> new ProfileNotFound(profileId));

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
                () -> new PostNotFound(postId)
        );

        return post.getLikedByUser().size();
    }
}
