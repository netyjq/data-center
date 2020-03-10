package com.yjq.data.client.api.core;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yejiaqian
 * @date Created in 2020/3/9 13:59 <br>
 */
@Data
public class Page<T> implements Serializable {

    /**
     * 总数
     */
    private int total;

    /**
     * 当页面码
     */
    private int pageNo;

    /**
     * 每页大小
     */
    private int pageSize;

    /**
     * 数据
     */
    private List<T> tList;

    public Page(int total, int pageNo, int pageSize, List<T> tList) {
        this.total = total;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.tList = tList;
    }
}
