package com.yjq.data.client.api.core;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-06
 */
public enum DataStatisticsErrorEnum {

    /**
     * 参数校验不通过
     */
    REQUEST_PARAMETER_INVALID("参数校验不通过"),

    /**
     * 无法查询到SQL模板
     */
    SQL_TEMPLATE_NOT_FOUND("无法查询到SQL模板"),

    /**
     * 调用错误，接口需要分页
     */
    SHOULD_BE_PAGING("方法调用错误，接口需要分页"),

    /**
     * 调用发生异常
     */
    EXECUTING_ERROR("服务出错了，请联系服务提供者"),


    /**
     * sql模板解析异常
     */
    TEMPLATE_RENDER_ERROR("SQL模板解析异常: 请仔细检查你配置的SQL模板. "),

    /**
     * SQL执行出错
     */
    SQL_EXECUTE_ERROR("SQL执行异常: 可能是sql编写错误或者SQL模板解析后的SQL语法出现错误，请仔细核对入参和sql模板. ");



    private String desc;

    DataStatisticsErrorEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
