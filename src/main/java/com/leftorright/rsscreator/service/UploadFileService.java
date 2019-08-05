package com.leftorright.rsscreator.service;

import com.leftorright.rsscreator.utils.ReturnValue;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
//    public ReturnValue uploadFile(MultipartFile zipFile);
    public String uploadFile(MultipartFile zipFile);
}
