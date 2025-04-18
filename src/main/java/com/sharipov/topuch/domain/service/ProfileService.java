package com.sharipov.topuch.domain.service;

import com.sharipov.topuch.domain.entity.Profile;

import java.util.List;


public interface ProfileService {

    List<Profile> getAllProfile();
    Profile getProfileById(Long id);

    Profile createProfile(Profile profile);

    Profile updateProfile(Long id, Profile profile);

    void deleteProfileById(Long id);


}
