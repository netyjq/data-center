package com.yjq.data.admin.web.controller;

import com.yjq.data.client.api.core.DataQueryErrorEnum;
import com.yjq.data.client.api.core.Page;
import com.yjq.data.client.api.core.ResponseMessage;
import com.yjq.data.client.api.manage.DataQueryManage;
import com.yjq.data.client.api.query.PageQuery;
import com.yjq.data.client.api.query.SimpleQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-07
 */
@RestController
@RequestMapping("/dubbo/test")
public class ApiTestController {

    @Autowired
    private DataQueryManage<LinkedHashMap> dataQueryManageImpl;

    @RequestMapping("/list")
    public ResponseMessage<List<LinkedHashMap>> list(@RequestBody SimpleQuery simpleQuery) {
        return dataQueryManageImpl.queryForList(simpleQuery, LinkedHashMap.class);
    }

    @RequestMapping("/page")
    public ResponseMessage<Page<LinkedHashMap>> page(@RequestBody PageQuery pageQuery) {
        if (pageQuery == null || pageQuery.invalid()) {
            return new ResponseMessage<Page<LinkedHashMap>>().error(DataQueryErrorEnum.REQUEST_PARAMETER_INVALID.getDesc());
        }
        return dataQueryManageImpl.queryForPage(pageQuery, LinkedHashMap.class);
    }

    @RequestMapping("/object")
    public ResponseMessage object(@RequestBody SimpleQuery simpleQuery) {
        if (simpleQuery == null || simpleQuery.invalid()) {
            return new ResponseMessage().error(DataQueryErrorEnum.REQUEST_PARAMETER_INVALID.getDesc());
        }
        return dataQueryManageImpl.queryForObject(simpleQuery, LinkedHashMap.class);
    }

    @RequestMapping("/count")
    public ResponseMessage<Long> count(@RequestBody SimpleQuery simpleQuery) {
        if (simpleQuery == null || simpleQuery.invalid()) {
            return new ResponseMessage<Long>().error(DataQueryErrorEnum.REQUEST_PARAMETER_INVALID.getDesc());
        }
        return dataQueryManageImpl.queryForCount(simpleQuery);
    }

}
