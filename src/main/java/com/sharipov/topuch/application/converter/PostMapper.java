package com.sharipov.topuch.application.converter;

import com.sharipov.topuch.application.dto.request.PostRequestDTO;
import com.sharipov.topuch.application.dto.response.PostResponseDTO;
import com.sharipov.topuch.domain.entity.Post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ImageMapper.class)
public interface PostMapper {

    PostResponseDTO toDto(Post post);

    Post toEntity(PostRequestDTO postRequestDTO);

    List<PostResponseDTO> toDtoList(List<Post> posts);

}
