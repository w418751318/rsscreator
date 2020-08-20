package com.leftorright.rsscreator.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BlogShow implements Serializable {
    private Integer id;
    private String type;//'类型 1.原创 2.企业',
    private String webbanner;//web的banner图',
    private String appbanner; //'app的banner图',
    private String isbanner;//'是否在banner区域显示',
    private String isshow;//'是否在show区域显示',
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatedate;
    private Integer podcastid;
    //收听渠道
    private List<Channel> channelList;
    private String channelIdList;
    
    private String anchordesc;
    private String link;
    private String name;
    private String creator;
    private List<Members> membersList;
    private String title;
    private String text;
    //收听标记
    private List<Channel> channels;
    private String channelIds;
    private String banner;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWebbanner() {
        return webbanner;
    }

    public void setWebbanner(String webbanner) {
        this.webbanner = webbanner;
    }

    public String getAppbanner() {
        return appbanner;
    }

    public void setAppbanner(String appbanner) {
        this.appbanner = appbanner;
    }

    public String getIsbanner() {
        return isbanner;
    }

    public void setIsbanner(String isbanner) {
        this.isbanner = isbanner;
    }

    public String getIsshow() {
        return isshow;
    }

    public void setIsshow(String isshow) {
        this.isshow = isshow;
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

    public Integer getPodcastid() {
        return podcastid;
    }

    public void setPodcastid(Integer podcastid) {
        this.podcastid = podcastid;
    }

    public List<Channel> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<Channel> channelList) {
        this.channelList = channelList;
    }

    public String getAnchordesc() {
        return anchordesc;
    }

    public void setAnchordesc(String anchordesc) {
        this.anchordesc = anchordesc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<Members> getMembersList() {
        return membersList;
    }

    public void setMembersList(List<Members> membersList) {
        this.membersList = membersList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public String getChannelIds() {
        return channelIds;
    }

    public void setChannelIds(String channelIds) {
        this.channelIds = channelIds;
    }

    public String getChannelIdList() {
        return channelIdList;
    }

    public void setChannelIdList(String channelIdList) {
        this.channelIdList = channelIdList;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

}


