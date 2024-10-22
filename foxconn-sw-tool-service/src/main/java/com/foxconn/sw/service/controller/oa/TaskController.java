package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.oa.*;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.task.OverviewParams;
import com.foxconn.sw.data.dto.request.task.UpdateTaskParams;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.task.*;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
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
    UpdateTaskProcessor updateTaskProcessor;
    @Autowired
    AssignTaskProcessor assignTaskProcessor;
    @Autowired
    UpdateProgressProcessor updateProgressProcessor;
    @Autowired
    EvaluateProcessor evaluateProcessor;
    @Autowired
    RejectTaskProcessor rejectTaskProcessor;
    @Autowired
    AchieveTaskProcessor achieveTaskProcessor;
    @Autowired
    RevokeProcessor revokeProcessor;
    @Autowired
    FollowProcessor followProcessor;
    @Autowired
    AcceptProcessor acceptProcessor;
    @Autowired
    BriefDetailProcessor briefDetail;

    @Permission
    @Operation(summary = "创建任务", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/create")
    public Response createTask(@Valid @RequestBody Request<TaskBriefDetailVo> request) {
        Integer taskID = createTaskProcessor.createTask(request.getData());
        return ResponseUtils.success(taskID, request.getTraceId());
    }

    @Permission
    @Operation(summary = "更新任务", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/update")
    public Response updateTask(@Valid @RequestBody Request<UpdateTaskParams> request) {
        updateTaskProcessor.updateTask(request.getData());
        return ResponseUtils.success(request.getTraceId());
    }

    @Permission
    @Operation(summary = "任务列表", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<PageEntity<TaskBriefListVo>> list(@Valid @RequestBody Request<PageParams<TaskParams>> request) {
        PageEntity<TaskBriefListVo> taskList = taskListProcessor.list(request.getData());
        return ResponseUtils.success(taskList, request.getTraceId());
    }

    @Permission
    @Operation(summary = "列表页-任务总览", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/overview")
    public Response<List<TaskOverviewVo>> overview(@Valid @RequestBody Request<OverviewParams> request) {
        List<TaskOverviewVo> taskList = overviewProcessor.overview(request.getData());
        return ResponseUtils.success(taskList, request.getTraceId());
    }

    @Permission
    @Operation(summary = "任务详情", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/detail")
    public Response<TaskEntityVo> detail(@Valid @RequestBody Request<IntegerParams> request) {
        TaskEntityVo taskEntityVo = taskDetailProcessor.detail(request.getData(), request.getHead());
        return ResponseUtils.success(taskEntityVo, request.getTraceId());
    }

    @Operation(summary = "任务信息", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/briefDetail")
    public Response<SwTask> briefDetail(@Valid @RequestBody Request<IntegerParams> request) {
        SwTask task = briefDetail.getTaskById(request.getData().getParams());
        return ResponseUtils.success(task, request.getTraceId());
    }

    @Permission
    @Operation(summary = "分派任务", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/assign")
    public Response<Boolean> assignTask(@Valid @RequestBody Request<TaskAssignParams> request) {
        boolean result = assignTaskProcessor.assignTask(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "任务更新-追加任务进度和说明信息", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/updateProgress")
    public Response<Boolean> updateProgress(@Valid @RequestBody Request<TaskProgressBriefParams> request) {
        boolean result = updateProgressProcessor.updateProgress(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "完成任务", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/achieve")
    public Response achieve(@Valid @RequestBody Request<TaskProgressBriefParams> request) {
        achieveTaskProcessor.achieve(request.getData(), request.getHead());
        return ResponseUtils.success(request.getTraceId());
    }

    @Operation(summary = "task任务打分评价", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/evaluate")
    public Response<Boolean> evaluate(@Valid @RequestBody Request<TaskEvaluateParams> request) {
        boolean result = evaluateProcessor.evaluate(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "驳回任务", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/reject")
    public Response<Boolean> reject(@RequestBody Request<TaskRejectParams> request) {
        boolean result = rejectTaskProcessor.reject(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "撤销任务", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/revoke")
    public Response<Boolean> revoke(@Valid @RequestBody Request<IntegerParams> request) {
        boolean result = revokeProcessor.revoke(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "撤销任务", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/follow")
    public Response follow(@Valid @RequestBody Request<FollowParams> request) {
        boolean result = followProcessor.follow(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "接受任务", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/accept")
    public Response accept(@Valid @RequestBody Request<IntegerParams> request) {
        boolean result = acceptProcessor.accept(request.getData().getParams(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }
}
