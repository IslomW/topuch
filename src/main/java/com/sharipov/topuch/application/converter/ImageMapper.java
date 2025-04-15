package com.sharipov.topuch.application.converter;

import com.sharipov.topuch.application.dto.ImageDTO;
import com.sharipov.topuch.domain.entity.Image;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageDTO toDto(Image image);

    Image toEntity(ImageDTO imageDTO);
}
