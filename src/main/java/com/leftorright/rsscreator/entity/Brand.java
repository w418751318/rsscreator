package com.leftorright.rsscreator.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id; // 主键ID

    private String name;
    private String logo;
    private String link;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatedate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

}
