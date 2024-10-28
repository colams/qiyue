package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.project.ProjectListVo;
import com.foxconn.sw.data.dto.request.project.ProjectSaveParams;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.project.ProjectListProcessor;
import com.foxconn.sw.service.processor.project.ProjectSaveProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/oa/project")
public class ProjectController {

    @Autowired
    ProjectListProcessor projectListProcessor;
    @Autowired
    ProjectSaveProcessor projectSaveProcessor;


    @Permission
    @Operation(summary = "获取专案列表", tags = TagsConstants.PROJECT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<ProjectListVo> list(@Valid @RequestBody Request request) {
        ProjectListVo listVo = projectListProcessor.list();
        return ResponseUtils.success(listVo, request.getTraceId());
    }


    @Permission
    @Operation(summary = "保存专案信息", tags = TagsConstants.PROJECT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/save")
    public Response<Boolean> save(@Valid @RequestBody Request<ProjectSaveParams> request) {
        Boolean result = projectSaveProcessor.save(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }


}
