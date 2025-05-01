package com.sharipov.topuch.web.controller;


import com.sharipov.topuch.domain.document.PostDocument;
import com.sharipov.topuch.domain.service.PostSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostSearchController {

    private final PostSearchService postSearchService;

    public PostSearchController(PostSearchService postSearchService) {
        this.postSearchService = postSearchService;
    }

    @GetMapping
    public ResponseEntity<List<PostDocument>> searchByKeyword(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(postSearchService.searchPostsByKeyword(keyword, page,size));
    }



    @GetMapping("/category")
    public ResponseEntity<List<PostDocument>> searchByCategory(
            @RequestParam String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(postSearchService.searchPostsBySubcategory(category, page, size));
    }

    @GetMapping("/subcategory")
    public ResponseEntity<List<PostDocument>> searchBySubcategory(
            @RequestParam String subcategory,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ){
        return ResponseEntity.ok(postSearchService.searchPostsBySubcategory(subcategory, page, size));
    }

    @GetMapping("price")
    public ResponseEntity<List<PostDocument>> searchByPriceRange(
            @RequestParam double min,
            @RequestParam double max,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size

    ){
        return ResponseEntity.ok(postSearchService.searchPostsByPriceRange(min, max, page, size));
    }


    @GetMapping("/price/sort")
    public ResponseEntity<List<PostDocument>> sortByPrice(
            @RequestParam String keyword,
            @RequestParam boolean ascending,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(postSearchService.searchAndSortByPrice(keyword, ascending, page, size));
    }



}
