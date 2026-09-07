package com.sharipov.topuch.application.dto.response;

import com.sharipov.topuch.application.dto.ImageDTO;
import com.sharipov.topuch.domain.entity.Condition;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record PostResponseDTO(
                      UUID postId,
                      SellerResponseDTO seller,
                      String title,
                      String description,
                      BigDecimal price,
                      Instant createdAt,
                      List<ImageDTO> images,
                      Condition condition) {
}
