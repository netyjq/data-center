package com.yjq.data.client.api.core;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-06
 */
public class DataStatisticsException extends RuntimeException {

    private String code;

    private String message;

    public DataStatisticsException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public DataStatisticsException(DataStatisticsErrorEnum errorEnum) {
        this.code = errorEnum.name();
        this.message = errorEnum.getDesc();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
