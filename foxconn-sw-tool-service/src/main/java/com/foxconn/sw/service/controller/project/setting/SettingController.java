package com.foxconn.sw.service.controller.project.setting;

import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.project.SysMoreProjectListVo;
import com.foxconn.sw.data.dto.entity.project.SysProjectDetailVo;
import com.foxconn.sw.data.dto.entity.project.SysProjectListVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.entity.universal.StringParams;
import com.foxconn.sw.data.dto.request.project.ImportScheduleParams;
import com.foxconn.sw.data.dto.request.project.SysIndexProjectUpdateParams;
import com.foxconn.sw.data.dto.request.project.SysProjectSaveParams;
import com.foxconn.sw.service.utils.ResponseUtils;
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

@Tag(name = "專案管理系統")
@RestController
@RequestMapping("api/pj/setting")
public class SettingController {
    @Operation(summary = "專案管理系統-新建专案信息")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/save")
    public Response<Boolean> saveProject(@Valid @RequestBody Request<SysProjectSaveParams> request) {
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
    public Response<PageEntity<List<SysProjectListVo>>> indexProjectList(@Valid @RequestBody Request<PageParams<StringParams>> request) throws IOException {
        PageEntity<List<SysProjectListVo>> list = new PageEntity<>();
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
    public Response<PageEntity<List<SysMoreProjectListVo>>> moreProjectList(@Valid @RequestBody Request<PageParams<StringParams>> request) throws IOException {
        PageEntity<List<SysMoreProjectListVo>> list = new PageEntity<>();
        return ResponseUtils.success(list, request.getTraceId());
    }
}
