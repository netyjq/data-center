package com.yjq.data.admin.model.domain;

import com.yjq.data.admin.model.AbstractModel;
import lombok.Data;

import java.util.Date;

/**
 * 告警配置
 * @author netyjq@gmail.com
 * @date 2019-04-26
 */
@Data
public class AlarmConfig extends AbstractModel<Integer> {

    private Integer id;

    /**
     * 应用id
     */
    private Integer appId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 创建人id
     */
    private Integer createId;

    /**
     * 创建人姓名
     */
    private String createName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人id
     */
    private Integer updateId;

    /**
     * 更新人姓名
     */
    private String updateName;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 阈值
     */
    private Integer threshold;

    /**
     * 告警规则 SLOW_COUNT(1), ERROR_COUNT(2);
     */
    private Integer rule;

    /**
     * 用户组id
     */
    private Integer userGroupId;

    /**
     * 用户组名称
     */
    private String userGroupName;


    public AlarmConfig(Integer appId, String appName, Integer createId, String createName, Integer updateId,
                       String updateName, Integer threshold, Integer rule, Integer userGroupId, String userGroupName) {
        this.appId = appId;
        this.appName = appName;
        this.createId = createId;
        this.createName = createName;
        this.updateId = updateId;
        this.updateName = updateName;
        this.threshold = threshold;
        this.rule = rule;
        this.userGroupId = userGroupId;
        this.userGroupName = userGroupName;
    }

    public AlarmConfig() {
    }

}
