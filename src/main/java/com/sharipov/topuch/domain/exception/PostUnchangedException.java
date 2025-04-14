package com.sharipov.topuch.domain.exception;

public class PostUnchangedException extends RuntimeException{

    public PostUnchangedException(Long id) {
        super("Post with Id:" + id + " has not been changed");
    }
}
