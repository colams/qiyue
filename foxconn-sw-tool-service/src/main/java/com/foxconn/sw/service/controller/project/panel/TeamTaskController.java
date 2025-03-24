package com.foxconn.sw.service.controller.project.panel;

import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.panel.TeamTaskCountVo;
import com.foxconn.sw.data.dto.entity.panel.TeamTaskVo;
import com.foxconn.sw.data.dto.request.project.panel.TeamTaskParams;
import com.foxconn.sw.data.dto.request.project.settings.ProjectIdParams;
import com.foxconn.sw.service.utils.ResponseUtils;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Tag(name = "專案面板")
@RestController
@RequestMapping("api/pj/task")
public class TeamTaskController {
    @Operation(summary = "專案面板-新建團隊任務")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/save")
    public Response<Boolean> save(@Valid @RequestBody Request<TeamTaskParams> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "專案面板-團隊任務列表")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<PageEntity<List<TeamTaskVo>>> list(@Valid @RequestBody Request<PageParams<ProjectIdParams>> request) throws IOException {
        PageEntity<List<TeamTaskVo>> list = new PageEntity(0L, Lists.newArrayList());
        return ResponseUtils.success(list, request.getTraceId());
    }

    @Operation(summary = "專案面板-團隊任務數據")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/count")
    public Response<TeamTaskCountVo> count(@Valid @RequestBody Request<ProjectIdParams> request) throws IOException {
        TeamTaskCountVo vo = new TeamTaskCountVo();
        return ResponseUtils.success(vo, request.getTraceId());
    }
}
