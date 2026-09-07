package com.sharipov.topuch.domain.service.impl;

import com.sharipov.topuch.domain.entity.Profile;
import com.sharipov.topuch.domain.exception.ProfileNotFound;
import com.sharipov.topuch.domain.repository.ProfileRepository;
import com.sharipov.topuch.domain.service.ProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Profile> getAllProfile() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles;
    }

    @Override
    public Profile getProfileById(UUID id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFound(id));
        return profile;
    }

    @Override
    public Profile createProfile(Profile profile) {
        profile.setCreatedAt(LocalDateTime.now());
        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(UUID id, Profile profile) {
        Profile exist = getProfileById(id);

        if (exist.equals(profile)){
            return exist;
        }

        return profileRepository.save(profile);
    }

    @Override
    public void deleteProfileById(UUID id) {
        profileRepository.deleteById(id);
    }
}
