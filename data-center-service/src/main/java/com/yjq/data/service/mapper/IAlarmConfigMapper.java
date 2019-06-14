package com.yjq.data.service.mapper;

import com.yjq.data.service.model.AlarmConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-07
 */
@Mapper
public interface IAlarmConfigMapper {

    /**
     * 查询告警配置
     * @param filters 入参
     * @return
     */
    List<AlarmConfig> selectList(Map<String, Object> filters);

}
