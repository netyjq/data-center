package com.yjq.data.admin.model.domain;

import com.yjq.data.admin.model.AbstractModel;
import lombok.Data;

import java.util.Date;

/**
 * 接口调用记录
 * 告警触发的规则就是定时轮训调用记录，判断是否符合告警的配置
 * 轮询完成后会设置标志位字段processed为-1，并记录处理时间
 *
 * @author netyjq@gmail.com
 * @date 2019-05-06
 */
@Data
public class ApiInvokeRecord extends AbstractModel<Integer> {

    private Integer id;

    /**
     * 应用id
     */
    private Integer appId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * sql模板id
     */
    private String sqlId;

    /**
     * sql模板
     */
    private String sqltext;

    /**
     * 请求入参
     */
    private String requestParam;

    /**
     * 接收到请求的时的系统时间
     */
    private Date requestTime;

    /**
     * 请求响应时系统时间
     */
    private Date responseTime;

    /**
     * 消耗的时间 毫秒
     */
    private Integer cost;

    /**
     * 是否调用成功 0 成功 -1 失败
     */
    private Integer success;

    /**
     * 是否已经轮询处理过了 0未处理 -1已经处理
     */
    private Integer processed;

    /**
     * 轮询标记的时间
     */
    private Date processedTime;

    /**
     * 错误详情 执行出错时记录
     */
    private String errorDetail;

}
