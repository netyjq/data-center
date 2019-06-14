package com.yjq.data.admin.service;

import com.yjq.data.admin.model.domain.ApiInvokeRecord;
import com.yjq.data.admin.model.dto.request.ApiInvokeRecordPageRequestDTO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-13
 */
@Service
public class ApiInvokeRecordService extends AbstractBaseService<ApiInvokeRecord, Integer> {

    public PageInfo<ApiInvokeRecord> page(ApiInvokeRecordPageRequestDTO recordPageRequestDTO) {
        return this.listPage(recordPageRequestDTO);
    }

}
