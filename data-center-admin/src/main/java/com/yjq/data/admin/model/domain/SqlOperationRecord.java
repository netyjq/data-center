package com.yjq.data.admin.model.domain;

import com.yjq.data.admin.model.AbstractModel;
import lombok.Data;

import java.util.Date;

/**
 * sql模板操作记录
 * @author netyjq@gmail.com
 * @date 2019-04-26
 */
@Data
public class SqlOperationRecord extends AbstractModel<Integer> {

    private Integer id;

    /**
     * sql id
     */
    private String sqlId;

    /**
     * 操作人id
     */
    private Integer operatorId;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 操作类型
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改前sql
     */
    private String sqltextBefore;

    /**
     * 修改后sql
     */
    private String sqltextAfter;

    /**
     * 修改前分页情况
     */
    private Integer pageBefore;

    /**
     * 修改后分页情况
     */
    private Integer pageAfter;

    /**
     * 修改前表字段映射情况
     */
    private String parameterMappingBefore;

    /**
     * 修改后表字段映射情况
     */
    private String parameterMappingAfter;

}
