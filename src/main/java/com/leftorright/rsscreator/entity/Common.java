package com.leftorright.rsscreator.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Common implements Serializable {
    private Integer id;
    private String title;
    private String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date recruittime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date publishtime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatetime;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRecruittime() {
        return recruittime;
    }

    public void setRecruittime(Date recruittime) {
        this.recruittime = recruittime;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
