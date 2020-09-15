
package com.leftorright.rsscreator.entity;


import java.util.Map;

public class PageBean {
    private Integer offset;
    private Integer limit;
    private Map<String, Object> page;
    private String id;
    private Integer type;

    private String isbanner;
    private String isshow;
    private String key;
    private String publish;


    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Map<String, Object> getPage() {
        return page;
    }

    public void setPage(Map<String, Object> page) {
        this.page = page;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getIsshow() {
        return isshow;
    }

    public void setIsshow(String isshow) {
        this.isshow = isshow;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIsbanner() {
        return isbanner;
    }

    public void setIsbanner(String isbanner) {
        this.isbanner = isbanner;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "offset=" + offset +
                ", limit=" + limit +
                ", page=" + page +
                ", id='" + id + '\'' +
                ", type=" + type +
                ", isbanner='" + isbanner + '\'' +
                ", isshow='" + isshow + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }
}
