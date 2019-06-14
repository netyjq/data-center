package com.yjq.data.admin.web.controller;

import com.yjq.data.admin.common.BeanUtil;
import com.yjq.data.admin.model.domain.SqlTemplate;
import com.yjq.data.admin.model.dto.request.SqlTemplateInsertRequestDTO;
import com.yjq.data.admin.model.dto.request.SqlTemplateUpdateRequestDTO;
import com.yjq.data.admin.model.dto.response.LayuiResponseDTO;
import com.yjq.data.admin.model.dto.response.ResponseDTO;
import com.yjq.data.admin.service.SqlTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-28
 */
@RestController
@RequestMapping("/sqltemplate")
public class SqlTemplateController extends AbstractController {

    @Autowired
    private SqlTemplateService sqlTemplateService;

    @RequestMapping({"/insert"})
    public ResponseDTO insert(@Validated SqlTemplateInsertRequestDTO insertRequestDTO, BindingResult result) {
        sqlTemplateService.insertSqlTemplate(insertRequestDTO, getLoginUser());
        return new ResponseDTO();
    }

    @RequestMapping("/update")
    public ResponseDTO update(@Validated SqlTemplateUpdateRequestDTO updateRequestDTO, BindingResult result) {
        sqlTemplateService.updateSqlTemplate(updateRequestDTO, getLoginUser());
        return new ResponseDTO();
    }


    @RequestMapping("/list")
    public LayuiResponseDTO list(Integer appId) {
        if (appId == null) {
            return new LayuiResponseDTO(Collections.emptyList());
        }
        Map<String, Object> filters = BeanUtil.getPropertyMap("appId", appId);
        List<SqlTemplate> list = sqlTemplateService.list(filters);
        list.forEach(sqlTemplate -> {
            sqlTemplate.setSqltext(sqlTemplate.getSqltext().replace("<", "&lt;").replace(">", "&gt;"));
        });
        return new LayuiResponseDTO(list);
    }




}
