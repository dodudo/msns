package com.dxg.msns.upload.service;

import com.dxg.msns.music.pojo.Music;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String upload(MultipartFile[] file);

    Music uploadMusic(MultipartFile file);
}
