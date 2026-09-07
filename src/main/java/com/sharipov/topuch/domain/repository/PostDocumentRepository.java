package com.sharipov.topuch.domain.repository;

import com.sharipov.topuch.domain.document.PostDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface PostDocumentRepository extends ElasticsearchRepository<PostDocument, UUID> {
    @Query("""
        {
          "multi_match": {
            "query": "?0",
            "fields": ["title", "description", "subcategoryName"],
            "fuzziness": "AUTO"
          }
        }
    """)
    List<PostDocument> searchByText(String query);

}
