package com.sharipov.topuch.web.controller;


import com.sharipov.topuch.application.converter.CategoryMapper;
import com.sharipov.topuch.application.dto.CategoryDTO;
import com.sharipov.topuch.domain.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {


    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;


    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> result = categoryMapper.toListDto(categoryService.getAllCategories());

        return ResponseEntity.ok(result);
    }


    @GetMapping("{parentId}/subcategories")
    public ResponseEntity<List<CategoryDTO>> getAllSubCategories(@PathVariable Long parentId) {
        List<CategoryDTO> result = categoryMapper.toListDto(categoryService.getSubcategories(parentId));

        return ResponseEntity.ok(result);
    }


    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO result = categoryMapper.toDto(
                categoryService.createCategory(categoryMapper.toEntity(categoryDTO))
        );

        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id,
                                                      @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO result = categoryMapper.toDto(
                categoryService.updateCategory(id, categoryMapper.toEntity(categoryDTO))
        );


        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);

        return ResponseEntity.noContent().build();
    }
}
