package com.yjq.data.service.api.impl;

import com.alibaba.fastjson.JSON;
import com.yjq.data.client.api.core.DataQueryInvoker;
import com.yjq.data.client.api.core.DataStatisticsErrorEnum;
import com.yjq.data.client.api.core.ResponseMessage;
import com.yjq.data.client.api.query.AbstractQuery;
import com.yjq.data.client.api.query.PageQuery;
import com.yjq.data.client.api.query.SimpleQuery;
import com.yjq.data.service.common.SqlException;
import com.yjq.data.service.config.MyFreemarkerConfig;
import com.yjq.data.service.mapper.IDynamicSqlMapper;
import com.yjq.data.service.mapper.ISqlTemplateMapper;
import com.yjq.data.service.model.SqlTemplate;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author netyjq@gmail.com
 * @date 2019/4/23
 */
@Component
public class DataQueryInvokerImpl implements DataQueryInvoker {

    @Autowired
    private IDynamicSqlMapper dynamicSqlMapper;

    @Autowired
    private ISqlTemplateMapper sqlTemplateMapper;

    @Autowired
    private MyFreemarkerConfig myFreemarkerConfig;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ResponseMessage<LinkedHashMap> queryForObject(SimpleQuery query) throws IOException, TemplateException {
        Map<String, Object> filters = getSqlMap(query);
        logger.info(">>> sql开始执行. params: {}", JSON.toJSONString(filters));
        try {
            List<LinkedHashMap> list = dynamicSqlMapper.selectList(filters);
            if (list == null || list.isEmpty()) {
                return null;
            }
            return new ResponseMessage<LinkedHashMap>().success(list.get(0));
        } catch (Exception e) {
            throw new SqlException(DataStatisticsErrorEnum.SQL_EXECUTE_ERROR.getDesc() + e.getMessage());
        }
    }

    @Override
    public ResponseMessage<List<LinkedHashMap>> queryForList(SimpleQuery query) throws IOException, TemplateException {
        Map<String, Object> filters = getSqlMap(query);
        logger.info(">>> sql开始执行. params: {}", JSON.toJSONString(filters));
        try {
            List<LinkedHashMap> list = dynamicSqlMapper.selectList(query.getFilters());
            return new ResponseMessage<List<LinkedHashMap>>().success(list);
        } catch (Exception e) {
            throw new SqlException(DataStatisticsErrorEnum.SQL_EXECUTE_ERROR.getDesc() + e.getMessage());
        }
    }

    @Override
    public ResponseMessage<PageInfo<LinkedHashMap>> queryForPage(PageQuery query) throws IOException, TemplateException {
        Map<String, Object> filters = getSqlMap(query);
        logger.info(">>> sql开始执行. params: {}", JSON.toJSONString(filters));
        try {
            PageInfo<LinkedHashMap> pageInfo = PageHelper.startPage(query.getPageNum(), query.getPageSize()).doSelectPageInfo(() -> {
                dynamicSqlMapper.selectList(filters);
            });
            return new ResponseMessage<PageInfo<LinkedHashMap>>().success(pageInfo);
        } catch (Exception e) {
            throw new SqlException(DataStatisticsErrorEnum.SQL_EXECUTE_ERROR.getDesc() + e.getMessage());
        }
    }

    private Map<String, Object> getSqlMap(AbstractQuery query) throws IOException, TemplateException {
        SqlTemplate sqlTemplate = sqlTemplateMapper.selectOne(query.getSqlId(), query.getAppId());
        Map<String, Object> filters = query.getFilters();
        String sql = myFreemarkerConfig.render(query.getSqlId(), sqlTemplate.getSqltext(), filters);
        filters.put("sql", sql);
        return filters;
    }

}
