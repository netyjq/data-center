package com.yjq.data.client.api.core;

import java.io.Serializable;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-28
 */
public class ResponseMessage<E> implements Serializable {

    private E object;

    private String errorMessage;

    private Boolean success;

    public Object getObject() {
        return object;
    }

    public ResponseMessage(E object, String errorMessage, Boolean success) {
        this.object = object;
        this.errorMessage = errorMessage;
        this.success = success;
    }

    public ResponseMessage() {
    }

    public ResponseMessage<E> success(E object) {
        this.success = true;
        this.object = object;
        return this;
    }

    public ResponseMessage<E> error(String errorMessage) {
        this.success = false;
        this.errorMessage = errorMessage;
        return this;
    }

    public void setObject(E object) {
        this.object = object;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
