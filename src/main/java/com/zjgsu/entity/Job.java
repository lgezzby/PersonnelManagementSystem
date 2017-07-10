package com.zjgsu.entity;

import java.io.Serializable;

/**
 * 对应职位:职位编号、职位名称及详细描述
 * Created by zby on 2017/7/7.
 */
public class Job implements Serializable{
    private Integer id;
    private String name;
    private String remark;

    public Job() {
        super();
    }

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
