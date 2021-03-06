package com.leftorright.rsscreator.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_podcast") // 指定关联的数据库的表名
public class PodcastInfo {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id; // 主键ID

    private String subtitle;
    private String link;
    private String description;
    private String author;
    private String image;
    private String email;
    private String podcastname;
    private String feedname;

    private String keywords;
    private String firstcategorycode;
    private String secondcategorycode;

    private BlogShow blogShow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPodcastname() {
        return podcastname;
    }

    public void setPodcastname(String podcastname) {
        this.podcastname = podcastname;
    }

    public String getFeedname() {
        return feedname;
    }

    public void setFeedname(String feedname) {
        this.feedname = feedname;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getFirstcategorycode() {
        return firstcategorycode;
    }

    public void setFirstcategorycode(String firstcategorycode) {
        this.firstcategorycode = firstcategorycode;
    }

    public String getSecondategorycode() {
        return secondcategorycode;
    }

    public void setSecondategorycode(String secondcategorycode) {
        this.secondcategorycode = secondcategorycode;
    }

    public BlogShow getBlogShow() {
        return blogShow;
    }

    public void setBlogShow(BlogShow blogShow) {
        this.blogShow = blogShow;
    }
    
}
