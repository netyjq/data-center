package com.yjq.data.admin.model.dto.response;

import java.util.List;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-08
 */
public class LayuiResponseDTO {

    private Integer code = 0;

    private String msg;

    private Integer count;

    private List data;

    public LayuiResponseDTO() {
    }

    public LayuiResponseDTO(List list) {
        this.data = list;
        this.count = list.size();
    }

    public LayuiResponseDTO(List list, int count) {
        this.data = list;
        this.count = count;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

}
