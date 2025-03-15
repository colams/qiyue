package com.foxconn.sw.service.controller.project.setting;

import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.request.project.settings.BatchAddDirParams;
import com.foxconn.sw.data.dto.request.project.settings.BatchDelDirParams;
import com.foxconn.sw.data.dto.request.project.settings.BatchRenameParams;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "專案管理--專案設置--目錄維護")
@RestController
@RequestMapping("api/pj/doc/tree")
public class TreeController {

    @Operation(summary = "批量添加目錄")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/batchAdd")
    public Response<Boolean> batchAdd(@Valid @RequestBody Request<BatchAddDirParams> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "批量修改目錄")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/batchRename")
    public Response<Boolean> batchRename(@Valid @RequestBody Request<BatchRenameParams> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "批量刪除目錄")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/batchDelDir")
    public Response<Boolean> batchDelDir(@Valid @RequestBody Request<BatchDelDirParams> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }
}
