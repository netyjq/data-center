package com.yjq.data.admin.web.controller;

import com.yjq.data.admin.common.validation.ValidationMarker;
import com.yjq.data.admin.model.domain.AlarmHistory;
import com.yjq.data.admin.model.dto.request.AlarmHistoryRequestDTO;
import com.yjq.data.admin.model.dto.response.LayuiResponseDTO;
import com.yjq.data.admin.service.AlarmHistoryService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-06
 */
@RestController
@RequestMapping("/alarm-history")
public class AlarmHistoryController {

    @Autowired
    private AlarmHistoryService alarmHistoryService;

    @RequestMapping("/page")
    public LayuiResponseDTO page(@Validated(ValidationMarker.SelectGroup.class) AlarmHistoryRequestDTO requestDTO, BindingResult result) {
        PageInfo<AlarmHistory> pageInfo = alarmHistoryService.listPage(requestDTO);
        LayuiResponseDTO responseDTO = new LayuiResponseDTO(pageInfo.getList(), Integer.valueOf(pageInfo.getTotal()+""));
        return responseDTO;
    }

}
