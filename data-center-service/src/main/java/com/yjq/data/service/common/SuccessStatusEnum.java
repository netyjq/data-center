package com.yjq.data.service.common;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-07
 */
public enum SuccessStatusEnum {

    /**
     * 成功
     */
    SUCCEED(0),

    /**
     * 失败
     */
    FAILED(-1);

    private int code;

    SuccessStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
