package com.yjq.data.service.mapper;

import com.yjq.data.service.model.AlarmHistory;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-07
 */
@Mapper
public interface IAlarmHistoryMapper {

    /**
     * 新增
     * @param alarmHistory 告警记录
     */
    void insertOne(AlarmHistory alarmHistory);

}
