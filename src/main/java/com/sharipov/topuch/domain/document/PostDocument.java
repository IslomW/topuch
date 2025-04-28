package com.sharipov.topuch.domain.document;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "products")
public class ProductDocument {

    @Id
    private String id;

    private String name;
    private String description;
    private Double price;
    private String category;
    private String brand;
}
