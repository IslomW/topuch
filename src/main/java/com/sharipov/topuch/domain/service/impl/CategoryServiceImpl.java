package com.sharipov.topuch.domain.service.impl;

import com.sharipov.topuch.common.exception.NotFoundException;
import com.sharipov.topuch.domain.entity.Category;
import com.sharipov.topuch.domain.repository.CategoryRepository;
import com.sharipov.topuch.domain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findParentCategories();
    }

    @Override
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(NotFoundException::categoryNotFound);
    }

    @Override
    public List<Category> getSubcategories(UUID parentId) {
        return categoryRepository.findSubcategoriesByParentId(parentId);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category createSubcategory(UUID parentId, Category category) {
        Category parent = getCategoryById(parentId);
        category.setParentId(parent.getCategoryId());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(UUID id, Category category) {
        Category exist = getCategoryById(id);

        if (exist.equals(category)){
            return exist;
        }

        exist.setName(category.getName());
        exist.setParentId(category.getParentId());
        return categoryRepository.save(exist);
    }

    @Override
    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }
}
