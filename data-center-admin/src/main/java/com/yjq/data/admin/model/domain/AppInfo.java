package com.yjq.data.admin.model.domain;

import com.yjq.data.admin.model.AbstractModel;

import java.util.Date;

/**
 * 应用
 * @author netyjq@gmail.com
 * @date 2019-04-26
 */
public class AppInfo extends AbstractModel<Integer> {

    private Integer id;

    /**
     * 应用名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createTime;

    public AppInfo(String name) {
        this.name = name;
        this.createTime = new Date();
    }

    public AppInfo() {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
