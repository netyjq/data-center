package com.yjq.data.service.api.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjq.data.client.api.core.DataQueryErrorEnum;
import com.yjq.data.client.api.core.DataQueryException;
import com.yjq.data.client.api.core.Page;
import com.yjq.data.client.api.invoke.DataQueryInvoker;
import com.yjq.data.client.api.query.AbstractQuery;
import com.yjq.data.client.api.query.PageQuery;
import com.yjq.data.client.api.query.SimpleQuery;
import com.yjq.data.service.config.MyFreemarkerConfig;
import com.yjq.data.service.mapper.IDynamicSqlMapper;
import com.yjq.data.service.mapper.ISqlTemplateMapper;
import com.yjq.data.service.model.SqlTemplate;
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
    public LinkedHashMap<String, Object> queryForObject(SimpleQuery query) throws DataQueryException {
        Map<String, Object> filters = getSqlMap(query);
        logger.info(">>> queryForObject sql开始执行. params: {}", JSON.toJSONString(filters));
        try {
            List<LinkedHashMap<String, Object>> list = dynamicSqlMapper.selectList(filters);
            if (list == null || list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } catch (Exception e) {
            throw new DataQueryException(DataQueryErrorEnum.SQL_EXECUTE_ERROR, e);
        }
    }

    @Override
    public List<LinkedHashMap<String, Object>> queryForList(SimpleQuery query) throws DataQueryException {
        Map<String, Object> filters = getSqlMap(query);
        logger.info(">>> queryForList sql开始执行. params: {}", JSON.toJSONString(filters));
        try {
            return dynamicSqlMapper.selectList(query.getFilters());
        } catch (Exception e) {
            throw new DataQueryException(DataQueryErrorEnum.SQL_EXECUTE_ERROR, e);
        }
    }

    @Override
    public Page<LinkedHashMap<String, Object>> queryForPage(PageQuery query) throws DataQueryException {
        Map<String, Object> filters = getSqlMap(query);
        logger.info(">>> queryForPage sql开始执行. params: {}", JSON.toJSONString(filters));
        try {
            PageInfo<LinkedHashMap<String, Object>> pageInfo = PageHelper.startPage(query.getPageNum(), query.getPageSize()).doSelectPageInfo(() -> {
                dynamicSqlMapper.selectList(filters);
            });
            return new Page<>((int) pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getList());
        } catch (Exception e) {
            throw new DataQueryException(DataQueryErrorEnum.SQL_EXECUTE_ERROR, e);
        }
    }

    @Override
    public long queryCount(SimpleQuery simpleQuery) throws DataQueryException {
        Map<String, Object> filters = getSqlMap(simpleQuery);
        logger.info(">>> queryCount sql开始执行. params: {}", JSON.toJSONString(filters));
        try {
            return dynamicSqlMapper.selectCount(filters);
        } catch (Exception e) {
            throw new DataQueryException(DataQueryErrorEnum.SQL_EXECUTE_ERROR, e);
        }
    }

    private Map<String, Object> getSqlMap(AbstractQuery query) {
        SqlTemplate sqlTemplate = sqlTemplateMapper.selectOne(query.getSqlId(), query.getAppId());
        Map<String, Object> filters = query.getFilters();
        String sql = null;
        try {
            sql = myFreemarkerConfig.render(query.getSqlId(), sqlTemplate.getSqltext(), filters);
        } catch (IOException | TemplateException e) {
            throw new DataQueryException(DataQueryErrorEnum.TEMPLATE_RENDER_ERROR, e);
        }
        filters.put("sql", sql);
        return filters;
    }

}
