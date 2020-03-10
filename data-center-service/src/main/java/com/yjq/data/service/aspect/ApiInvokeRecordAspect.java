package com.yjq.data.service.aspect;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.yjq.data.client.api.core.DataQueryErrorEnum;
import com.yjq.data.client.api.core.DataQueryException;
import com.yjq.data.client.api.core.Page;
import com.yjq.data.client.api.core.ResponseMessage;
import com.yjq.data.client.api.query.PageQuery;
import com.yjq.data.client.api.query.SimpleQuery;
import com.yjq.data.service.common.PaginationEnum;
import com.yjq.data.service.common.ParamMappingException;
import com.yjq.data.service.common.ProgressEnum;
import com.yjq.data.service.common.SuccessStatusEnum;
import com.yjq.data.service.mapper.IApiInvokeRecordMapper;
import com.yjq.data.service.mapper.ISqlTemplateMapper;
import com.yjq.data.service.model.ApiInvokeRecord;
import com.yjq.data.service.model.SqlTemplate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 通过aop记录请求流水以及前置参数校验
 * @date 2019/5/6
 * @author netyjq@gmail.com
 */
@Aspect
@Component
public class ApiInvokeRecordAspect {

    @Autowired
    private IApiInvokeRecordMapper apiInvokeRecordMapper;

    @Autowired
    private ISqlTemplateMapper sqlTemplateMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Around("execution(* com.yjq.data.service.api.impl.DataQueryInvokerImpl.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) {
        Date date = new Date();
        long startTime = System.currentTimeMillis();

        // 参数校验
        Object [] args = joinPoint.getArgs();
        Object requestQuery = args[0];
        SqlTemplate sqlTemplate = null;
        Map<String, Object> requestParams = null;
        if (requestQuery instanceof PageQuery) {
            PageQuery pageQuery = (PageQuery) requestQuery;
            if (pageQuery.invalid()) {
                return new ResponseMessage().error(DataQueryErrorEnum.REQUEST_PARAMETER_INVALID.getDesc());
            }
            sqlTemplate = sqlTemplateMapper.selectOne(pageQuery.getSqlId(), pageQuery.getAppId());
            // 模板非空校验
            if (sqlTemplate == null) {
                return new ResponseMessage().error(DataQueryErrorEnum.SQL_TEMPLATE_NOT_FOUND.getDesc());
            }
            requestParams = pageQuery.getFilters();
        } else if (requestQuery instanceof SimpleQuery) {
            SimpleQuery simpleQuery = (SimpleQuery) requestQuery;
            if (simpleQuery.invalid()) {
                return new ResponseMessage().error(DataQueryErrorEnum.REQUEST_PARAMETER_INVALID.getDesc());
            }
            sqlTemplate = sqlTemplateMapper.selectOne(simpleQuery.getSqlId(), simpleQuery.getAppId());
            // 模板非空校验
            if (sqlTemplate == null) {
                return new ResponseMessage().error(DataQueryErrorEnum.SQL_TEMPLATE_NOT_FOUND.getDesc());
            }
            if (sqlTemplate.getPaging() == 0) {
                return new ResponseMessage<LinkedHashMap>().error(DataQueryErrorEnum.SHOULD_BE_PAGING.getDesc());
            }
            requestParams = simpleQuery.getFilters();
        }
        ApiInvokeRecord apiInvokeRecord = new ApiInvokeRecord();
        apiInvokeRecord.setAppId(sqlTemplate.getAppId());
        apiInvokeRecord.setAppName(sqlTemplate.getAppName());
        apiInvokeRecord.setProcessed(ProgressEnum.UN_PROCESSED.getCode());
        apiInvokeRecord.setRequestTime(date);
        apiInvokeRecord.setSqltext(sqlTemplate.getSqltext());
        apiInvokeRecord.setSqlId(sqlTemplate.getId());
        if (requestParams != null) {
            apiInvokeRecord.setRequestParam(JSON.toJSONString(requestParams));
        }
        String paging = PaginationEnum.getByCode(sqlTemplate.getPaging()).name();
        Object response = null;
        try {
            response = joinPoint.proceed();

            // 出参映射
            parseResultMapping(sqlTemplate, response);

            apiInvokeRecord.setResponseTime(new Date());
            apiInvokeRecord.setCost(System.currentTimeMillis() - startTime);
            apiInvokeRecord.setSuccess(SuccessStatusEnum.SUCCEED.getCode());
            logger.info(">>> sql执行完毕. 状态: 成功, appId: {}, appName: {}, 分页: {}, 耗时: {}ms", apiInvokeRecord.getAppId(),
                    apiInvokeRecord.getAppName(), paging, apiInvokeRecord.getCost());
        } catch (Throwable e) {
            apiInvokeRecord.setResponseTime(new Date());
            apiInvokeRecord.setCost(System.currentTimeMillis() - startTime);
            apiInvokeRecord.setSuccess(SuccessStatusEnum.FAILED.getCode());
            apiInvokeRecord.setErrorDetail(e.getMessage());
            logger.info(">>> sql执行完毕. 状态: 异常, appId: {}, appName: {}, 分页: {}, 耗时: {}ms", apiInvokeRecord.getAppId(),
                    apiInvokeRecord.getAppName(), paging, apiInvokeRecord.getCost(), e);

            throw new DataQueryException(DataQueryErrorEnum.EXECUTING_ERROR, e.getMessage());
        }
        try {
            apiInvokeRecordMapper.insertOne(apiInvokeRecord);
            logger.info(">>> 记录请求流水成功. record: {}", JSON.toJSONString(apiInvokeRecord));
        } catch (Exception e) {
            logger.info(">>> 记录请求流水异常. record: {}, e", JSON.toJSONString(apiInvokeRecord), e);
        }

        return response;
    }


