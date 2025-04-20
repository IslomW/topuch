package com.sharipov.topuch.application.dto.response;

import com.sharipov.topuch.domain.entity.Address;

import java.time.LocalDateTime;

public record ProfileResponseDTO(
         Long profileId,
         String firstName,
         String lastName,
         LocalDateTime createdAt,
         Long phoneNumber,
         String email,
         Address address
) {
}
