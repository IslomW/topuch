package com.sharipov.topuch.application.converter;

import com.sharipov.topuch.application.dto.request.PostRequestDTO;
import com.sharipov.topuch.application.dto.response.PostResponseDTO;
import com.sharipov.topuch.application.dto.response.SellerResponseDTO;
import com.sharipov.topuch.domain.entity.Post;
import com.sharipov.topuch.domain.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ImageMapper.class)
public interface PostMapper {

    PostResponseDTO toDto(Post post);

    @Mapping(target = "postId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "likedByUser", ignore = true)
    Post toEntity(PostRequestDTO postRequestDTO);

    SellerResponseDTO toSellerDto(Profile profile);

    List<PostResponseDTO> toDtoList(List<Post> posts);

}
