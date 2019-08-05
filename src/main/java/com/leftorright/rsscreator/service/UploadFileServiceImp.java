package com.leftorright.rsscreator.service;

import com.leftorright.rsscreator.utils.ReturnCodeAndMsgEnum;
import com.leftorright.rsscreator.utils.ReturnValue;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
@Service
public class UploadFileServiceImp implements UploadFileService {

    private static final Logger logger = LoggerFactory.getLogger(UploadFileServiceImp.class);

    @Override
    public String uploadFile(MultipartFile zipFile) {
        String targetFilePath = "/app/file/";
//        String fileName = UUID.randomUUID().toString().replace("-", "");
        String fileName = zipFile.getOriginalFilename();
        logger.info("fileName:"+fileName);
        File targetFile = new File(targetFilePath + File.separator + fileName);

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(targetFile);
            IOUtils.copy(zipFile.getInputStream(), fileOutputStream);
            logger.info("------>>>>>>uploaded a file successfully!<<<<<<------");
        } catch (IOException e) {
            logger.info("------>>>>>>uploaded a file IOException!<<<<<<------"+e.toString());
//            return new ReturnValue<>(-1, null);
            return "upload fail";
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                logger.error("", e);
            }
        }
//        return new ReturnValue<>(ReturnCodeAndMsgEnum.Success, null);
        return "upload success!";
    }
}
