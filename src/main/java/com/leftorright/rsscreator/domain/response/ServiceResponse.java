package com.leftorright.rsscreator.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务应答类
 */
public class ServiceResponse<DATA,ATTACH> {
    private static Logger logger =  LoggerFactory.getLogger(ServiceResponse.class);


    @ApiModelProperty(name = "status", value = "服务请求结果编码", required = true, example = "0000")
    @JsonProperty("status")
    private String status;
    @ApiModelProperty(name = "msg", value = "服务请求结果描述", required = true, example = "success")
    @JsonProperty("msg")
    private String msg;

    @ApiModelProperty(name = "uid", value = "用户uid", required = true, example = "11111")
    @JsonProperty("uid")
    private String uid;

    @ApiModelProperty(name = "username", value = "用户名", required = true, example = "admin")
    @JsonProperty("username")
    private String username;


    @ApiModelProperty(name = "permissions", value = "用户权限", required = true, example = "Dashboard")
    @JsonProperty("permissions")
    private String[] permissions;

    @ApiModelProperty(name = "data", value = "服务返回业务数据", required = true)
    @JsonProperty("data")
    private RspData<DATA,ATTACH> rspData;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }


    /**
     * 服务其他错误返回结果
     * @param errorStatus
     * @param errorMsg
     * @param rspData
     * @return
     */
    public ServiceResponse getErrorResponse(String errorStatus, String errorMsg, RspData<DATA, ATTACH> rspData){
        this.setStatus(errorStatus);
        this.setMsg(errorMsg);
        this.setRspData(rspData);
        return this;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public RspData<DATA, ATTACH> getRspData() {
        return rspData;
    }

    public void setRspData(RspData<DATA, ATTACH> rspData) {
        this.rspData = rspData;
    }
}
