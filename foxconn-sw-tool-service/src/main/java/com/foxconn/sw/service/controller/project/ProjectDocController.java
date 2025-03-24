package com.foxconn.sw.service.controller.project;

import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.project.doc.DocVo;
import com.foxconn.sw.data.dto.entity.project.doc.HomePageVo;
import com.foxconn.sw.data.dto.request.project.doc.*;
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

import java.util.ArrayList;
import java.util.List;

@Tag(name = "專案管理--文檔管理")
@RestController
@RequestMapping("/api/pj/doc/docManage")
public class ProjectDocController {

    @Operation(summary = "搜索文件")
    @ApiResponse(responseCode = "0", description = "成功碼")
    @PostMapping("/search")
    public Response<List<DocVo>> search(@Valid @RequestBody Request<SearchParams> request) {
        List<DocVo> result = Lists.newArrayList();
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "高级搜索")
    @ApiResponse(responseCode = "0", description = "成功碼")
    @PostMapping("/advancedSearch")
    public Response<List<DocVo>> advancedSearch(@Valid @RequestBody Request<AdvancedSearchParams> request) {
        List<DocVo> result = Lists.newArrayList();
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "首页")
    @ApiResponse(responseCode = "0", description = "成功碼")
    @PostMapping("/homePage")
    public Response<HomePageVo> homePage(@Valid @RequestBody Request<Integer> request) {
        HomePageVo result = new HomePageVo();
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "文件列表")
    @ApiResponse(responseCode = "0", description = "成功碼")
    @PostMapping("/listFile")
    public Response<List<DocVo>> listFile(@Valid @RequestBody Request<ListFileParams> request) {
        List<DocVo> result = new ArrayList<>();
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "添加文件")
    @ApiResponse(responseCode = "0", description = "成功碼")
    @PostMapping("/addFile")
    public Response<Boolean> addFile(@Valid @RequestBody Request<AddFileParams> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "未歸檔文件數量")
    @ApiResponse(responseCode = "0", description = "成功碼")
    @PostMapping("/countNoArchive")
    public Response<Integer> countNoArchive(@Valid @RequestBody Request<Integer> request) {
        Integer result = 1;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "未歸檔文件")
    @ApiResponse(responseCode = "0", description = "成功碼")
    @PostMapping("/listNoArchive")
    public Response<List<DocumentDetailVo>> listNoArchive(@Valid @RequestBody Request<Integer> request) {
        List<DocumentDetailVo> result = new ArrayList<>();
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "歸檔")
    @ApiResponse(responseCode = "0", description = "成功碼")
    @PostMapping("/archive")
    public Response<Boolean> archive(@Valid @RequestBody Request<ArchiveParams> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }
}
