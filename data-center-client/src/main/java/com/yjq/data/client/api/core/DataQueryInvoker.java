package com.yjq.data.client.api.core;

import com.yjq.data.client.api.query.PageQuery;
import com.yjq.data.client.api.query.SimpleQuery;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author netyjq@gmail.com
 * @date 2019/4/22
 */
public interface DataQueryInvoker {

    /**
     * 查询单个
     * @param query 入参
     * @return LinkedHashMap
     */
    ResponseMessage<LinkedHashMap> queryForObject(SimpleQuery query) throws IOException, TemplateException;

    /**
     * 批量查询
     * @param query query
     * @return List<LinkedHashMap> result list
     */
    ResponseMessage<List<LinkedHashMap>> queryForList(SimpleQuery query) throws IOException, TemplateException;

    /**
     * 分页查询
     * @param pageQuery PageRequest
     * @return Page<LinkedHashMap>
     */
    ResponseMessage<com.github.pagehelper.PageInfo<LinkedHashMap>> queryForPage(PageQuery pageQuery) throws IOException, TemplateException;


}
