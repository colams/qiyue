package com.foxconn.sw.service.controller;


import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.group.GroupDetailVo;
import com.foxconn.sw.data.dto.request.group.ApplyJoinGroupParams;
import com.foxconn.sw.data.dto.request.group.CreateGroupParams;
import com.foxconn.sw.data.dto.request.group.SearchGroupParams;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.group.CreateGroupProcessor;
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

    @Permission
    @Operation(summary = "创建群组", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/create")
    public Response<GroupDetailVo> create(@Valid @RequestBody Request<CreateGroupParams> request) {
        GroupDetailVo groupDetailVo = createGroupProcessor.createGroup(request.getData());
        return ResponseUtils.success(groupDetailVo, request.getTraceId());
    }

    @Permission
    @Operation(summary = "获取群组", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<List<GroupDetailVo>> list(@Valid @RequestBody Request<SearchGroupParams> request) {
        List<GroupDetailVo> groupDetailVos = feedBackListProcessor.list(request.getData());
        return ResponseUtils.success(groupDetailVos, request.getTraceId());
    }

    @Permission
    @Operation(summary = "申请加入群组", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/applyJoin")
    public Response<Boolean> applyJoin(@Valid @RequestBody Request<ApplyJoinGroupParams> request) {
        return ResponseUtils.success(true, request.getTraceId());
    }

}
