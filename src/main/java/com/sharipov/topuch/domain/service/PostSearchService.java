package com.sharipov.topuch.domain.service;

import com.sharipov.topuch.domain.document.PostDocument;
import com.sharipov.topuch.domain.repository.PostDocumentRepository;

import java.util.List;

public interface PostSearchService {

    // Поиск по ключевым словам в нескольких полях
    List<PostDocument> searchPostsByKeyword(String keyword, int page, int size);

    // Поиск по категории
    List<PostDocument> searchPostsByCategory(String categoryName, int page, int size);

    // Поиск по цене
    List<PostDocument> searchPostsByPriceRange(double minPrice, double maxPrice, int page, int size);

    // Сортировка по цене (по возрастанию или убыванию)
    List<PostDocument> searchAndSortByPrice(String keyword, boolean ascending, int page, int size);

    // Поиск с подсветкой
    List<PostDocument> searchWithHighlighting(String keyword, int page, int size);

    // Получение всех товаров (для административных нужд)
    List<PostDocument> getAllPosts(int page, int size);

    // Метод для подсчета общего количества товаров по ключевому запросу
    long countPostsByKeyword(String keyword);

    // Метод для удаления поста по ID (если нужно в сервисе)
    void deletePostById(String postId);

    // Метод для поиска по подкатегории
    List<PostDocument> searchPostsBySubcategory(String subcategoryName, int page, int size);
}