    /**
     * 表字段映射
     * @param sqlTemplate
     * @param object
     */
    private void parseResultMapping(SqlTemplate sqlTemplate, Object object) {
        String parameterMapping = sqlTemplate.getParameterMapping();
        if (Strings.isNullOrEmpty(parameterMapping)) {
            logger.info(">>> 当前SQL模板没有配置表字段映射. sqlId: {}", sqlTemplate.getId());
            return;
        }
        HashMap<String, String> mapping = null;
        try {
            mapping = JSON.parseObject(parameterMapping, HashMap.class);
        } catch (Exception e) {
            throw new ParamMappingException("表字段映射解析JSON失败: 不是有效的JSON格式. parameter: " + parameterMapping);
        }
        try {
            if (object instanceof List) {
                List<LinkedHashMap> dataList = (List<LinkedHashMap>) object;
                mapping.forEach((oldKey, newKey)-> {
                    dataList.forEach(linkedHashMap -> {
                        Object o = linkedHashMap.remove(oldKey);
                        if (o != null) {
                            linkedHashMap.put(newKey, o);
                        }
                    });
                });
            } else if (object instanceof LinkedHashMap) {
                LinkedHashMap linkedHashMap = (LinkedHashMap) object;
                mapping.forEach((oldKey, newKey)-> {
                    Object o = linkedHashMap.remove(oldKey);
                    if (o != null) {
                        linkedHashMap.put(newKey, o);
                    }
                });
            } else if (object instanceof Page) {
                Page<LinkedHashMap> page = (Page<LinkedHashMap>) object;
                List<LinkedHashMap> dataList = page.getTList();
                if (dataList == null || dataList.isEmpty()) {
                    return;
                }
                mapping.forEach((oldKey, newKey)-> {
                    dataList.forEach(linkedHashMap -> {
                        Object o = linkedHashMap.remove(oldKey);
                        if (o != null) {
                            linkedHashMap.put(newKey, o);
                        }
                    });
                });
            }
        } catch (Exception e) {
            throw new ParamMappingException("表字段映射解析发生异常, parameter: " + parameterMapping);
        }
    }

}
