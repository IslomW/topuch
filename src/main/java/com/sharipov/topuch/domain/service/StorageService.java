package com.sharipov.topuch.domain.service.impl;

import java.util.List;

public interface StorageService {
    List<PresignedImageResponse> generatePresignedUploadUrls(List<String> fileNames);
}
