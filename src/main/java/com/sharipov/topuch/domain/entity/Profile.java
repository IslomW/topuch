package com.sharipov.topuch.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.time.Instant;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.id.uuid.UuidVersion7Strategy;

@Entity
@Table(name = "profiles")
public class Profile {
    public static final int DEFAULT_TRUST_FACTOR = 5;

    @Id
    @UuidGenerator(algorithm = UuidVersion7Strategy.class)
    private UUID profileId;

    private String firstName;

    private String lastName;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    private Long phoneNumber;

    private String email;

    @Min(0)
    @Max(10)
    @Column(name = "trust_factor", nullable = false)
    private int trustFactor = DEFAULT_TRUST_FACTOR;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;


    public UUID getProfileId() {
        return profileId;
    }

    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }



    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTrustFactor() {
        return trustFactor;
    }

    public void setTrustFactor(int trustFactor) {
        if (trustFactor < 0 || trustFactor > 10) {
            throw new IllegalArgumentException("Trust factor must be between 0 and 10");
        }
        this.trustFactor = trustFactor;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "profileId=" + profileId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", createdAt=" + createdAt +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", trustFactor=" + trustFactor +
                ", address=" + address +
                '}';
    }
}
