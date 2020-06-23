package com.leftorright.rsscreator.controller;

import com.leftorright.rsscreator.service.UploadFileService;
import com.leftorright.rsscreator.service.Imp.UploadFileServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/managepodcast")
public class FileAcceptController {
    private static final Logger logger = LoggerFactory.getLogger(UploadFileServiceImp.class);

    @Autowired
    private UploadFileService uploadFileService;

    //上传图片接口：ip:port/managepodcast/uploadpic
    @RequestMapping(value = "/uploadpic", method = RequestMethod.POST)
    public String upLoadPic(MultipartFile uploadFile) {
        String fileType = "pic";
        return uploadFileService.uploadFile(uploadFile, fileType, "");
    }

    //上传音频接口：ip:port/managepodcast/uploadpic
    @RequestMapping(value = "/uploadaudio", method = RequestMethod.POST)
    public String upLoadAudio(MultipartFile uploadFile, @RequestParam("feedName") String feedName) {
        String fileType = "audio";
        logger.info("feedName=="+feedName);
        return uploadFileService.uploadFile(uploadFile, fileType, feedName);
    }
}
