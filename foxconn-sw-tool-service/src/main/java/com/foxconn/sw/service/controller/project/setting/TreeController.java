package com.foxconn.sw.service.controller.project.setting;

import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.request.project.SwProjectDocTree;
import com.foxconn.sw.data.dto.request.project.settings.AddSubParams;
import com.foxconn.sw.data.dto.request.project.settings.ListDirParams;
import com.foxconn.sw.data.dto.request.project.settings.RenameNodeParams;
import com.foxconn.sw.data.dto.request.project.settings.SortParams;
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

import java.util.List;


@Tag(name = "專案設置--目錄維護")
@RestController
@RequestMapping("api/pj/doc/tree")
public class TreeController {

    @Operation(summary = "下一級目錄列表", description = "查看某個節點下一級的子目錄")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/listNext")
    public Response<List<SwProjectDocTree>> listNext(@Valid @RequestBody Request<ListDirParams> request) {
        List<SwProjectDocTree> result = Lists.newArrayList();
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "添加節點")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/add")
    public Response<Boolean> add(@Valid @RequestBody Request<AddSubParams> request) {
        request.getData().setPriority(1);
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "排序")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/sort")
    public Response<Boolean> sort(@Valid @RequestBody Request<List<SortParams>> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "重命名")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/rename")
    public Response<Boolean> rename(@Valid @RequestBody Request<RenameNodeParams> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "刪除前是否彈出安全提示")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/canDelete")
    public Response<Boolean> canDelete(@Valid @RequestBody Request<Integer> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "刪除節點")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/del")
    public Response<Boolean> del(@Valid @RequestBody Request<Integer> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }
}
