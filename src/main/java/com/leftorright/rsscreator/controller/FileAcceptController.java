package com.leftorright.rsscreator.controller;

import com.leftorright.rsscreator.service.UploadFileService;
import com.leftorright.rsscreator.utils.ReturnValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/managepodcast")
public class FileAcceptController {

    @Autowired
    private UploadFileService uploadFileService;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ReturnValue upLoadFile(@RequestParam("uploadFile") MultipartFile uploadFile){
        return uploadFileService.uploadFile(uploadFile);
    }
}
