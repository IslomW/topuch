package com.sharipov.topuch.domain.exception;

public class ProfileNotFound extends RuntimeException{

    public ProfileNotFound(Long id) {
        super("Profile with Id: "+id+ " not found");
    }
}
