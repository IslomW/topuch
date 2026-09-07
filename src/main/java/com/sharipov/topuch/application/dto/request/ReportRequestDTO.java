package com.sharipov.topuch.application.dto.request;

import com.sharipov.topuch.domain.entity.Abuse;
import java.util.UUID;

public record ReportRequestDTO(
        UUID postId,
        Abuse abuseType,
        String  message

) {
}
