package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.entity.universal.StringParams;
import com.foxconn.sw.data.dto.request.schedule.CreateScheduleParams;
import com.foxconn.sw.data.dto.request.schedule.MyScheduleParams;
import com.foxconn.sw.data.dto.request.schedule.ScheduleListParams;
import com.foxconn.sw.data.dto.response.schedule.ScheduleListVo;
import com.foxconn.sw.data.dto.response.schedule.TeamScheduleListVo;
import com.foxconn.sw.service.processor.schedule.AddCommonDestinationProcessor;
import com.foxconn.sw.service.processor.schedule.CreateScheduleProcessor;
import com.foxconn.sw.service.processor.schedule.MyScheduleProcessor;
import com.foxconn.sw.service.processor.schedule.TeamScheduleProcessor;
import com.foxconn.sw.service.processor.system.dic.QueryConfigDicProcessor;
import com.foxconn.sw.service.utils.ExcelScheduleUtils;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
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
    AddCommonDestinationProcessor addCommonDestinationProcessor;
    @Autowired
    HttpServletResponse response;

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
        List<TeamScheduleListVo> list = teamScheduleProcessor.teamSchedule(request.getData());
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

    @Operation(summary = "導出行程信息", tags = "schedule")
    @ApiResponse(responseCode = "0", description = "成功码")
    @CrossOrigin(exposedHeaders = {"Content-Disposition"})
    @PostMapping("/export")
    public ResponseEntity export(@Valid @RequestBody Request<ScheduleListParams> request) throws IOException {
        // 设置响应头
        response.setContentType("application/vnd.ms-excel");
        String fileName = String.format("schedule %s-%s.xlsx", request.getData().getStartDate(), request.getData().getEndDate());
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        List<TeamScheduleListVo> vos = teamScheduleProcessor.teamSchedule(request.getData());
        if (CollectionUtils.isEmpty(vos)) {
            return ResponseEntity.ok().body(null);
        }
        // 使用Apache POI生成Excel文件
        Workbook workbook = ExcelScheduleUtils.generateExcel(vos);

        // 将Excel文件写入响应输出流
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();

        workbook.close();
        return ResponseEntity.ok().body(null);
    }
}
