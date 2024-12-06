package com.foxconn.sw.service.controller;


import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.group.GroupBriefVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.group.ApplyJoinGroupParams;
import com.foxconn.sw.data.dto.request.group.CreateGroupParams;
import com.foxconn.sw.data.dto.request.group.ProcessApplyParams;
import com.foxconn.sw.data.dto.request.group.SearchGroupParams;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.group.CreateGroupProcessor;
import com.foxconn.sw.service.processor.group.GroupListProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    CreateGroupProcessor createGroupProcessor;
    @Autowired
    GroupListProcessor groupListProcessor;

    @Permission
    @Operation(summary = "创建群组", tags = TagsConstants.GROUP)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/create")
    public Response<GroupBriefVo> create(@Valid @RequestBody Request<CreateGroupParams> request) {
        GroupBriefVo briefVo = createGroupProcessor.createGroup(request.getData());
        return ResponseUtils.success(briefVo, request.getTraceId());
    }

    @Permission
    @Operation(summary = "维护群组信息", tags = TagsConstants.GROUP)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/update")
    public Response<GroupBriefVo> update(@Valid @RequestBody Request<CreateGroupParams> request) {
        GroupBriefVo briefVo = createGroupProcessor.createGroup(request.getData());
        return ResponseUtils.success(briefVo, request.getTraceId());
    }

    @Permission
    @Operation(summary = "获取群组", tags = TagsConstants.GROUP)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<PageEntity> list(@Valid @RequestBody Request<PageParams<SearchGroupParams>> request) {
        PageEntity entity = groupListProcessor.list(request.getData());
        return ResponseUtils.success(entity, request.getTraceId());
    }


    @Permission
    @Operation(summary = "申请加入群组", tags = TagsConstants.GROUP)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/applyJoin")
    public Response<Boolean> applyJoin(@Valid @RequestBody Request<ApplyJoinGroupParams> request) {
        return ResponseUtils.success(true, request.getTraceId());
    }


    @Permission
    @Operation(summary = "處理申請加群操作", tags = TagsConstants.GROUP)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/process")
    public Response<Boolean> processApply(@Valid @RequestBody Request<ProcessApplyParams> request) {
        return ResponseUtils.success(true, request.getTraceId());
    }

    @Permission
    @Operation(summary = "获取待处理加群操作", tags = TagsConstants.GROUP)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/applyList")
    public Response<Boolean> applyList(@Valid @RequestBody Request<ProcessApplyParams> request) {
        return ResponseUtils.success(true, request.getTraceId());
    }


    @Permission
    @Operation(summary = "解散群组操作", tags = TagsConstants.GROUP)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/disband")
    public Response<Boolean> disband(@Valid @RequestBody Request<IntegerParams> request) {
        return ResponseUtils.success(true, request.getTraceId());
    }

    @Permission
    @Operation(summary = "收藏群组操作", tags = TagsConstants.GROUP)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/collect")
    public Response<Boolean> collect(@Valid @RequestBody Request<IntegerParams> request) {
        return ResponseUtils.success(true, request.getTraceId());
    }

    @Permission
    @Operation(summary = "查看群组成员操作", tags = TagsConstants.GROUP)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/members")
    public Response<Boolean> members(@Valid @RequestBody Request<IntegerParams> request) {
        return ResponseUtils.success(true, request.getTraceId());
    }


}
