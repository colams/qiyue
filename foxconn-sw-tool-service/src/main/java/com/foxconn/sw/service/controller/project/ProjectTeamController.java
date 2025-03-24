package com.foxconn.sw.service.controller.project;

import com.alibaba.excel.EasyExcel;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.project.TeamVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.project.team.*;
import com.foxconn.sw.service.utils.ResponseUtils;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Tag(name = "团队管理")
@RequestMapping("/api/pj/team")
@RestController
@Slf4j
public class ProjectTeamController {

    @Autowired
    HttpServletResponse response;

    @Operation(summary = "列表", tags = TagsConstants.PROJECT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<PageEntity<TeamVo>> page(@Valid @RequestBody Request<PageParams<PagePjTeamParams>> request) {
        PageEntity<TeamVo> list = new PageEntity(0L, Lists.newArrayList());
        return ResponseUtils.success(list, request.getTraceId());
    }

    @Operation(summary = "新增", tags = TagsConstants.PROJECT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/add")
    public Response<Boolean> add(@Valid @RequestBody Request<AddPjTeamParams> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "編輯", tags = TagsConstants.PROJECT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/edit")
    public Response<Boolean> edit(@Valid @RequestBody Request<EditPjTeamParams> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "form表單導入", tags = TagsConstants.PROJECT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/import")
    public Response<Boolean> importExcel(@Valid @RequestParam("request") String otherParam,
                                         @RequestParam("multipartFile") MultipartFile file)
            throws IOException {
        Request<IntegerParams> request = JsonUtils.deserialize(otherParam, Request.class, IntegerParams.class);
//        ProjectTeamImportListener listener = new ProjectTeamImportListener(request.getData().getParams(), projectTeamBusiness);
//        EasyExcel.read(file.getInputStream(), ImportPjTeamParams.class, listener)
//                .sheet()
//                .doRead();
        return ResponseUtils.success(true, request.getTraceId());
    }

    @Operation(summary = "導出", tags = TagsConstants.PROJECT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/export")
    public ResponseEntity export(@Valid @RequestBody Request<ExportPjTeamParams> request) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        ExportPjTeamParams data = request.getData();
        String fileName = URLEncoder.encode("專案" + data.getProjectId() + "的團隊.xlsx", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName);
        List<ImportPjTeamParams> list = Lists.newArrayList();
        EasyExcel.write(response.getOutputStream(), ImportPjTeamParams.class).sheet("用户信息").doWrite(list);
        return ResponseEntity.ok().body(null);
    }

}
