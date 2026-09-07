package com.sharipov.topuch.application.dto.response;

import com.sharipov.topuch.domain.entity.Address;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProfileResponseDTO(
         UUID profileId,
         String firstName,
         String lastName,
         LocalDateTime createdAt,
         Long phoneNumber,
         String email,
         Address address
) {
}
