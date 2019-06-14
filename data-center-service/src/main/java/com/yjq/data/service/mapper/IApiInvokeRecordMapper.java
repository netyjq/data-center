package com.yjq.data.service.mapper;

import com.yjq.data.service.model.ApiInvokeRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-06
 */
@Mapper
public interface IApiInvokeRecordMapper {

    /**
     * 新增流水记录
     * @param apiInvokeRecord ApiInvokeRecord
     */
    void insertOne(ApiInvokeRecord apiInvokeRecord);

    /**
     * 查询流水
     * @param filters 过滤条件
     * @return List<ApiInvokeRecord>
     */
    List<ApiInvokeRecord> selectList(Map<String, Object> filters);

    /**
     * 单个更新
     * @param apiInvokeRecord
     */
    void updateOne(ApiInvokeRecord apiInvokeRecord);

}
