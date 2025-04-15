package com.sharipov.topuch.application.dto.response;

import com.sharipov.topuch.application.dto.ImageDTO;
import com.sharipov.topuch.domain.entity.Condition;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PostResponseDTO(
                      Long profileId,
                      String title,
                      String description,
                      BigDecimal price,
                      LocalDateTime created_at,
                      List<ImageDTO> images,
                      Condition condition) {
}
