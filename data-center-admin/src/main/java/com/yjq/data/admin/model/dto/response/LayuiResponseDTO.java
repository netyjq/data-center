package com.yjq.data.admin.model.dto.response;

import lombok.Data;

import java.util.List;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-08
 */
@Data
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

}
