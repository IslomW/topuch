package com.sharipov.topuch.domain.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.id.uuid.UuidVersion7Strategy;


@Entity
@Table(name = "images")
public class Image {

    @Id
    @UuidGenerator(algorithm = UuidVersion7Strategy.class)
    private UUID imageId;
    private LocalDateTime createdAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
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
