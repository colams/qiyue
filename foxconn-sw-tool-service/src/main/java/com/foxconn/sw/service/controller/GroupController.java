package com.foxconn.sw.service.controller;


import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.group.GroupBriefVo;
import com.foxconn.sw.data.dto.entity.group.GroupMemberVo;
import com.foxconn.sw.data.dto.entity.system.AuthorizedVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.enums.AuthorizedEnums;
import com.foxconn.sw.data.dto.request.group.*;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.group.ApplyJoinProcessor;
import com.foxconn.sw.service.processor.group.AuthorizedProcessor;
import com.foxconn.sw.service.processor.group.CreateGroupProcessor;
import com.foxconn.sw.service.processor.group.GroupListProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    CreateGroupProcessor createGroupProcessor;
    @Autowired
    GroupListProcessor groupListProcessor;
    @Autowired
    ApplyJoinProcessor applyJoinProcessor;
    @Autowired
    AuthorizedProcessor authorizedProcessor;

    @Permission
    @Operation(summary = "创建群组", tags = TagsConstants.GROUP)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/authorized")
    public Response<List<AuthorizedVo>> authorized(@Valid @RequestBody Request<List<AuthorizedEnums>> request) {
        List<AuthorizedVo> vos = authorizedProcessor.authorized(request.getData());
        return ResponseUtils.success(vos, request.getTraceId());
    }

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
    public Response<Boolean> update(@Valid @RequestBody Request<UpdateGroupParams> request) {
        Boolean result = createGroupProcessor.updateGroup(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
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
        boolean result = applyJoinProcessor.applyJoin(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }


    @Permission
    @Operation(summary = "處理申請加群操作", tags = TagsConstants.GROUP)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/process")
    public Response<Boolean> processApply(@Valid @RequestBody Request<ProcessApplyParams> request) {
        boolean result = applyJoinProcessor.process(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "获取待处理加群操作", tags = TagsConstants.GROUP)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/operateList")
    public Response<Boolean> operateList(@Valid @RequestBody Request request) {
        applyJoinProcessor.operateList();
        return ResponseUtils.success(true, request.getTraceId());
    }


    @Permission
    @Operation(summary = "解散群组操作", tags = TagsConstants.GROUP)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/disband")
    public Response<Boolean> disband(@Valid @RequestBody Request<IntegerParams> request) {
        boolean result = createGroupProcessor.disband(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "收藏群组操作", tags = TagsConstants.GROUP)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/collect")
    public Response<Boolean> collect(@Valid @RequestBody Request<IntegerParams> request) {
        boolean result = applyJoinProcessor.collect(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "查看群组成员操作", tags = TagsConstants.GROUP)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/members")
    public Response<List<GroupMemberVo>> members(@Valid @RequestBody Request<IntegerParams> request) {
        List<GroupMemberVo> vos = createGroupProcessor.listMember(request.getData());
        return ResponseUtils.success(vos, request.getTraceId());
    }
}
