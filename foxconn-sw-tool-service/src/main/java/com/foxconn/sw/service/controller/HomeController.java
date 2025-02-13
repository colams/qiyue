package com.foxconn.sw.service.controller;

import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.home.AgendaVo;
import com.foxconn.sw.data.dto.entity.home.GeneralVo;
import com.foxconn.sw.data.dto.entity.home.TaskScheduleVo;
import com.foxconn.sw.service.processor.GeneralProcessor;
import com.foxconn.sw.service.processor.MonthlyWorkProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    GeneralProcessor generalProcessor;
    @Autowired
    MonthlyWorkProcessor monthlyWorkProcessor;

    @PostMapping("/general")
    @Operation(summary = "工作概览", tags = "home")
    @ApiResponse(responseCode = "0", description = "成功码")
    public ResponseEntity<GeneralVo> general(@Valid @RequestBody Request request) {
        GeneralVo generalVo = generalProcessor.general();
        return ResponseEntity.ok(generalVo);
    }


    @Operation(summary = "我的日程信息", tags = "home")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/schedule")
    public Response<List<AgendaVo>> schedule(@Valid @RequestBody Request request) {
        List<AgendaVo> agendaVoList = Lists.newArrayList();
        return ResponseUtils.success(agendaVoList, request.getTraceId());
    }

    @Operation(summary = "我的日程信息", tags = "home")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/scheduleDetail")
    public Response<List<TaskScheduleVo>> scheduleDetail(@Valid @RequestBody Request request) {
        List<TaskScheduleVo> taskScheduleVos = Lists.newArrayList();
        return ResponseUtils.success(taskScheduleVos, request.getTraceId());
    }


}
