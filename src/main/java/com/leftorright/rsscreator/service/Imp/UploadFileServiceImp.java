package com.leftorright.rsscreator.service.Imp;


import com.alibaba.fastjson.JSONObject;
import com.leftorright.rsscreator.service.UploadFileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.beans.Encoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class UploadFileServiceImp implements UploadFileService {

    private static final Logger logger = LoggerFactory.getLogger(UploadFileServiceImp.class);

    @Override
    public String uploadFile(MultipartFile zipFile, String fileType, String feedName) {
        String targetFilePath = "/app/file/";
//        String targetFilePath = "/home/jus/";
        String duration = "";
//        String targetFilePath = "C:\\Users\\unicom\\Desktop\\";
        if (zipFile != null) {
            String fileName = zipFile.getOriginalFilename();
            File targetFile;
            // 当feedName为空，则表示上传的是图片，上传路径为/app/file/pic/logo.png
            if ("".equals(feedName)) {
                targetFile = new File(targetFilePath + File.separator + fileType + File.separator + fileName);
            } else {
                // 当feedName不为空，则表示上传的是音频文件，拼装音频文件路径，在音频路径里加上feedname,eg /app/file/audio/leftright/
                targetFile = new File(targetFilePath + File.separator + fileType + File.separator + feedName + File.separator + fileName);
            }
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(targetFile);
                IOUtils.copy(zipFile.getInputStream(), fileOutputStream);
                logger.info("------>>>>>>uploaded a file successfully!<<<<<<------");
            } catch (IOException e) {
                logger.info("------>>>>>>uploaded a file IOException!<<<<<<------" + e.toString());
                return "upload file fail!";
            } finally {
                try {
                    if(fileOutputStream != null){
                        fileOutputStream.close();
                    }

                } catch (IOException e) {
                    logger.error("", e);
                    return "upload file fail!";
                }
            }
            try {
                if(fileType.equals("audio")){
                    //获取音频文件时长
                    MP3File f = (MP3File) AudioFileIO.read(targetFile);
                    MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
                    duration = audioHeader.getTrackLength() + "";
                    logger.info("duration:" + duration);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject uploadReturn = new JSONObject();
            uploadReturn.put("fileName", fileName);
            uploadReturn.put("duration", duration);
            return uploadReturn.toString();
        } else {
            return "upload file fail!(uploadfile == null)";
        }

    }
}
