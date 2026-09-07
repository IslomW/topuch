package com.sharipov.topuch.domain.repository;

import com.sharipov.topuch.domain.entity.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("select p.postId from Post p where p.createdAt < :date")
    List<UUID> findAllIdsByCreateAtBefore(@Param("date")LocalDateTime date);


    @Modifying
    @Transactional
    @Query("DELETE FROM Post p WHERE p.createdAt < :date")
    void deleteAllOlderThan(@Param("date") LocalDateTime date);
}
