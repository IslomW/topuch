package com.sharipov.topuch.application.converter;


import com.sharipov.topuch.application.dto.CategoryDTO;
import com.sharipov.topuch.domain.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDto(Category category);

    Category toEntity(CategoryDTO categoryDTO);


    List<CategoryDTO> toListDto(List<Category> categories);

    List<Category> toListEntity(List<CategoryDTO> categoryDTOS);


}

