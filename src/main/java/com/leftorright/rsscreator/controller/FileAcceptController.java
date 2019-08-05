package com.leftorright.rsscreator.controller;

import com.alibaba.fastjson.JSONObject;
import com.leftorright.rsscreator.domain.ResponesMSG;
import com.leftorright.rsscreator.domain.ServiceRequest;
import com.leftorright.rsscreator.domain.response.ServiceResponse;
import com.leftorright.rsscreator.service.UploadFileService;
import com.leftorright.rsscreator.service.UploadFileServiceImp;
import com.leftorright.rsscreator.utils.ReturnValue;
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
        logger.info("uploadFile.getOriginalFilename:"+JSONObject.toJSONString(uploadFile.getOriginalFilename()));
//        return "Success";
        return uploadFileService.uploadFile(uploadFile);
    }
//    @RequestMapping(value = "/upload",method = RequestMethod.POST)
//    public String upLoadFile(@RequestBody ServiceRequest sreq){
//        logger.info("requestParameter:"+JSONObject.toJSONString(sreq));
//        logger.info("data:"+sreq);
//        return "data:"+sreq;
//    }
}
