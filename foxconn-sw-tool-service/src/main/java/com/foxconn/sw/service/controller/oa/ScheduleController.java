package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.entity.universal.StringParams;
import com.foxconn.sw.data.dto.request.schedule.CreateScheduleParams;
import com.foxconn.sw.data.dto.request.schedule.MyScheduleParams;
import com.foxconn.sw.data.dto.request.schedule.ScheduleListParams;
import com.foxconn.sw.data.dto.response.schedule.ScheduleListVo;
import com.foxconn.sw.service.processor.acount.SetStationedPlaceProcessor;
import com.foxconn.sw.service.processor.schedule.AddCommonDestinationProcessor;
import com.foxconn.sw.service.processor.schedule.CreateScheduleProcessor;
import com.foxconn.sw.service.processor.schedule.MyScheduleProcessor;
import com.foxconn.sw.service.processor.schedule.TeamScheduleProcessor;
import com.foxconn.sw.service.processor.system.dic.QueryConfigDicProcessor;
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
    MyScheduleProcessor myScheduleProcessor;
    @Autowired
    TeamScheduleProcessor teamScheduleProcessor;
    @Autowired
    QueryConfigDicProcessor queryConfigDicProcessor;
    @Autowired
    SetStationedPlaceProcessor setStationedPlaceProcessor;
    @Autowired
    AddCommonDestinationProcessor addCommonDestinationProcessor;

    @Operation(summary = "保存行程信息", tags = "schedule")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/createSchedule")
    public ResponseEntity createSchedule(@Valid @RequestBody Request<CreateScheduleParams> request) {
        Boolean result = createScheduleProcessor.createSchedule(request.getData());
        return ResponseEntity.ok(ResponseUtils.success(result, request.getTraceId()));
    }

    @Operation(summary = "我的行程列表", tags = "schedule")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/mySchedule")
    public ResponseEntity mySchedule(@Valid @RequestBody Request<MyScheduleParams> request) {
        List<ScheduleListVo> list = myScheduleProcessor.mySchedule(request.getData());
        return ResponseEntity.ok(ResponseUtils.success(list, request.getTraceId()));
    }

    @Operation(summary = "团队行程列表", tags = "schedule")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/teamSchedule")
    public ResponseEntity teamSchedule(@Valid @RequestBody Request<ScheduleListParams> request) {
        List<ScheduleListVo> list = teamScheduleProcessor.teamSchedule(request.getData());
        return ResponseEntity.ok(ResponseUtils.success(list, request.getTraceId()));
    }

    @Operation(summary = "常用地址信息", tags = "schedule")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/commonDestination")
    public ResponseEntity commonDestination(@Valid @RequestBody Request request) {
        String configKey = "schedule.destination";
        List<String> list = queryConfigDicProcessor.getConfigDicValue(configKey, List.class, String.class);
        return ResponseEntity.ok(ResponseUtils.success(list, request.getTraceId()));
    }

    @Operation(summary = "增加常用地址", tags = "schedule")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/addCommonDestination")
    public ResponseEntity addCommonDestination(@Valid @RequestBody Request<StringParams> request) {
        Boolean result = addCommonDestinationProcessor.addCommonDestination(request.getData());
        return ResponseEntity.ok(ResponseUtils.success(result, request.getTraceId()));
    }

    @Operation(summary = "设置为常驻地", tags = "schedule")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/setStationedPlace")
    public ResponseEntity setStationedPlace(@Valid @RequestBody Request<StringParams> request) {
        Boolean result = setStationedPlaceProcessor.setStationedPlace(request.getData());
        return ResponseEntity.ok(ResponseUtils.success(result, request.getTraceId()));
    }
}
