package com.sharipov.topuch.domain.service.impl;

import com.sharipov.topuch.domain.document.PostDocument;
import com.sharipov.topuch.domain.service.PostSearchService;

import java.util.List;

public class PostSearchServiceImpl implements PostSearchService {
    @Override
    public List<PostDocument> searchPostsByKeyword(String keyword, int page, int size) {
        return null;
    }

    @Override
    public List<PostDocument> searchPostsByCategory(String categoryName, int page, int size) {
        return null;
    }

    @Override
    public List<PostDocument> searchPostsByPriceRange(double minPrice, double maxPrice, int page, int size) {
        return null;
    }

    @Override
    public List<PostDocument> searchAndSortByPrice(String keyword, boolean ascending, int page, int size) {
        return null;
    }

    @Override
    public List<PostDocument> searchWithHighlighting(String keyword, int page, int size) {
        return null;
    }

    @Override
    public List<PostDocument> getAllPosts(int page, int size) {
        return null;
    }

    @Override
    public long countPostsByKeyword(String keyword) {
        return 0;
    }

    @Override
    public void deletePostById(String postId) {

    }

    @Override
    public List<PostDocument> searchPostsBySubcategory(String subcategoryName, int page, int size) {
        return null;
    }
}
