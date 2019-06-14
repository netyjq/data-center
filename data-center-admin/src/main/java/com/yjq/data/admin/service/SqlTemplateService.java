package com.yjq.data.admin.service;

import com.yjq.data.admin.common.GlobalStatusEnum;
import com.yjq.data.admin.common.SqlOperationTypeEnum;
import com.yjq.data.admin.common.exception.ServiceException;
import com.yjq.data.admin.mapper.ISqlTemplateMapper;
import com.yjq.data.admin.model.domain.AppInfo;
import com.yjq.data.admin.model.domain.SqlOperationRecord;
import com.yjq.data.admin.model.domain.SqlTemplate;
import com.yjq.data.admin.model.domain.UserInfo;
import com.yjq.data.admin.model.dto.request.SqlTemplateInsertRequestDTO;
import com.yjq.data.admin.model.dto.request.SqlTemplateRequestDTO;
import com.yjq.data.admin.model.dto.request.SqlTemplateUpdateRequestDTO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-28
 */
@Service
public class SqlTemplateService extends AbstractBaseService<SqlTemplate, String> {

    @Autowired
    private ISqlTemplateMapper sqlTemplateMapper;

    @Autowired
    private AppInfoService appInfoService;

    @Autowired
    private SqlOperationRecordService sqlOperationRecordService;

    /**
     * 新增sqlTemplate
     * @param insertRequestDTO dto
     * @param userInfo the login user
     */
    @Transactional(rollbackFor = Exception.class)
    public void insertSqlTemplate(SqlTemplateInsertRequestDTO insertRequestDTO, UserInfo userInfo) {
        if (!insertRequestDTO.validate()) {
            throw new ServiceException("sql模板编写有误请核对");
        }
        Integer appId = insertRequestDTO.getAppId();
        AppInfo appInfo = appInfoService.get(appId);
        if (appInfo == null) {
            throw new ServiceException("无法新增模板,无法查询到应用.appId: " + appId);
        }
        SqlTemplate sqlTemplate = new SqlTemplate(insertRequestDTO.getSqltext(), insertRequestDTO.getDescription(),
                appId, appInfo.getName(), GlobalStatusEnum.AVAILABLE.getStatus(), insertRequestDTO.getPaging(),
                insertRequestDTO.getParameterMapping(), userInfo.getName(), userInfo.getId());
        super.insert(sqlTemplate);
        // 添加操作记录
        insertOperationRecord(sqlTemplate.getId(), null, sqlTemplate.getSqltext(), null,
                sqlTemplate.getPaging(), null, sqlTemplate.getParameterMapping(),
                SqlOperationTypeEnum.INSERT, userInfo);
    }

    /**
     * 更新sqlTemplate
     * @param updateRequestDTO dto
     * @param userInfo the login user
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateSqlTemplate(SqlTemplateUpdateRequestDTO updateRequestDTO, UserInfo userInfo) {
        if (!updateRequestDTO.validate()) {
            throw new ServiceException("sql模板编写有误请核对");
        }
        String sqlTemplateId = updateRequestDTO.getId();
        SqlTemplate sqlTemplate = sqlTemplateMapper.selectOne(sqlTemplateId);
        if (sqlTemplate == null) {
            throw new ServiceException("无法查询到sql模板,templateId: " + sqlTemplateId);
        }
        String sqltextBefore = sqlTemplate.getSqltext();
        Integer pageBefore = sqlTemplate.getPaging();
        String parameterMappingBefore = sqlTemplate.getParameterMapping();

        sqlTemplate.setParameterMapping(updateRequestDTO.getParameterMapping());
        sqlTemplate.setPaging(updateRequestDTO.getPaging());
        sqlTemplate.setDescription(updateRequestDTO.getDescription());
        sqlTemplate.setSqltext(updateRequestDTO.getSqltext());
        sqlTemplate.setUpdateName(userInfo.getName());
        sqlTemplate.setUpdateId(userInfo.getId());
        sqlTemplate.setUpdateTime(new Date());
        super.update(sqlTemplate);
        // 添加操作记录
        insertOperationRecord(sqlTemplateId, sqltextBefore, updateRequestDTO.getSqltext(), pageBefore,
                updateRequestDTO.getPaging(), parameterMappingBefore, updateRequestDTO.getParameterMapping(),
                SqlOperationTypeEnum.UPDATE, userInfo);
    }

    /**
     * 操作记录入库
     * @param sqlTemplateId sql模板id
     * @param sqltextBefore 修改前
     * @param sqltextAfter 修改后
     * @param operationType 操作类型
     * @param pageBefore
     * @param pageAfter
     * @param parameterMappingAfter
     * @param paramterMappingBefore
     * @param userInfo 操作用户
     */
    public void insertOperationRecord(String sqlTemplateId, String sqltextBefore, String sqltextAfter, Integer pageBefore,
                                      Integer pageAfter, String paramterMappingBefore, String parameterMappingAfter,
                                      SqlOperationTypeEnum operationType, UserInfo userInfo) {
        // 操作记录入库
        SqlOperationRecord operationRecord = new SqlOperationRecord();
        operationRecord.setCreateTime(new Date());
        operationRecord.setOperatorId(userInfo.getId());
        operationRecord.setOperatorName(userInfo.getName());
        operationRecord.setSqlId(sqlTemplateId);
        operationRecord.setSqltextAfter(sqltextAfter);
        operationRecord.setSqltextBefore(sqltextBefore);
        operationRecord.setType(operationType.getType());
        operationRecord.setPageBefore(pageBefore);
        operationRecord.setPageAfter(pageAfter);
        operationRecord.setParameterMappingBefore(paramterMappingBefore);
        operationRecord.setParameterMappingAfter(parameterMappingAfter);
        sqlOperationRecordService.insert(operationRecord);
    }


    /**
     * 按条件分页查询
     * @param requestDTO SqlTemplateRequestDTO
     * @return PageInfo of SqlTemplate
     */
    public PageInfo<SqlTemplate> listPage(SqlTemplateRequestDTO requestDTO) {
        return super.listPage(requestDTO);
    }

}
