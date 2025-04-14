package com.sharipov.topuch.domain.entity.service;

import com.sharipov.topuch.domain.entity.Profile;

import java.util.List;

public interface ProfileService {

    List<Profile> getAllProfile();
    Profile getProfileById(Long id);

    Profile createProfile(Profile profile);

    void updateProfile(Profile profile);

    void deleteProfileById(Long id);


}
