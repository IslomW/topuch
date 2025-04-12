package com.sharipov.topuch.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "posts")
public class Post {
    @Id
    private Long postId;
    private String title;
    private String description;
    private BigDecimal price;
    private LocalDateTime created_at;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Image> images;

    @Enumerated(EnumType.STRING)
    private Condition condition;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;


}
