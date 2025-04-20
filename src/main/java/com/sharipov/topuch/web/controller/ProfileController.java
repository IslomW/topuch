package com.sharipov.topuch.web.controller;

import com.sharipov.topuch.application.converter.ProfileMapper;
import com.sharipov.topuch.application.dto.request.ProfileRequestDTO;
import com.sharipov.topuch.application.dto.response.ProfileResponseDTO;
import com.sharipov.topuch.domain.entity.Profile;
import com.sharipov.topuch.domain.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/users")
public class ProfileController {

    private final ProfileService profileService;

    private final ProfileMapper profileMapper;

    private final Logger log = LoggerFactory.getLogger(ProfileController.class);

    public ProfileController(ProfileService profileService, ProfileMapper profileMapper) {
        this.profileService = profileService;
        this.profileMapper = profileMapper;
    }


        @GetMapping
    public ResponseEntity<List<ProfileResponseDTO>> getAllUsers() {
        List<Profile> profiles = profileService.getAllProfile();
        return ResponseEntity.ok(profileMapper.toDtoList(profiles));
    }


    @GetMapping("{id}")
    public ResponseEntity<ProfileResponseDTO> getUserById(@PathVariable Long id) {
       Profile profile = profileService.getProfileById(id);
        return ResponseEntity.ok(profileMapper.toDto(profile)) ;
    }

    @PostMapping
    public ResponseEntity<ProfileResponseDTO> createUser(@RequestBody ProfileRequestDTO profile) {
        Profile result = profileService.createProfile(profileMapper.toEntity(profile));
        return ResponseEntity.status(HttpStatus.CREATED).body(profileMapper.toDto(result));
    }


    @PutMapping("{id}")
    public ResponseEntity<ProfileResponseDTO> updateUser(@PathVariable Long id,
            @RequestBody ProfileRequestDTO profile){
        Profile update =  profileService.updateProfile(id, profileMapper.toEntity(profile));
        return ResponseEntity.ok(profileMapper.toDto(update));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        profileService.deleteProfileById(id);
        return ResponseEntity.noContent().build();
    }







}
