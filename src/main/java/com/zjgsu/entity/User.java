package com.zjgsu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 对应用户:用户编号、用户名、登录名、密码、状态及建档日期
 * Created by zby on 2017/7/7.
 */
public class User implements Serializable{
    private Integer id;
    private String username;
    private String loginname;
    private String password;
    private Integer status;
    private Date createDate;

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
