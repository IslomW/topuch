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

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p.postId from Post p where p.created_at < :date")
    List<Long> findAllIdsByCreateAtBefore(@Param("date")LocalDateTime date);


    @Modifying
    @Transactional
    @Query("DELETE FROM Post p WHERE p.created_at < :date")
    void deleteAllOlderThan(@Param("date") LocalDateTime date);
}
