package com.sharipov.topuch.domain.repository;

import com.sharipov.topuch.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
