package com.sharipov.topuch.domain.repository;

import com.sharipov.topuch.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.parentId is null")
    List<Category> findParentCategories();

    @Query("select c from Category c where c.parentId = :parentId")
    List<Category> findSubcategoriesByParentId(@Param("parentId") Long parentId);
}
