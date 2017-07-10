package com.zjgsu.entity;

import java.io.Serializable;

/**
 * 对应部门:部门编号、部门名称及详细描述
 * Created by zby on 2017/7/7.
 */
public class Dept implements Serializable{
    private Integer id;
    private String name;
    private String remark;

    public Dept() {
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
