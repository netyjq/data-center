package com.yjq.data.service.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author netyjq@gmail.com
 * @date 2019/4/23
 */
@Mapper
public interface IDynamicSqlMapper {

    /**
     * 查询列表
     * @param filters 过滤条件
     * @return List<LinkedHashMap>
     */
    List<LinkedHashMap> selectList(Map<String, Object> filters);

}
