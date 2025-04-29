package com.sharipov.topuch.application.converter;


import com.sharipov.topuch.domain.document.PostDocument;
import com.sharipov.topuch.domain.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PostDocumentMapper {

    @Mappings({
            @Mapping(target = "price", expression = "java(post.getPrice().doubleValue())"),
            @Mapping(source = "post.category.name", target = "subcategoryName"),
            @Mapping(source = "categoryName", target = "categoryName")

    })
    PostDocument toPostDocument(Post post, String categoryName);







}
