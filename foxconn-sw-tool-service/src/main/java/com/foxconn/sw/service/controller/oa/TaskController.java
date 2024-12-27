package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.TupleValue;
import com.foxconn.sw.data.dto.entity.oa.FollowParams;
import com.foxconn.sw.data.dto.entity.task.*;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.task.OverviewParams;
import com.foxconn.sw.data.dto.request.task.SubTaskParams;
import com.foxconn.sw.data.dto.request.task.TaskEmployRelationParams;
import com.foxconn.sw.data.dto.request.task.UpdateTaskParams;
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
    @Autowired
    TaskEmployeeRelationProcessor employeeRelationProcessor;
    @Autowired
    TaskHistoryProcessor taskHistoryProcessor;
    @Autowired
    ReadTaskProcessor readTaskProcessor;

    @Permission
    @Operation(summary = "创建任务", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/create")
    public Response createTask(@Valid @RequestBody Request<TaskBriefDetailVo> request) {
        Integer taskID = createTaskProcessor.createTask(request.getData());
        return ResponseUtils.success(taskID, request.getTraceId());
    }

    @Permission
    @Operation(summary = "创建子任务", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/createSubTask")
    public Response createSubTask(@Valid @RequestBody Request<SubTaskParams> request) {
        Integer taskID = createTaskProcessor.createSubTask(request.getData());
        return ResponseUtils.success(taskID, request.getTraceId());
    }

    @Permission
    @Operation(summary = "创建子任务", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/subTaskList")
    public Response<List<TaskBriefListVo>> subTaskList(@Valid @RequestBody Request<IntegerParams> request) {
        List<TaskBriefListVo> vos = taskListProcessor.subTaskList(request.getData());
        return ResponseUtils.success(vos, request.getTraceId());
    }

    @Permission
    @Operation(summary = "更新任务", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/update")
    public Response updateTask(@Valid @RequestBody Request<UpdateTaskParams> request) {
        updateTaskProcessor.updateTask(request.getData());
        return ResponseUtils.success(request.getTraceId());
    }

    @Permission
    @Operation(summary = "任务列表", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<PageEntity<TaskBriefListVo>> list(@Valid @RequestBody Request<PageParams<TaskParams>> request) {
        PageEntity<TaskBriefListVo> taskList = taskListProcessor.list(request.getData());
        return ResponseUtils.success(taskList, request.getTraceId());
    }

    @Permission
    @Operation(summary = "任务列表", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list2")
    public Response<TaskListPageVo> list2(@Valid @RequestBody Request<PageParams<TaskParams>> request) {
        TaskListPageVo vo = taskListProcessor.list2(request.getData());
        return ResponseUtils.success(vo, request.getTraceId());
    }

    @Permission
    @Operation(summary = "任务列表", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/read")
    public Response<Boolean> read(@Valid @RequestBody Request<IntegerParams> request) {
        Boolean result = readTaskProcessor.setRead(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "列表页-任务总览", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/overview")
    public Response<List<TaskOverviewVo>> overview(@Valid @RequestBody Request<OverviewParams> request) {
        List<TaskOverviewVo> taskList = overviewProcessor.overview(request.getData());
        return ResponseUtils.success(taskList, request.getTraceId());
    }

    @Permission
    @Operation(summary = "任务详情", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/detail")
    public Response<TaskEntityVo> detail(@Valid @RequestBody Request<IntegerParams> request) {
        TaskEntityVo taskEntityVo = taskDetailProcessor.detail(request.getData(), request.getHead());
        return ResponseUtils.success(taskEntityVo, request.getTraceId());
    }

    @Permission
    @Operation(summary = "任务信息", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/briefDetail")
    public Response<BriefTaskVo> briefDetail(@Valid @RequestBody Request<IntegerParams> request) {
        BriefTaskVo task = briefDetail.getTaskById(request.getData().getParams());
        return ResponseUtils.success(task, request.getTraceId());
    }

    @Permission
    @Operation(summary = "分派转交任务", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/assign")
    public Response<Boolean> assignTask(@Valid @RequestBody Request<TaskAssignParams> request) {
        boolean result = assignTaskProcessor.assignTask(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "任务更新-追加任务进度和说明信息", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/updateProgress")
    public Response<Boolean> updateProgress(@Valid @RequestBody Request<TaskProgressBriefParams> request) {
        boolean result = updateProgressProcessor.updateProgress(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "完成任务", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/achieve")
    public Response achieve(@Valid @RequestBody Request<TaskProgressBriefParams> request) {
        achieveTaskProcessor.achieve(request.getData(), request.getHead());
        return ResponseUtils.success(request.getTraceId());
    }

    @Permission
    @Operation(summary = "task任务打分评价", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/evaluate")
    public Response<Boolean> evaluate(@Valid @RequestBody Request<TaskEvaluateParams> request) {
        boolean result = evaluateProcessor.evaluate(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "驳回任务", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/reject")
    public Response<Boolean> reject(@RequestBody Request<TaskRejectParams> request) {
        boolean result = rejectTaskProcessor.reject(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "撤销任务", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/revoke")
    public Response<Boolean> revoke(@Valid @RequestBody Request<IntegerParams> request) {
        boolean result = revokeProcessor.revoke(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "撤销任务", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/follow")
    public Response follow(@Valid @RequestBody Request<FollowParams> request) {
        boolean result = followProcessor.follow(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "接受任务", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/accept")
    public Response accept(@Valid @RequestBody Request<IntegerParams> request) {
        boolean result = acceptProcessor.accept(request.getData().getParams(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "刪除任务", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/delete")
    public Response delete(@Valid @RequestBody Request<IntegerParams> request) {
        boolean result = updateTaskProcessor.delete(request.getData().getParams());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "接受任务", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/taskEmployeeRelation")
    public Response taskEmployeeRelation(@Valid @RequestBody Request<TaskEmployRelationParams> request) {
        boolean result = employeeRelationProcessor.updateEmployeeRelation(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "接受任务", tags = TagsConstants.TASK)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/historyContent")
    public Response<TupleValue> historyContent(@Valid @RequestBody Request<IntegerParams> request) {
        TupleValue value = taskHistoryProcessor.getHistoryContent(request.getData().getParams());
        return ResponseUtils.success(value, request.getTraceId());
    }

}
