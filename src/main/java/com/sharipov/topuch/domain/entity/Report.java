package com.sharipov.topuch.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reportId;

    private LocalDateTime reportedAt;

    @Enumerated(EnumType.STRING)
    private Abuse abuseType;
    private String  message;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post postId;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profileId;


    public Report(Long reportId, LocalDateTime reportedAt, Abuse abuseType, String message, Post postId, Profile profileId) {
        this.reportId = reportId;
        this.reportedAt = reportedAt;
        this.abuseType = abuseType;
        this.message = message;
        this.postId = postId;
        this.profileId = profileId;
    }
}
