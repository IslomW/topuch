package com.sharipov.topuch.domain.service.impl;

import com.sharipov.topuch.domain.entity.Category;
import com.sharipov.topuch.domain.exception.CategoryNotFound;
import com.sharipov.topuch.domain.repository.CategoryRepository;
import com.sharipov.topuch.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findParentCategories();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFound(id));
    }

    @Override
    public List<Category> getSubcategories(Long parentId) {
        return categoryRepository.findSubcategoriesByParentId(parentId);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category createSubcategory(Long parentId, Category category) {
        Category parent = getCategoryById(parentId);
        category.setParentId(parent.getCategoryId());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category exist = getCategoryById(id);

        if (exist.equals(category)){
            return exist;
        }

        exist.setName(category.getName());
        exist.setParentId(category.getParentId());
        return categoryRepository.save(exist);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
