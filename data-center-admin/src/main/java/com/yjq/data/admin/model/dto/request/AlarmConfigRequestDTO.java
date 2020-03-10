package com.yjq.data.admin.model.dto.request;

import com.yjq.data.admin.common.AlarmRuleEnum;
import com.yjq.data.admin.common.validation.ValidationMarker;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-05
 */
@Data
public class AlarmConfigRequestDTO extends PageRequestDTO {


    /**
     * id
     */
    @NotNull(groups = {ValidationMarker.UpdateGroup.class, ValidationMarker.DeleteGroup.class})
    private Integer id;

    /**
     * 应用id
     */
    @NotNull(groups = {ValidationMarker.InsertGroup.class, ValidationMarker.UpdateGroup.class})
    private Integer appId;

    /**
     * 阈值
     */
    @NotNull(groups = {ValidationMarker.InsertGroup.class, ValidationMarker.UpdateGroup.class})
    @Range(min = 1, max = 100, groups = {ValidationMarker.InsertGroup.class, ValidationMarker.UpdateGroup.class})
    private Integer threshold;

    /**
     * 告警类型
     */
    @NotNull(groups = {ValidationMarker.InsertGroup.class, ValidationMarker.UpdateGroup.class})
    private Integer rule;

    /**
     * 用户组id
     */
    @NotNull(groups = {ValidationMarker.InsertGroup.class, ValidationMarker.UpdateGroup.class})
    private Integer userGroupId;

    @Override
    public boolean validate() {
        AlarmRuleEnum alarmRuleEnum = AlarmRuleEnum.getByRuleCode(rule);
        if (alarmRuleEnum == null) {
            return false;
        }
        return true;
    }
}
