package com.sharipov.topuch.service;

import com.sharipov.topuch.entity.Profile;

import java.util.List;

public interface ProfileService {

    List<Profile> getAllProfile();
    Profile getProfileById(Long id);

    Profile createProfile(Profile profile);

    void updateProfile(Profile profile);

    void deleteProfileById(Long id);


}
