package com.sharipov.topuch.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.id.uuid.UuidVersion7Strategy;

@Entity
@Table(name = "reports")
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @UuidGenerator(algorithm = UuidVersion7Strategy.class)
    private UUID reportId;

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

}
