package com.foxconn.sw.service.controller;


import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.request.feedback.CreateFeedBackParams;
import com.foxconn.sw.data.dto.request.feedback.FeedBackStatusParams;
import com.foxconn.sw.data.entity.SwFeedback;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.feedback.FeedBackListProcessor;
import com.foxconn.sw.service.processor.feedback.FeedBackSaveProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/feedback")
public class FeedBackController {

    @Autowired
    FeedBackListProcessor feedBackListProcessor;
    @Autowired
    FeedBackSaveProcessor feedBackSaveProcessor;

    @Permission
    @Operation(summary = "获取反馈问题", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<List<SwFeedback>> list(@Valid @RequestBody Request request) {
        List<SwFeedback> feedbacks = feedBackListProcessor.list();
        return ResponseUtils.success(feedbacks, request.getTraceId());
    }

    @Operation(summary = "新增反馈问题", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/save")
    public Response<Boolean> save(@Valid @RequestBody Request<CreateFeedBackParams> request) {
        Boolean result = feedBackSaveProcessor.save(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "更新反馈状态", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/update")
    public Response<Boolean> update(@Valid @RequestBody Request<FeedBackStatusParams> request) {
        Boolean result = feedBackSaveProcessor.updateFeedBackStatus(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }
}
