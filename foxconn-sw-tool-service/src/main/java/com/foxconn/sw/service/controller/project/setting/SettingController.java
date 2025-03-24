package com.foxconn.sw.service.controller.project.setting;

import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.project.SysMoreProjectListVo;
import com.foxconn.sw.data.dto.entity.project.SysProjectDetailVo;
import com.foxconn.sw.data.dto.entity.project.SysProjectListVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.entity.universal.StringParams;
import com.foxconn.sw.data.dto.request.project.*;
import com.foxconn.sw.service.utils.ResponseUtils;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "專案管理系統")
@RestController
@RequestMapping("api/pj/setting")
public class SettingController {
    @Operation(summary = "首頁-新建专案")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/index/save")
    public Response<Boolean> saveProject(@Valid @RequestBody Request<SysProjectSaveParams> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "專案設置-更新专案")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/update")
    public Response<Boolean> updateProject(@Valid @RequestBody Request<SysProjectUpdateParams> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "專案管理系統-獲取专案信息詳細信息")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/detail")
    public Response<SysProjectDetailVo> detailProject(@Valid @RequestBody Request<IntegerParams> request) {
        SysProjectDetailVo sysProjectDetailVo = new SysProjectDetailVo();
        return ResponseUtils.success(sysProjectDetailVo, request.getTraceId());
    }

    @Operation(summary = "專案管理系統-首頁-專案信息列表")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/index/list")
    public Response<PageEntity<List<SysProjectListVo>>> indexProjectList(@Valid @RequestBody Request<PageParams<YearParams>> request) throws IOException {
        PageEntity<List<SysProjectListVo>> list = new PageEntity(0L, Lists.newArrayList());
        return ResponseUtils.success(list, request.getTraceId());
    }

    @Operation(summary = "專案管理系統-首頁-導入排期")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/index/import-schedule")
    public Response<Boolean> importSchedule(@Valid @RequestBody Request<ImportScheduleParams> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "專案管理系統-首頁-專案修改")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/index/update")
    public Response<Boolean> indexProjectUpdate(@Valid @RequestBody Request<SysIndexProjectUpdateParams> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "專案管理系統-首頁-更多")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/index/more-list")
    public Response<PageEntity<SysMoreProjectListVo>> moreProjectList(@Valid @RequestBody Request<PageParams<StringParams>> request) throws IOException {
        PageEntity<SysMoreProjectListVo> list = new PageEntity(0L, Lists.newArrayList());
        return ResponseUtils.success(list, request.getTraceId());
    }

    @Operation(summary = "Project List导出")
    @ApiResponse(responseCode = "0", description = "成功码")
    @CrossOrigin(exposedHeaders = {"Content-Disposition"})
    @PostMapping("/more-list/export")
    public ResponseEntity export(@Valid @RequestBody Request<PageParams<StringParams>> request) throws IOException {
        return ResponseEntity.ok().body(null);
    }

    @Operation(summary = "Project List導入")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/import")
    public Response<Boolean> importExcel(@Valid @RequestParam("request") String reqJson, @RequestParam("multipartFile")
            MultipartFile multipartFile) throws IOException {
        Request<StringParams> request = JsonUtils.deserialize(reqJson, Request.class, StringParams.class);
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }
}
