package com.sharipov.topuch.domain.exception;

public class PostNotFound extends RuntimeException{
    public PostNotFound(Long id) {
        super("Post with Id:" + id + " not found");
    }
}
