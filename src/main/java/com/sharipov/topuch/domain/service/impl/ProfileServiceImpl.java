package com.sharipov.topuch.domain.service.impl;

import com.sharipov.topuch.common.exception.NotFoundException;
import com.sharipov.topuch.domain.entity.Profile;
import com.sharipov.topuch.domain.repository.ProfileRepository;
import com.sharipov.topuch.domain.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public List<Profile> getAllProfile() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles;
    }

    @Override
    public Profile getProfileById(UUID id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(NotFoundException::profileNotFound);
        return profile;
    }

    @Override
    public Profile createProfile(Profile profile) {
        profile.setCreatedAt(Instant.now());
        profile.setTrustFactor(Profile.DEFAULT_TRUST_FACTOR);
        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(UUID id, Profile profile) {
        Profile existing = getProfileById(id);
        existing.setFirstName(profile.getFirstName());
        existing.setLastName(profile.getLastName());
        existing.setPhoneNumber(profile.getPhoneNumber());
        existing.setEmail(profile.getEmail());
        existing.setAddress(profile.getAddress());

        return profileRepository.save(existing);
    }

    @Override
    public void deleteProfileById(UUID id) {
        profileRepository.deleteById(id);
    }
}
