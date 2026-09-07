package com.sharipov.topuch.domain.service;

import com.sharipov.topuch.domain.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(UUID id);

    List<Category> getSubcategories(UUID parentId);

    Category createCategory(Category category);

    Category createSubcategory(UUID parentId, Category category);

    Category updateCategory(UUID id, Category category);

    void deleteCategory(UUID id);


}
