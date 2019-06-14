package com.yjq.data.service.aspect;

import com.alibaba.fastjson.JSON;
import com.yjq.data.client.api.core.DataStatisticsErrorEnum;
import com.yjq.data.client.api.core.ResponseMessage;
import com.yjq.data.client.api.query.PageQuery;
import com.yjq.data.client.api.query.SimpleQuery;
import com.yjq.data.service.common.*;
import com.yjq.data.service.mapper.IApiInvokeRecordMapper;
import com.yjq.data.service.mapper.ISqlTemplateMapper;
import com.yjq.data.service.model.ApiInvokeRecord;
import com.yjq.data.service.model.SqlTemplate;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import freemarker.template.TemplateException;
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
    public ResponseMessage around(ProceedingJoinPoint joinPoint) {
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
                return new ResponseMessage().error(DataStatisticsErrorEnum.REQUEST_PARAMETER_INVALID.getDesc());
            }
            sqlTemplate = sqlTemplateMapper.selectOne(pageQuery.getSqlId(), pageQuery.getAppId());
            // 模板非空校验
            if (sqlTemplate == null) {
                return new ResponseMessage().error(DataStatisticsErrorEnum.SQL_TEMPLATE_NOT_FOUND.getDesc());
            }
            requestParams = pageQuery.getFilters();
        } else if (requestQuery instanceof SimpleQuery) {
            SimpleQuery simpleQuery = (SimpleQuery) requestQuery;
            if (simpleQuery.invalid()) {
                return new ResponseMessage().error(DataStatisticsErrorEnum.REQUEST_PARAMETER_INVALID.getDesc());
            }
            sqlTemplate = sqlTemplateMapper.selectOne(simpleQuery.getSqlId(), simpleQuery.getAppId());
            // 模板非空校验
            if (sqlTemplate == null) {
                return new ResponseMessage().error(DataStatisticsErrorEnum.SQL_TEMPLATE_NOT_FOUND.getDesc());
            }
            if (sqlTemplate.getPaging() == 0) {
                return new ResponseMessage<LinkedHashMap>().error(DataStatisticsErrorEnum.SHOULD_BE_PAGING.getDesc());
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
        ResponseMessage response = null;
        try {
            response = (ResponseMessage) joinPoint.proceed();

            // 出参映射
            parseResultMapping(sqlTemplate, response);

            apiInvokeRecord.setResponseTime(new Date());
            apiInvokeRecord.setCost(System.currentTimeMillis() - startTime);
            apiInvokeRecord.setSuccess(SuccessStatusEnum.SUCCEED.getCode());
            logger.info(">>> sql执行完毕. 状态: 成功, appId: {}, appName: {}, 分页: {}, 耗时: {}ms", apiInvokeRecord.getAppId(),
                    apiInvokeRecord.getAppName(), paging, apiInvokeRecord.getCost());

            // 结果集映射
            response.getObject();

        } catch (Throwable throwable) {
            apiInvokeRecord.setResponseTime(new Date());
            apiInvokeRecord.setCost(System.currentTimeMillis() - startTime);
            apiInvokeRecord.setSuccess(SuccessStatusEnum.FAILED.getCode());
            apiInvokeRecord.setErrorDetail(throwable.getMessage());
            response = new ResponseMessage().error(DataStatisticsErrorEnum.EXECUTING_ERROR.getDesc());
            if (throwable instanceof TemplateException) {
                response = new ResponseMessage<LinkedHashMap>().error(DataStatisticsErrorEnum.TEMPLATE_RENDER_ERROR.getDesc() + throwable.getMessage());
            } else if (throwable instanceof ParamMappingException) {
                response = new ResponseMessage().error(throwable.getMessage());
            } else if (throwable instanceof SqlException) {
                response = new ResponseMessage().error(throwable.getMessage());
            }
            logger.info(">>> sql执行完毕. 状态: 异常, appId: {}, appName: {}, 分页: {}, 耗时: {}ms", apiInvokeRecord.getAppId(),
                    apiInvokeRecord.getAppName(), paging, apiInvokeRecord.getCost(), throwable);
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
     * @param response
     */
    private void parseResultMapping(SqlTemplate sqlTemplate, ResponseMessage response) {
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
            Object object = response.getObject();
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
            } else if (object instanceof PageInfo) {
                PageInfo<LinkedHashMap> pageInfo = (PageInfo<LinkedHashMap>) object;
                List<LinkedHashMap> dataList = pageInfo.getList();
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
