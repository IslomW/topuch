package com.sharipov.topuch.application.dto.response;

import com.sharipov.topuch.domain.entity.Address;

import java.time.Instant;
import java.util.UUID;

public record ProfileResponseDTO(
         UUID profileId,
         String firstName,
         String lastName,
         Instant createdAt,
         Long phoneNumber,
         String email,
         int trustFactor,
         Address address
) {
}
