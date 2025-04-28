package com.sharipov.topuch.domain.service.impl;

import com.sharipov.topuch.domain.document.PostDocument;
import com.sharipov.topuch.domain.repository.ProductDocumentRepository;
import com.sharipov.topuch.domain.service.ProductDocumentService;

public class ProductDocumentServiceImpl implements ProductDocumentService {

    private final ProductDocumentRepository productDocumentRepository;

    public ProductDocumentServiceImpl(ProductDocumentRepository productDocumentRepository) {
        this.productDocumentRepository = productDocumentRepository;
    }

    @Override
    public PostDocument save(PostDocument postDocument) {
        return productDocumentRepository.save(postDocument);
    }


}
