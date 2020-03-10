package com.yjq.data.client.api.manage;

import com.yjq.data.client.api.core.DataQueryException;
import com.yjq.data.client.api.core.Page;
import com.yjq.data.client.api.core.ResponseMessage;
import com.yjq.data.client.api.query.PageQuery;
import com.yjq.data.client.api.query.SimpleQuery;

import java.util.List;

/**
 * @author yejiaqian
 * @date Created in 2020/3/9 14:19 <br>
 */
public interface DataQueryManage<T> {

    /**
     * 查询数据列表
     * @param simpleQuery parameters
     * @param tClass T
     * @return ResponseMessage<List<T>>
     * @throws DataQueryException DataQueryException
     */
    ResponseMessage<List<T>> queryForList(SimpleQuery simpleQuery, Class<T> tClass) throws DataQueryException;

    /**
     * 查询单条数据
     * @param simpleQuery parameters
     * @param tClass t
     * @return ResponseMessage<T>
     * @throws DataQueryException DataQueryException
     */
    ResponseMessage<T> queryForObject(SimpleQuery simpleQuery, Class<T> tClass) throws DataQueryException;

    /**
     * 分页查询
     * @param pageQuery parameters
     * @param tClass t
     * @return ResponseMessage<Page<T>>
     * @throws DataQueryException DataQueryException
     */
    ResponseMessage<Page<T>> queryForPage(PageQuery pageQuery, Class<T> tClass) throws DataQueryException;

    /**
     * 查询数量
     * @param simpleQuery parameters
     * @return ResponseMessage<Long>
     * @throws DataQueryException DataQueryException
     */
    ResponseMessage<Long> queryForCount(SimpleQuery simpleQuery) throws DataQueryException;

}
