package com.sharipov.topuch.application.dto;

import com.sharipov.topuch.domain.entity.Abuse;

public record ReportResponseDTO(
        Long reportId,
        Abuse abuseType,
        String  message,
        Long postId,
        Long profileId) {
}
