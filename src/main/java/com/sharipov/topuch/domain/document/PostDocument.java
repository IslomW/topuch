package com.sharipov.topuch.domain.document;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;
import java.util.UUID;

@Document(indexName = "posts")
public class PostDocument {

    @Id
    private UUID postId;

    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Double)
    private Double price;
    @Field(type = FieldType.Date)
    private Instant createdAt;
    @Field(type = FieldType.Keyword)
    private String introductionImageUrl;
    @Field(type = FieldType.Keyword)
    private String condition;
    @Field(type = FieldType.Keyword)
    private String categoryName;
    @Field(type = FieldType.Keyword)
    private String subcategoryName;
    @Field(type = FieldType.Keyword)
    private UUID sellerId;
    @Field(type = FieldType.Integer)
    private Integer sellerTrustFactor;

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getIntroductionImageUrl() {
        return introductionImageUrl;
    }

    public void setIntroductionImageUrl(String introductionImageUrl) {
        this.introductionImageUrl = introductionImageUrl;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getSellerTrustFactor() {
        return sellerTrustFactor;
    }

    public void setSellerTrustFactor(Integer sellerTrustFactor) {
        this.sellerTrustFactor = sellerTrustFactor;
    }

    @Override
    public String toString() {
        return "PostDocument{" +
                "postId='" + postId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", introductionImageUrl='" + introductionImageUrl + '\'' +
                ", condition='" + condition + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", subcategoryName='" + subcategoryName + '\'' +
                ", sellerId=" + sellerId +
                ", sellerTrustFactor=" + sellerTrustFactor +
                '}';
    }
}
