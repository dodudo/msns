package com.dxg.msns.upload.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String upload(MultipartFile file);

    String uploadMusic(MultipartFile file);
}
