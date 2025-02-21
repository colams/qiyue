package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.request.schedule.CreateScheduleParams;
import com.foxconn.sw.data.dto.request.schedule.ScheduleListParams;
import com.foxconn.sw.data.dto.response.schedule.ScheduleListVo;
import com.foxconn.sw.service.processor.schedule.CreateScheduleProcessor;
import com.foxconn.sw.service.processor.schedule.ListScheduleProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
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
@RequestMapping("api/schedule")
public class ScheduleController {

    @Autowired
    CreateScheduleProcessor createScheduleProcessor;
    @Autowired
    ListScheduleProcessor listScheduleProcessor;

    @Operation(summary = "保存行程信息", tags = "schedule")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/createSchedule")
    public ResponseEntity createSchedule(@Valid @RequestBody Request<CreateScheduleParams> request) {
        Long scheduleId = createScheduleProcessor.createSchedule(request.getData());
        return ResponseEntity.ok(ResponseUtils.success(scheduleId, request.getTraceId()));
    }

    @Operation(summary = "行程列表", tags = "schedule")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public ResponseEntity list(@Valid @RequestBody Request<ScheduleListParams> request) {
        List<ScheduleListVo> list = listScheduleProcessor.list(request.getData());
        return ResponseEntity.ok(ResponseUtils.success(list, request.getTraceId()));
    }
}
