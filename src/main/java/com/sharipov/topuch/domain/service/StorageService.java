package com.sharipov.topuch.domain.service;

import com.sharipov.topuch.application.dto.response.PresignedImageResponse;

import java.util.List;

public interface StorageService {
    List<PresignedImageResponse> generatePresignedUploadUrls(List<String> fileNames);
}
