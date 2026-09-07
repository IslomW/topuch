package com.sharipov.topuch.application.dto.response;

import java.util.UUID;

public record SellerResponseDTO(
        UUID profileId,
        String firstName,
        String lastName,
        int trustFactor
) {
}
