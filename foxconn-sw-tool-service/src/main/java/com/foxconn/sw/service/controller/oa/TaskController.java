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
    @Autowired
    AchieveTaskProcessor achieveTaskProcessor;

    @Operation(summary = "任务列表", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<PageEntity<TaskBriefListVo>> list(@RequestBody Request<PageParams<TaskParams>> request) {
        PageEntity<TaskBriefListVo> taskList = taskListProcessor.list(request.getData(), request.getHead());
        return ResponseUtils.success(taskList, request.getTraceId());
    }


    @Operation(summary = "任务详情", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/detail")
    public Response<TaskEntityVo> detail(@RequestBody Request<IDParams> request) {
        TaskEntityVo taskEntityVo = taskDetailProcessor.detail(request.getData(), request.getHead());
        return ResponseUtils.success(taskEntityVo, request.getTraceId());
    }


    @Operation(summary = "列表页-任务总览", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/overview")
    public Response<List<TaskOverviewVo>> overview(@RequestBody Request request) {
        List<TaskOverviewVo> taskList = overviewProcessor.overview(request.getHead());
        return ResponseUtils.success(taskList, request.getTraceId());
    }

    @Operation(summary = "创建任务", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/create")
    public Response<Boolean> createTask(@RequestBody Request<TaskBriefDetailVo> request) {
        boolean result = createTaskProcessor.createTask(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }


    @Operation(summary = "分派任务", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/assign")
    public Response<Boolean> assignTask(@RequestBody Request<TaskAssignParams> request) {
        boolean result = assignTaskProcessor.assignTask(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "任务更新-追加任务进度和说明信息", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/updateProgress")
    public Response<Boolean> updateProgress(@RequestBody Request<TaskProgressBriefParams> request) {
        boolean result = updateProgressProcessor.updateProgress(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "task任务打分评价", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/evaluate")
    public Response<Boolean> evaluate(@RequestBody Request<TaskEvaluateParams> request) {
        boolean result = evaluateProcessor.evaluate(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }

    /**
     * todo
     *
     * @param request
     * @return
     */
    @Operation(summary = "驳回任务", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/reject")
    public Response<Boolean> reject(@RequestBody Request<TaskRejectParams> request) {
        boolean result = rejectTaskProcessor.reject(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }

    /**
     * todo
     *
     * @param request
     * @return
     */
    @Operation(summary = "完成任务", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/achieve")
    public Response<Boolean> achieve(@RequestBody Request<TaskRejectParams> request) {
        boolean result = achieveTaskProcessor.achieve(request.getData(), request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }
}
