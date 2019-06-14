package com.yjq.data.admin.common;

/**
 * sql操作类型
 * @author netyjq@gmail.com
 * @date 2019-05-06
 */
public enum SqlOperationTypeEnum {

    /**
     * 新增
     */
    INSERT(1),

    /**
     * 更新
     */
    UPDATE(2),

    /**
     * 删除
     */
    DELETE(3);

    private int type;

    SqlOperationTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
