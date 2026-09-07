package com.sharipov.topuch.application.dto.response;

import com.sharipov.topuch.domain.entity.Abuse;
import java.util.UUID;

public record ReportResponseDTO(
        UUID reportId,
        Abuse abuseType,
        String  message,
        UUID postId,
        UUID profileId) {
}
