package com.leftorright.rsscreator.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 调用返回实例
 */
@ApiModel
public class ResponesMSG {
    //
    @ApiModelProperty(name = "RSP_DESC", value = "调用返回标识信息", required = true, example = "收到请求，正在处理")
    @JsonProperty("RSP_DESC")
    private String rspDesc;
    @ApiModelProperty(name = "RSP_CODE", value = "调用返回标识信息", required = true, example = "0000")
    @JsonProperty("RSP_CODE")
    private String rspCode;
    @ApiModelProperty(name = "RECORD_SEQUENCE_ID", value = "调用返回标识信息", required = true, example = "999999999")
    @JsonProperty("RECORD_SEQUENCE_ID")
    private String recordSequenceId;

    public String getRspDesc() {
        return rspDesc;
    }

    public void setRspDesc(String rspDesc) {
        this.rspDesc = rspDesc;
    }
    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }
    public void setRecordSequenceId(String recordSequenceId) {
        this.recordSequenceId = recordSequenceId;
    }
}