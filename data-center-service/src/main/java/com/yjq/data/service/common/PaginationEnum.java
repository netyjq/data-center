package com.yjq.data.service.common;

/**
 * 分页
 * @author netyjq@gmail.com
 * @date 2019-04-29
 */
public enum PaginationEnum {

    /**
     * 分页
     */
    YES(0),

    /**
     * 不分页
     */
    NO(-1);

    private int code;

    PaginationEnum(int code) {
        this.code = code;
    }

    public static PaginationEnum getByCode(int code) {
        for (PaginationEnum paginationEnum : PaginationEnum.values()) {
            if (paginationEnum.code == code) {
                return paginationEnum;
            }
        }
        return PaginationEnum.YES;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
