package com.sharipov.topuch.application.dto.request;

import com.sharipov.topuch.application.dto.ImageDTO;
import com.sharipov.topuch.domain.entity.Condition;

import java.math.BigDecimal;
import java.util.List;

public record PostRequestDTO(String title,
                             String description,
                             BigDecimal price,
                             List<ImageDTO> images,
                             Condition condition) {
}
