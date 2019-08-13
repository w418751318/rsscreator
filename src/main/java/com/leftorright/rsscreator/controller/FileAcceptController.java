package com.leftorright.rsscreator.controller;

import com.leftorright.rsscreator.service.UploadFileService;
import com.leftorright.rsscreator.service.Imp.UploadFileServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/managepodcast")
public class FileAcceptController {
    private static final Logger logger = LoggerFactory.getLogger(UploadFileServiceImp.class);

    @Autowired
    private UploadFileService uploadFileService;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upLoadFile(MultipartFile uploadFile){
        return uploadFileService.uploadFile(uploadFile);
    }
}
