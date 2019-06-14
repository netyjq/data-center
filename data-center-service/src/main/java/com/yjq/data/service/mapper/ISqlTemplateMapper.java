package com.yjq.data.service.mapper;

import com.yjq.data.service.model.SqlTemplate;
import org.apache.ibatis.annotations.*;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-28
 */
@Mapper
public interface ISqlTemplateMapper {

    /**
     * 查询 sql模板
     * @param sqlTemplateId sql模板id
     * @param appId 应用id
     * @return SqlTemplate
     */
    SqlTemplate selectOne(@Param("sqlTemplateId") String sqlTemplateId, @Param("appId") Integer appId);

}
