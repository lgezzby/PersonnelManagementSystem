package com.zjgsu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 对应公告:公告编号、公告标题、公告内容、发布日期
 * Created by zby on 2017/7/7.
 */
public class Notice implements Serializable{
    private Integer id;
    private String title;
    private String content;
    private Date createDate;
    private User user;

    public Notice() {
        super();
    }

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
