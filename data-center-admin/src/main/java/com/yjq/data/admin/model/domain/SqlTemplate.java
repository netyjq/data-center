package com.yjq.data.admin.model.domain;

import com.yjq.data.admin.model.AbstractModel;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * sql模板
 * @author netyjq@gmail.com
 * @date 2019/4/24
 */
@Data
public class SqlTemplate extends AbstractModel<String> {

    private String id;

    /**
     * sql模板
     */
    private String sqltext;

    /**
     * 描述
     */
    private String description;

    /**
     * 应用id
     */
    private Integer appId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 状态0 正常 -1 禁用
     */
    private Integer enableStatus;

    /**
     * 是否分页 0分页 -1不分页
     */
    private Integer paging;

    /**
     * 出参映射 like: {"app_id":"appId","enable_status":enableStatus}
     */
    private String parameterMapping;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人 equals User#getName
     */
    private String createName;

    /**
     * 创建人id
     */
    private Integer createId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateName;

    /**
     * 更新人id
     */
    private Integer updateId;

    public SqlTemplate(String sqltext, String description, Integer appId, String appName, Integer enableStatus,
                       Integer paging, String parameterMapping, String createName, Integer createId) {
        this.sqltext = sqltext;
        this.description = description;
        this.appId = appId;
        this.appName = appName;
        this.enableStatus = enableStatus;
        this.paging = paging;
        this.parameterMapping = parameterMapping;
        this.createTime = new Date();
        this.createName = createName;
        this.id = UUID.randomUUID().toString();
        this.createId = createId;
    }

    public SqlTemplate() {
    }

}
