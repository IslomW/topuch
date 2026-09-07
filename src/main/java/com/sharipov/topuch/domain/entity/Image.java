package com.sharipov.topuch.domain.entity;


import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.id.uuid.UuidVersion7Strategy;


@Entity
@Table(name = "images")
public class Image {

    @Id
    @UuidGenerator(algorithm = UuidVersion7Strategy.class)
    private UUID imageId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    private String imageAddress;


    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;


    public UUID getImageId() {
        return imageId;
    }

    public void setImageId(UUID imageId) {
        this.imageId = imageId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
