package com.leftorright.rsscreator.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

//过往案例
public class PastCases implements Serializable {
    private int id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatedate;
    private String podcastid;
    private String type;
    private PodcastInfo podcastInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getPodcastid() {
        return podcastid;
    }

    public void setPodcastid(String podcastid) {
        this.podcastid = podcastid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PodcastInfo getPodcastInfo() {
        return podcastInfo;
    }

    public void setPodcastInfo(PodcastInfo podcastInfo) {
        this.podcastInfo = podcastInfo;
    }

}
