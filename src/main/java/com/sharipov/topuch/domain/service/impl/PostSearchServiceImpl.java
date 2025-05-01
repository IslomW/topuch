package com.sharipov.topuch.domain.service.impl;

import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sharipov.topuch.domain.document.PostDocument;
import com.sharipov.topuch.domain.service.PostSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostSearchServiceImpl implements PostSearchService {

    private final Logger log = LoggerFactory.getLogger(PostSearchServiceImpl.class);
    private final ElasticsearchOperations elasticsearchOperations;

    public PostSearchServiceImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

//    private static final String INDEX = "posts";


    //Поиск по ключевому слову
    @Override
    public List<PostDocument> searchPostsByKeyword(String keyword, int page, int size) {
        Query multiMatchQuery = Query.of(q -> q.multiMatch(
                m -> m.fields("title", "description")
                        .query(keyword)
                        .fuzziness("AUTO")
        ));

        NativeQuery query = NativeQuery.builder()
                .withQuery(multiMatchQuery)
                .withPageable(PageRequest.of(page, size))
                .build();

        SearchHits<PostDocument> searchHits = elasticsearchOperations.search(query, PostDocument.class);
        log.info(searchHits.getSearchHits().toString());
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

    }

    @Override
    public List<PostDocument> searchPostsByCategory(String categoryName, int page, int size) {
        Query matchQuery = Query.of(q -> q.match(
                m -> m.field("category")
                        .query(categoryName)
        ));
        NativeQuery query = NativeQuery.builder()
                .withQuery(matchQuery)
                .withPageable(PageRequest.of(page, size))
                .build();

        log.info(query.toString());

        SearchHits<PostDocument> searchHits = elasticsearchOperations.search(query, PostDocument.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDocument> searchPostsByPriceRange(double minPrice, double maxPrice, int page, int size) {
        Query rangeQuery = Query.of(q -> q.range(
                rq -> rq.number(
                        nrq -> nrq.field("price")
                                .from(minPrice)
                                .to(maxPrice)
                )
        ));

        NativeQuery query = NativeQuery.builder()
                .withQuery(rangeQuery)
                .withPageable(PageRequest.of(page, size))
                .build();

        SearchHits<PostDocument> searchHits = elasticsearchOperations.search(query, PostDocument.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

    }

    @Override
    public List<PostDocument> searchAndSortByPrice(String keyword, boolean ascending, int page, int size) {
        Query matchQuery = Query.of(q -> q.match(
                m -> m.field("content")
                        .query(keyword)
        ));

        SortOptions sortOptions = SortOptions.of(s -> s
                .field(f -> f
                        .field("price")
                        .order(ascending ? SortOrder.Asc : SortOrder.Desc)
                )
        );

        NativeQuery query = NativeQuery.builder()
                .withQuery(matchQuery)
                .withSort(sortOptions)
                .withPageable(PageRequest.of(page, size))
                .build();

        SearchHits<PostDocument> searchHits = elasticsearchOperations.search(query, PostDocument.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDocument> searchWithHighlighting(String keyword, int page, int size) {
        return null;
    }

    @Override
    public List<PostDocument> getAllPosts(int page, int size) {

        Query matchAllQuery = Query.of(q -> q
                .matchAll(ma -> ma));

        NativeQuery query = NativeQuery.builder()
                .withQuery(matchAllQuery)
                .withPageable(PageRequest.of(page, size))
                .build();

        SearchHits<PostDocument> searchHits = elasticsearchOperations.search(query, PostDocument.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    @Override
    public long countPostsByKeyword(String keyword) {
        Query matchQuery = Query.of(q -> q
                .match(m -> m
                        .field("content")
                        .query(keyword)
                )
        );

        NativeQuery query = NativeQuery.builder()
                .withQuery(matchQuery)
                .build();

        return elasticsearchOperations.count(query, PostDocument.class);
    }

    @Override
    public void deletePostById(String postId) {
        elasticsearchOperations.delete(postId, PostDocument.class);
    }

    @Override
    public List<PostDocument> searchPostsBySubcategory(String subcategoryName, int page, int size) {
        Query termQuery = Query.of(q -> q
                .term(t -> t
                        .field("subcategory")
                        .value(subcategoryName)
                )
        );

        NativeQuery query = NativeQuery.builder()
                .withQuery(termQuery)
                .withPageable(PageRequest.of(page, size))
                .build();

        SearchHits<PostDocument> searchHits = elasticsearchOperations.search(query, PostDocument.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}
