package com.yjq.data.client.api.invoke;

import com.yjq.data.client.api.core.DataQueryException;
import com.yjq.data.client.api.core.Page;
import com.yjq.data.client.api.query.PageQuery;
import com.yjq.data.client.api.query.SimpleQuery;

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
    LinkedHashMap<String, Object> queryForObject(SimpleQuery query) throws DataQueryException;

    /**
     * 批量查询
     * @param query query
     * @return List<LinkedHashMap> result list
     */
    List<LinkedHashMap<String, Object>> queryForList(SimpleQuery query) throws DataQueryException;

    /**
     * 分页查询
     * @param pageQuery PageRequest
     * @return Page<LinkedHashMap>
     */
    Page<LinkedHashMap<String, Object>> queryForPage(PageQuery pageQuery) throws DataQueryException;

    /**
     * 查询数量
     * @param simpleQuery SimpleQuery
     * @return long
     */
    long queryCount(SimpleQuery simpleQuery) throws DataQueryException;

}
