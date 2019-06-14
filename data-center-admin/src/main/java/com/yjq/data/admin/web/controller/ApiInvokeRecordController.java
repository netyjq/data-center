package com.yjq.data.admin.web.controller;

import com.yjq.data.admin.model.domain.ApiInvokeRecord;
import com.yjq.data.admin.model.dto.request.ApiInvokeRecordPageRequestDTO;
import com.yjq.data.admin.model.dto.response.LayuiResponseDTO;
import com.yjq.data.admin.service.ApiInvokeRecordService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-13
 */
@RequestMapping("/api-invoke-record")
@RestController
public class ApiInvokeRecordController {

    @Autowired
    private ApiInvokeRecordService apiInvokeRecordService;

    @RequestMapping("/page")
    public LayuiResponseDTO page(@Validated ApiInvokeRecordPageRequestDTO recordPageRequestDTO, BindingResult result) {
        PageInfo<ApiInvokeRecord> recordPageInfo = apiInvokeRecordService.page(recordPageRequestDTO);
        return new LayuiResponseDTO(recordPageInfo.getList(), Integer.valueOf(recordPageInfo.getTotal()+""));
    }

}
