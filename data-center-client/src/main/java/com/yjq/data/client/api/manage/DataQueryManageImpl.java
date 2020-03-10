package com.yjq.data.client.api.manage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.yjq.data.client.api.core.DataQueryErrorEnum;
import com.yjq.data.client.api.core.DataQueryException;
import com.yjq.data.client.api.core.Page;
import com.yjq.data.client.api.core.ResponseMessage;
import com.yjq.data.client.api.invoke.DataQueryInvoker;
import com.yjq.data.client.api.query.PageQuery;
import com.yjq.data.client.api.query.SimpleQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author yejiaqian
 * @date Created in 2020/3/9 09:17 <br>
 */
@Service
public class DataQueryManageImpl<T> implements DataQueryManage<T> {

    @Resource
    private DataQueryInvoker dataQueryInvoker;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    @Override
    public ResponseMessage<List<T>> queryForList(SimpleQuery simpleQuery, Class<T> tClass) throws DataQueryException {
        try {
            List<LinkedHashMap<String, Object>> mapList = dataQueryInvoker.queryForList(simpleQuery);
            if (mapList.isEmpty()) {
                return new ResponseMessage<List<T>>();
            }
            List<T> tList = convertValueList(tClass, mapList);
            return new ResponseMessage<List<T>>().success(tList);
        } catch (Exception e) {
            if (e instanceof DataQueryException) {
                throw e;
            }
            throw new DataQueryException(DataQueryErrorEnum.EXECUTING_ERROR, e);
        }
    }

    @Override
    public ResponseMessage<T> queryForObject(SimpleQuery simpleQuery, Class<T> tClass) throws DataQueryException {
        try {
            LinkedHashMap<String, Object> map =  dataQueryInvoker.queryForObject(simpleQuery);
            if (map == null) {
                return new ResponseMessage<T>();
            }
            T t = convertValueObject(tClass, map);
            return new ResponseMessage<T>().success(t);
        } catch (Exception e) {
            if (e instanceof DataQueryException) {
                throw e;
            }
            throw new DataQueryException(DataQueryErrorEnum.EXECUTING_ERROR, e);
        }
    }

    @Override
    public ResponseMessage<Page<T>> queryForPage(PageQuery pageQuery, Class<T> tClass) throws DataQueryException {
        try {
            Page<LinkedHashMap<String, Object>> page =  dataQueryInvoker.queryForPage(pageQuery);
            List<T> tList = convertValueList(tClass, page.getTList());
            Page<T> tPage = new Page<T>(page.getTotal(), page.getPageNo(), page.getPageSize(), tList);
            return new ResponseMessage<Page<T>>().success(tPage);
        } catch (Exception e) {
            if (e instanceof DataQueryException) {
                throw e;
            }
            throw new DataQueryException(DataQueryErrorEnum.EXECUTING_ERROR, e);
        }
    }

    @Override
    public ResponseMessage<Long> queryForCount(SimpleQuery simpleQuery) throws DataQueryException {
        try {
            Long count = dataQueryInvoker.queryCount(simpleQuery);
            return new ResponseMessage<Long>().success(count);
        } catch (Exception e) {
            if (e instanceof DataQueryException) {
                throw e;
            }
            throw new DataQueryException(DataQueryErrorEnum.EXECUTING_ERROR, e);
        }
    }

    public T convertValueObject(Class<T> tClass, LinkedHashMap<String, Object> map) {
        try {
            return OBJECT_MAPPER.convertValue(map, tClass);
        } catch (Exception e) {
            throw new DataQueryException(DataQueryErrorEnum.DESERIALIZATION_ERROR, e);
        }
    }

    public List<T> convertValueList(Class<T> tClass, List<LinkedHashMap<String, Object>> mapList) {
        List<T> tList = new ArrayList<>();
        mapList.forEach(map-> {
            tList.add(convertValueObject(tClass, map));
        });
        return tList;
    }
}
