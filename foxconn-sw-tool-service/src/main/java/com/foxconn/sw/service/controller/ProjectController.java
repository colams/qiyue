package com.foxconn.sw.service.controller;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.project.ProjectListVo;
import com.foxconn.sw.data.dto.request.project.ProjectSaveParams;
import com.foxconn.sw.data.dto.request.project.ProjectUpdateParams;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/oa/project")
public class ProjectController {
    @Operation(summary = "获取专案列表", tags = TagsConstants.PROJECT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<ProjectListVo> list(@Valid @RequestBody Request request) throws IOException {
        ProjectListVo listVo = new ProjectListVo();
        return ResponseUtils.success(listVo, request.getTraceId());
    }

    @Operation(summary = "新建专案信息", tags = TagsConstants.PROJECT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/save")
    public Response<Boolean> save(@Valid @RequestBody Request<ProjectSaveParams> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "更新专案信息", tags = TagsConstants.PROJECT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/update")
    public Response<Boolean> update(@Valid @RequestBody Request<ProjectUpdateParams> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "导入专案信息", tags = TagsConstants.PROJECT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/import")
    public Response<Boolean> importExcel(@Valid @RequestBody Request request) throws IOException {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "导出专案", tags = TagsConstants.PROJECT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @CrossOrigin(exposedHeaders = {"Content-Disposition"})
    @PostMapping("/export")
    public ResponseEntity export(@Valid @RequestBody Request request) throws IOException {
        return ResponseEntity.ok().body(null);
    }
}
