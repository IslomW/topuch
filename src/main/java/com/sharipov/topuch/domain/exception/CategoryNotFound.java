package com.sharipov.topuch.domain.exception;

public class CategoryNotFound extends RuntimeException{

    public CategoryNotFound(Long id) {
        super("Post with Id:" + id + " not found");
    }
}
