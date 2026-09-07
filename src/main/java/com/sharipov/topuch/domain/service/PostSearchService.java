package com.sharipov.topuch.domain.service;

import com.sharipov.topuch.domain.document.PostDocument;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface PostSearchService {

    // Поиск по ключевым словам в нескольких полях
    List<PostDocument> searchPostsByKeyword(String keyword, Pageable pageable);

    // Поиск по категории
    List<PostDocument> searchPostsByCategory(String categoryName, Pageable pageable);

    // Поиск по цене
    List<PostDocument> searchPostsByPriceRange(double minPrice, double maxPrice, Pageable pageable);

    // Сортировка по цене (по возрастанию или убыванию)
    List<PostDocument> searchAndSortByPrice(String keyword, boolean ascending, Pageable pageable);

    // Поиск с подсветкой
    List<PostDocument> searchWithHighlighting(String keyword, Pageable pageable);

    // Получение всех товаров (для административных нужд)
    List<PostDocument> getAllPosts(Pageable pageable);

    // Метод для подсчета общего количества товаров по ключевому запросу
    long countPostsByKeyword(String keyword);

    // Метод для удаления поста по ID (если нужно в сервисе)
    void deletePostById(UUID postId);

    // Метод для поиска по подкатегории
    List<PostDocument> searchPostsBySubcategory(String subcategoryName, Pageable pageable);
}
