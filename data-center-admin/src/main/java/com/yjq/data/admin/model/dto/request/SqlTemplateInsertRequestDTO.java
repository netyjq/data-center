package com.yjq.data.admin.model.dto.request;

import com.alibaba.fastjson.JSON;
import com.yjq.data.admin.common.FreemarkerConstant;
import com.yjq.data.admin.common.exception.ServiceException;
import com.yjq.data.admin.model.dto.AbstractDTO;
import com.google.common.base.Strings;
import freemarker.template.Template;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.StringReader;
import java.util.UUID;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-28
 */
public class SqlTemplateInsertRequestDTO extends AbstractDTO {

    /**
     * sql模板
     */
    @NotEmpty
    private String sqltext;

    /**
     * 描述
     */
    @NotEmpty
    @Length(max = 20)
    private String description;

    /**
     * 应用id
     */
    @NotNull
    @Range(min = 1)
    private Integer appId;

    /**
     * 出参映射
     */
    private String parameterMapping;

    /**
     * 是否分页
     */
    @NotNull
    @Range(min = -1, max = 0)
    private Integer paging;

    @Override
    public boolean validate() {
        // 校验sql模板是否正确
        if (!(sqltext.contains("select") || sqltext.contains("SELECT"))) {
            throw new ServiceException("SQL模板编写错误，非SQL SELECT语句");
        }
        if (!(sqltext.contains("from") || sqltext.contains("FROM"))) {
            throw new ServiceException("SQL模板编写错误，非SQL SELECT语句");
        }
        if (!Strings.isNullOrEmpty(parameterMapping)) {
            if (!parameterMapping.contains(":")) {
                throw new ServiceException("表字段映射格式输入错误，无法解析");
            }
            try {
                JSON.parseObject(parameterMapping);
            } catch (Exception e) {
                throw new ServiceException("表字段映射格式输入错误，无法解析");
            }
        }
        try {
            Template template = new Template(UUID.randomUUID().toString(), new StringReader(sqltext),
                    FreemarkerConstant.myFreemarkerConfig);
        } catch (IOException e) {
            throw new ServiceException("SQL模板编写错误，无法解析");
        }
        return true;
    }

    public String getSqltext() {
        return sqltext;
    }

    public void setSqltext(String sqltext) {
        this.sqltext = sqltext;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getParameterMapping() {
        return parameterMapping;
    }

    public void setParameterMapping(String parameterMapping) {
        this.parameterMapping = parameterMapping;
    }

    public Integer getPaging() {
        return paging;
    }

    public void setPaging(Integer paging) {
        this.paging = paging;
    }
}
