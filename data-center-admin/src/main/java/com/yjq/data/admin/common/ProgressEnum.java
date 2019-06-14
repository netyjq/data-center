package com.yjq.data.admin.common;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-07
 */
public enum ProgressEnum {

    /**
     * 已处理
     */
    PROCESSED(1),

    /**
     * 未处理
     */
    UN_PROCESSED(-1);

    private int code;

    ProgressEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
