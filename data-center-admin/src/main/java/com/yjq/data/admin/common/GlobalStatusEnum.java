package com.yjq.data.admin.common;

/**
 * 全局状态
 * @author netyjq@gmail.com
 * @date 2019-04-29
 */
public enum GlobalStatusEnum {

    /**
     * 可用正常状态
     */
    AVAILABLE(0),

    /**
     * 不可用状态
     */
    DISABLED(-1);

    private int status;

    GlobalStatusEnum(int status) {
        this.status = status;
    }

    public static GlobalStatusEnum getByStatusCode(int status) {
        for (GlobalStatusEnum globalStatusEnum : GlobalStatusEnum.values()) {
            if (globalStatusEnum.status == status) {
                return globalStatusEnum;
            }
        }
        return null;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
