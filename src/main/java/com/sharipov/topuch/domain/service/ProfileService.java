package com.sharipov.topuch.domain.service;

import com.sharipov.topuch.domain.entity.Profile;

import java.util.List;
import java.util.UUID;


public interface ProfileService {

    List<Profile> getAllProfile();
    Profile getProfileById(UUID id);

    Profile createProfile(Profile profile);

    Profile updateProfile(UUID id, Profile profile);

    void deleteProfileById(UUID id);


}
