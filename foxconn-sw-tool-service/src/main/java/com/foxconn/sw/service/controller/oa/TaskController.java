package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.common.IDParams;
import com.foxconn.sw.data.dto.entity.oa.*;
import com.foxconn.sw.service.processor.oa.*;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 任务管理相关接口
 */
@CrossOrigin
@RestController
@RequestMapping("api/oa/task")
public class TaskController {

    @Autowired
    TaskListProcessor taskListProcessor;
    @Autowired
    TaskDetailProcessor taskDetailProcessor;
    @Autowired
    OverviewProcessor overviewProcessor;
    @Autowired
    CreateTaskProcessor createTaskProcessor;
    @Autowired
    AssignTaskProcessor assignTaskProcessor;
    @Autowired
    UpdateProgressProcessor updateProgressProcessor;
    @Autowired
    StatusUpdateProcessor statusUpdateProcessor;
    @Autowired
    EvaluateProcessor evaluateProcessor;
    @Autowired
    RejectTaskProcessor rejectTaskProcessor;

    @Operation(summary = "任务列表", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response list(@RequestBody Request<PageParams<TaskParams>> request) {
        PageEntity<TaskBriefVo> taskList = taskListProcessor.list(request.getData(), request.getHead());
        return ResponseUtils.success(taskList, request.getTraceId());
    }


    @Operation(summary = "任务详情", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/detail")
    public Response detail(@RequestBody Request<IDParams> request) {
        TaskDetailVo taskList = taskDetailProcessor.detail(request.getData(), request.getHead());
        return ResponseUtils.success(taskList, request.getTraceId());
    }


    @Operation(summary = "任务总览", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/overview")
    public Response overview(@RequestBody Request request) {
        List<TaskOverviewVo> taskList = overviewProcessor.overview(request.getHead());
        return ResponseUtils.success(taskList, request.getTraceId());
    }

    @Operation(summary = "创建任务", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/create")
    public Response createTask(@RequestBody Request<TaskDetailVo> request) {
        boolean result = createTaskProcessor.createTask(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }


    @Operation(summary = "分派任务", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/assign")
    public Response assignTask(@RequestBody Request<TaskAssignParams> request) {
        boolean result = assignTaskProcessor.assignTask(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "任务更新-追加任务进度和说明信息", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/updateProgress")
    public Response updateProgress(@RequestBody Request<TaskProgressBriefParams> request) {
        boolean result = updateProgressProcessor.updateProgress(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "task任务打分评价", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/evaluate")
    public Response evaluate(@RequestBody Request<TaskEvaluateParams> request) {
        boolean result = evaluateProcessor.evaluate(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "完成任务", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/achieve")
    public Response achieve(@RequestBody Request<TaskRejectParams> request) {
        boolean result = rejectTaskProcessor.reject(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }


}
