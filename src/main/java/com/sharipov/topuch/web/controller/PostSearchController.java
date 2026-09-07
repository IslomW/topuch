package com.sharipov.topuch.web.controller;


import com.sharipov.topuch.domain.document.PostDocument;
import com.sharipov.topuch.domain.service.PostSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/search")
@RequiredArgsConstructor
public class PostSearchController {

    private final PostSearchService postSearchService;

    //Is Done
    @GetMapping("/by-keyword")
    public ResponseEntity<List<PostDocument>> searchByKeyword(
            @RequestParam String keyword,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        return ResponseEntity.ok(postSearchService.searchPostsByKeyword(keyword, pageable));
    }


    //Is Done
    @GetMapping("/by-category")
    public ResponseEntity<List<PostDocument>> searchByCategory(
            @RequestParam String category,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        return ResponseEntity.ok(postSearchService.searchPostsByCategory(category, pageable));
    }

    //Is Done
    @GetMapping("/by-subcategory")
    public ResponseEntity<List<PostDocument>> searchBySubcategory(
            @RequestParam String subcategory,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
            ){
        return ResponseEntity.ok(postSearchService.searchPostsBySubcategory(subcategory, pageable));
    }

    //Is Done
    @GetMapping("price")
    public ResponseEntity<List<PostDocument>> searchByPriceRange(
            @RequestParam double min,
            @RequestParam double max,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable

    ){
        return ResponseEntity.ok(postSearchService.searchPostsByPriceRange(min, max, pageable));
    }

    // Is Done
    @GetMapping("/price/sort")
    public ResponseEntity<List<PostDocument>> sortByPrice(
            @RequestParam String keyword,
            @RequestParam boolean ascending,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        return ResponseEntity.ok(postSearchService.searchAndSortByPrice(keyword, ascending, pageable));
    }


    @GetMapping("/highlight")
    public ResponseEntity<List<PostDocument>> searchWithHighlighting(
            @RequestParam String keyword,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(postSearchService.searchWithHighlighting(keyword, pageable));
    }

   //Is Done
    @GetMapping("/all")
    public ResponseEntity<List<PostDocument>> getAllPosts(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        return ResponseEntity.ok(postSearchService.getAllPosts(pageable));
    }

    @GetMapping("count")
    public ResponseEntity<Long> countByKeyword(@RequestParam String keyword){
            return ResponseEntity.ok(postSearchService.countPostsByKeyword(keyword));
    }


}
