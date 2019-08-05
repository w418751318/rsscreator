package com.leftorright.rsscreator.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * 业务相关所有返回信息请添加到这里
 */
@ApiModel
public class RspData<DATA,ATTACH> {
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @ApiModelProperty(name = "uid", value = "用户uid", required = true, example = "11111")
    @JsonProperty("uid")
    private String uid;
    @ApiModelProperty(name = "username", value = "用户名", required = true, example = "admin")
    @JsonProperty("username")
    private String userName;





}
