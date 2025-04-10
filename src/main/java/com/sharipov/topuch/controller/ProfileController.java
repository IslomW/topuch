package com.sharipov.topuch.controller;

import com.sharipov.topuch.entity.Profile;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
public class ProfileController {


    @GetMapping("users")
    public Profile getAllUsers() {
        Profile profile = new Profile();
        return profile;
    }


    @GetMapping("user/{id}")
    public Profile getUserById(@PathVariable Long id) {
        Profile profile = new Profile();
        return profile;
    }

    @PostMapping("user")
    public void createUser(@RequestBody Profile profile) {
    }


//    @GetMapping
//    public Profile getUser(){
//
//        return profile;
//
//    }

}
