package com.sharipov.topuch.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.id.uuid.UuidVersion7Strategy;


@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @UuidGenerator(algorithm = UuidVersion7Strategy.class)
    private UUID postId;

    private String title;

    private String description;

    private BigDecimal price;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Image> images;

    @Enumerated(EnumType.STRING)
    private Condition condition;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile seller;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "post_likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private Set<Profile> likedByUser = new HashSet<>();

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Profile getSeller() {
        return seller;
    }

    public void setSeller(Profile seller) {
        this.seller = seller;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Profile> getLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(Set<Profile> likedByUser) {
        this.likedByUser = likedByUser;
    }
}
