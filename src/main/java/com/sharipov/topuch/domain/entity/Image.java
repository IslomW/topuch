package com.sharipov.topuch.domain.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imageId;
    private LocalDateTime createdAt;
    private String imageAddress;


    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
