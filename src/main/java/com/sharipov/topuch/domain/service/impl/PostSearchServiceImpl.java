package com.sharipov.topuch.domain.service.impl;

import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.sharipov.topuch.domain.document.PostDocument;
import com.sharipov.topuch.domain.service.PostSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
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
    public List<PostDocument> searchPostsByKeyword(String keyword, Pageable pageable) {
        Query multiMatchQuery = Query.of(q -> q.multiMatch(
                m -> m.fields("title", "description")
                        .query(keyword)
                        .fuzziness("AUTO")
        ));

        NativeQuery query = NativeQuery.builder()
                .withQuery(multiMatchQuery)
                .withPageable(pageable)
                .build();

        SearchHits<PostDocument> searchHits = elasticsearchOperations.search(query, PostDocument.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

    }

    @Override
    public List<PostDocument> searchPostsByCategory(String categoryName, Pageable pageable) {
        Query matchQuery = Query.of(q -> q.match(
                m -> m.field("categoryName")
                        .query(categoryName)
        ));
        NativeQuery query = NativeQuery.builder()
                .withQuery(matchQuery)
                .withPageable(pageable)
                .build();
        log.warn(query.getQuery().toString());

        SearchHits<PostDocument> searchHits = elasticsearchOperations.search(query, PostDocument.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDocument> searchPostsByPriceRange(double minPrice, double maxPrice, Pageable pageable) {
        Query rangeQuery = Query.of(q -> q.range(
                rq -> rq.number(
                        nrq -> nrq.field("price")
                                .gte(minPrice)
                                .lte(maxPrice)
                )
        ));

        NativeQuery query = NativeQuery.builder()
                .withQuery(rangeQuery)
                .withPageable(pageable)
                .build();

        SearchHits<PostDocument> searchHits = elasticsearchOperations.search(query, PostDocument.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

    }

    @Override
    public List<PostDocument> searchAndSortByPrice(String keyword, boolean ascending, Pageable pageable) {
        Query multiMatchQuery = Query.of(q -> q.multiMatch(
                m -> m.fields("title", "description")
                        .query(keyword)
                        .fuzziness("AUTO")
        ));

        SortOptions sortOptions = SortOptions.of(s -> s
                .field(f -> f
                        .field("price")
                        .order(ascending ? SortOrder.Asc : SortOrder.Desc)
                )
        );

        NativeQuery query = NativeQuery.builder()
                .withQuery(multiMatchQuery)
                .withSort(sortOptions)
                .withPageable(pageable)
                .build();

        SearchHits<PostDocument> searchHits = elasticsearchOperations.search(query, PostDocument.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDocument> searchWithHighlighting(String keyword, Pageable pageable) {
        return null;
    }

    @Override
    public List<PostDocument> getAllPosts(Pageable pageable) {

        Query matchAllQuery = Query.of(q -> q
                .matchAll(ma -> ma));

        NativeQuery query = NativeQuery.builder()
                .withQuery(matchAllQuery)
                .withPageable(pageable)
                .build();

        SearchHits<PostDocument> searchHits = elasticsearchOperations.search(query, PostDocument.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    //Is Done
    @Override
    public long countPostsByKeyword(String keyword) {
        Query multiMatchQuery = Query.of(q -> q.multiMatch(
                m -> m.fields("title", "description")
                        .query(keyword)
                        .fuzziness("AUTO")
        ));

        NativeQuery query = NativeQuery.builder()
                .withQuery(multiMatchQuery)
                .build();

        return elasticsearchOperations.count(query, PostDocument.class);
    }

    @Override
    public void deletePostById(UUID postId) {
        elasticsearchOperations.delete(postId.toString(), PostDocument.class);
    }

    @Override
    public List<PostDocument> searchPostsBySubcategory(String subcategoryName, Pageable pageable) {
        Query termQuery = Query.of(q -> q
                .term(t -> t
                        .field("subcategoryName")
                        .value(subcategoryName)
                )
        );

        NativeQuery query = NativeQuery.builder()
                .withQuery(termQuery)
                .withPageable(pageable)
                .build();

        SearchHits<PostDocument> searchHits = elasticsearchOperations.search(query, PostDocument.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}
