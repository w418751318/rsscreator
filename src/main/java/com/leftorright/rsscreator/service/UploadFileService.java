package com.leftorright.rsscreator.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
    public String uploadFile(MultipartFile zipFile, String fileType);
}
