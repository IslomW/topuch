package com.sharipov.topuch.controller;

import com.sharipov.topuch.entity.Post;


import com.sharipov.topuch.entity.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/post")

public class ProfileContoller {

    @GetMapping
    public Profile getProfileById() {
        return new Profile();
    }

}