package com.sharipov.topuch.domain.service;

import com.sharipov.topuch.domain.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    List<Category> getSubcategories(Long parentId);

    Category createCategory(Category category);

    Category createSubcategory(Long parentId, Category category);

    Category updateCategory(Long id, Category category);

    void deleteCategory(Long id);


}
