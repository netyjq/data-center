package com.yjq.data.admin.service;

import com.yjq.data.admin.mapper.ISqlOperationRecordMapper;
import com.yjq.data.admin.model.domain.SqlOperationRecord;
import com.yjq.data.admin.model.dto.request.SqlOperationRecordPageRequestDTO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-28
 */
@Service
public class SqlOperationRecordService extends AbstractBaseService<SqlOperationRecord, Integer> {

    @Autowired
    private ISqlOperationRecordMapper sqlOperationRecordMapper;

    /**
     * 根据sqlid分页查询
     * @param recordPageRequestDTO 分页dto
     * @return
     */
    public PageInfo<SqlOperationRecord> page(SqlOperationRecordPageRequestDTO recordPageRequestDTO) {
        return this.listPage(recordPageRequestDTO);
    }

}
