package com.leftorright.rsscreator.domain.response;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务应答类
 */
public class BaseResponse<DATA, ATTACH> {
    private static Logger logger = LoggerFactory.getLogger(BaseResponse.class);

    @ApiModelProperty(name = "status", value = "服务请求结果编码", required = true, example = "0000")
    @JsonProperty("status")
    private String status;
    @ApiModelProperty(name = "msg", value = "服务请求结果描述", required = true, example = "success")
    @JsonProperty("msg")
    private String msg;
    @ApiModelProperty(name = "rspData", value = "返回数据", required = true, example = "{}")
    @JsonProperty("rspData")
    private JSONObject rspData;

    public JSONObject getRspData() {
        return rspData;
    }

    public void setRspData(JSONObject rspData) {
        this.rspData = rspData;
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

}
