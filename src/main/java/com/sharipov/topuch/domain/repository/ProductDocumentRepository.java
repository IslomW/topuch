package com.sharipov.topuch.domain.repository;

import com.sharipov.topuch.domain.document.PostDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductDocumentRepository extends ElasticsearchRepository<PostDocument, String> {


}
