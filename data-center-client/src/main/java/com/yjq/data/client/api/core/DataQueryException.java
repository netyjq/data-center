package com.yjq.data.client.api.core;

import java.util.Objects;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-06
 */
public class DataQueryException extends RuntimeException {

    private String code;

    private String message;

    public DataQueryException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public DataQueryException(DataQueryErrorEnum errorEnum, String message) {
        this.code = errorEnum.name();
        this.message = errorEnum.getDesc();
        if (Objects.nonNull(message)) {
            this.message = errorEnum.getDesc() + message;
        }
    }

    public DataQueryException(DataQueryErrorEnum errorEnum, Exception e) {
        this.code = errorEnum.name();
        this.message = errorEnum.getDesc();
        if (Objects.nonNull(e) && Objects.nonNull(e.getMessage())) {
            this.message = errorEnum.getDesc() + e.getMessage();
        }
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
