package com.yjq.data.admin.web.controller;

import com.yjq.data.admin.common.validation.ValidationMarker;
import com.yjq.data.admin.model.domain.AlarmConfig;
import com.yjq.data.admin.model.dto.request.AlarmConfigRequestDTO;
import com.yjq.data.admin.model.dto.response.LayuiResponseDTO;
import com.yjq.data.admin.model.dto.response.ResponseDTO;
import com.yjq.data.admin.service.AlarmConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-05
 */
@RestController
@RequestMapping("/alarm-config")
public class AlarmConfigController extends AbstractController {

    @Autowired
    private AlarmConfigService alarmConfigService;

    @RequestMapping("/insert")
    public ResponseDTO insert(@Validated({ValidationMarker.InsertGroup.class}) AlarmConfigRequestDTO insertDTO, BindingResult result) {
        alarmConfigService.insertAlarmConfig(insertDTO, getLoginUser());
        return new ResponseDTO();
    }

    @RequestMapping("/update")
    public ResponseDTO update(@Validated({ValidationMarker.UpdateGroup.class}) AlarmConfigRequestDTO updateDTO, BindingResult result) {
        alarmConfigService.updateAlarmConfig(updateDTO, getLoginUser());
        return new ResponseDTO();
    }

    @RequestMapping("/list")
    public LayuiResponseDTO list(@Validated({ValidationMarker.SelectGroup.class}) AlarmConfigRequestDTO selectDTO, BindingResult result) {
        List<AlarmConfig> list = alarmConfigService.findAlarmConfigListByAppId(selectDTO.getAppId());
        return new LayuiResponseDTO(list);
    }

    @RequestMapping("/delete")
    public ResponseDTO delete(@Validated({ValidationMarker.DeleteGroup.class}) AlarmConfigRequestDTO deleteDTO, BindingResult result) {
        alarmConfigService.deleteAlarmConfig(deleteDTO.getId());
        return new ResponseDTO();
    }

}
