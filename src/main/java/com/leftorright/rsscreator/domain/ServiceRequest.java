package com.leftorright.rsscreator.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ServiceRequest {

    @ApiModelProperty(name = "USER_NAME", value = "用户名", required = true, example = "mike")
    @JsonProperty("USER_NAME")
    @JSONField(name = "USER_NAME")
    private String userName;

    @ApiModelProperty(name = "PASS_WORD", value = "密码", required = true, example = "10010")
    @JsonProperty("PASS_WORD")
    @JSONField(name = "PASS_WORD")
    private String passWord;

    @ApiModelProperty(name = "UPLOAD_FILE_NAME", value = "上传文件名称", required = true, example = "文体不限.mp3")
    @JsonProperty("UPLOAD_FILE_NAME")
    @JSONField(name = "UPLOAD_FILE_NAME")
    private String upload_file_name;

    @ApiModelProperty(name = "UPLOAD_CONTENT_TYPE", value = "上传文件类型", required = true, example = "image/jpeg")
    @JsonProperty("UPLOAD_CONTENT_TYPE")
    @JSONField(name = "UPLOAD_CONTENT_TYPE")
    private String upload_content_type;


    @ApiModelProperty(name = "UPLOAD_TMP_PATH", value = "上传文件缓存地址", required = true, example = "/app/file")
    @JsonProperty("UPLOAD_TMP_PATH")
    @JSONField(name = "UPLOAD_TMP_PATH")
    private String upload_tmp_path;

    @ApiModelProperty(name = "UPLOAD_FILE_MD5", value = "上传文件md5", required = true, example = "adlkjflskdjf")
    @JsonProperty("UPLOAD_FILE_MD5")
    @JSONField(name = "UPLOAD_FILE_MD5")
    private String upload_file_md5;

    @ApiModelProperty(name = "UPLOAD_FILE_SIZE", value = "上传文件大小", required = true, example = "500m")
    @JsonProperty("UPLOAD_FILE_SIZE")
    @JSONField(name = "UPLOAD_FILE_SIZE")
    private String upload_file_size;


    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUpload_file_name() {
        return upload_file_name;
    }

    public void setUpload_file_name(String upload_file_name) {
        this.upload_file_name = upload_file_name;
    }

    public String getUpload_content_type() {
        return upload_content_type;
    }

    public void setUpload_content_type(String upload_content_type) {
        this.upload_content_type = upload_content_type;
    }

    public String getUpload_tmp_path() {
        return upload_tmp_path;
    }

    public void setUpload_tmp_path(String upload_tmp_path) {
        this.upload_tmp_path = upload_tmp_path;
    }

    public String getUpload_file_md5() {
        return upload_file_md5;
    }

    public void setUpload_file_md5(String upload_file_md5) {
        this.upload_file_md5 = upload_file_md5;
    }

    public String getUpload_file_size() {
        return upload_file_size;
    }

    public void setUpload_file_size(String upload_file_size) {
        this.upload_file_size = upload_file_size;
    }
}