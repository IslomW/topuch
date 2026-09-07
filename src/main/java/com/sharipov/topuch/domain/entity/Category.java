package com.sharipov.topuch.domain.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.id.uuid.UuidVersion7Strategy;

import java.util.UUID;

@Entity
public class Category {

    @Id
    @UuidGenerator(algorithm = UuidVersion7Strategy.class)
    private UUID categoryId;
    private String name;

    private UUID parentId;


    public Category(UUID categoryId, String name, UUID parentId) {
        this.categoryId = categoryId;
        this.name = name;
        this.parentId = parentId;
    }

    public Category(String name) {
        this.name = name;
    }

    public Category() {

    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }
}
