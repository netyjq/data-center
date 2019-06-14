package com.yjq.data.admin.web.controller;

import com.yjq.data.admin.model.domain.SqlOperationRecord;
import com.yjq.data.admin.model.dto.request.SqlOperationRecordPageRequestDTO;
import com.yjq.data.admin.model.dto.response.LayuiResponseDTO;
import com.yjq.data.admin.service.SqlOperationRecordService;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-13
 */
@RequestMapping("/sql-operation-record")
@RestController
public class SqlOperationRecordController {

    @Autowired
    private SqlOperationRecordService sqlOperationRecordService;

    @RequestMapping("/page")
    public LayuiResponseDTO page(@Validated SqlOperationRecordPageRequestDTO recordPageRequestDTO, BindingResult result) {
        PageInfo<SqlOperationRecord> recordPageInfo = sqlOperationRecordService.page(recordPageRequestDTO);
        recordPageInfo.getList().forEach(sqlOperationRecord -> {
            if (!Strings.isNullOrEmpty(sqlOperationRecord.getSqltextBefore())) {
                sqlOperationRecord.setSqltextBefore(sqlOperationRecord.getSqltextBefore().replace("<", "&lt;").replace(">", "&gt;"));
            }
            sqlOperationRecord.setSqltextAfter(sqlOperationRecord.getSqltextAfter().replace("<", "&lt;").replace(">", "&gt;"));
        });
        return new LayuiResponseDTO(recordPageInfo.getList(), Integer.valueOf(recordPageInfo.getTotal()+""));
    }

}


