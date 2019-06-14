package com.yjq.data.service.common;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-16
 */
public class ParamMappingException extends RuntimeException {

    private String message;

    public ParamMappingException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
