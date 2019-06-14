package com.yjq.data.admin.web.controller;

import com.yjq.data.client.api.core.DataQueryInvoker;
import com.yjq.data.client.api.core.DataStatisticsErrorEnum;
import com.yjq.data.client.api.core.ResponseMessage;
import com.yjq.data.client.api.query.PageQuery;
import com.yjq.data.client.api.query.SimpleQuery;
import com.github.pagehelper.PageInfo;
import freemarker.template.TemplateException;
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
    private DataQueryInvoker dataQueryInvoker;

    @RequestMapping("/list")
    public ResponseMessage list(@RequestBody SimpleQuery simpleQuery) {
        if (simpleQuery == null || simpleQuery.invalid()) {
            return new ResponseMessage().error(DataStatisticsErrorEnum.REQUEST_PARAMETER_INVALID.getDesc());
        }
        ResponseMessage<List<LinkedHashMap>> response = null;
        try {
            response = dataQueryInvoker.queryForList(simpleQuery);
        } catch (Exception e) {
            if (e instanceof TemplateException) {
                return new ResponseMessage().error(DataStatisticsErrorEnum.TEMPLATE_RENDER_ERROR.getDesc());
            }
            return new ResponseMessage().error(DataStatisticsErrorEnum.EXECUTING_ERROR.getDesc());
        }
        return response;
    }

    @RequestMapping("/page")
    public ResponseMessage page(@RequestBody PageQuery pageQuery) {
        if (pageQuery == null || pageQuery.invalid()) {
            return new ResponseMessage().error(DataStatisticsErrorEnum.REQUEST_PARAMETER_INVALID.getDesc());
        }
        ResponseMessage<PageInfo<LinkedHashMap>> response = null;
        try {
            response = dataQueryInvoker.queryForPage(pageQuery);
        } catch (Exception e) {
            if (e instanceof TemplateException) {
                return new ResponseMessage().error(DataStatisticsErrorEnum.TEMPLATE_RENDER_ERROR.getDesc());
            }
            return new ResponseMessage().error(DataStatisticsErrorEnum.EXECUTING_ERROR.getDesc());
        }
        return response;
    }

    @RequestMapping("/object")
    public ResponseMessage object(@RequestBody SimpleQuery simpleQuery) {
        if (simpleQuery == null || simpleQuery.invalid()) {
            return new ResponseMessage().error(DataStatisticsErrorEnum.REQUEST_PARAMETER_INVALID.getDesc());
        }
        ResponseMessage<LinkedHashMap> response = null;
        try {
            response = dataQueryInvoker.queryForObject(simpleQuery);
        } catch (Exception e) {
            if (e instanceof TemplateException) {
                return new ResponseMessage().error(DataStatisticsErrorEnum.TEMPLATE_RENDER_ERROR.getDesc());
            }
            return new ResponseMessage().error(DataStatisticsErrorEnum.EXECUTING_ERROR.getDesc());
        }
        return response;
    }

}
