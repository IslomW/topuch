package com.sharipov.topuch.application.dto.request;

import com.sharipov.topuch.domain.entity.Abuse;

public record ReportRequestDTO(
        Long postId,
        Abuse abuseType,
        String  message

) {
}
