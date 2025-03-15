package com.foxconn.sw.service.controller.project.document;

import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.project.doc.DocVo;
import com.foxconn.sw.data.dto.entity.project.doc.SearchVo;
import com.foxconn.sw.data.dto.request.project.doc.AddFileParams;
import com.foxconn.sw.data.dto.request.project.doc.ArchiveParams;
import com.foxconn.sw.data.dto.request.project.doc.SearchParams;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.project.doc.DocManageProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "專案管理--文檔管理")
@RestController
@RequestMapping("/api/pj/doc/docManage")
public class DocManageController {

    @Autowired
    private DocManageProcessor docManageProcessor;

    @Permission
    @Operation(summary = "搜索文件")
    @ApiResponse(responseCode = "0", description = "成功碼")
    @PostMapping("/search")
    public Response<List<SearchVo>> search(@Valid @RequestBody Request<SearchParams> request) {
        List<SearchVo> result = docManageProcessor.search(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "文件列表")
    @ApiResponse(responseCode = "0", description = "成功碼")
    @PostMapping("/listFile")
    public Response<List<DocVo>> listFile(@Valid @RequestBody Request<Integer> request) {
        List<DocVo> result = docManageProcessor.listFile(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "添加文件")
    @ApiResponse(responseCode = "0", description = "成功碼")
    @PostMapping("/addFile")
    public Response<Boolean> addFile(@Valid @RequestBody Request<AddFileParams> request) {
        Boolean result = docManageProcessor.addFile(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "未歸檔文件數量")
    @ApiResponse(responseCode = "0", description = "成功碼")
    @PostMapping("/countNoArchive")
    public Response<Integer> countNoArchive(@Valid @RequestBody Request<Integer> request) {
        Integer result = docManageProcessor.countNoArchive(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "未歸檔文件")
    @ApiResponse(responseCode = "0", description = "成功碼")
    @PostMapping("/listNoArchive")
    public Response<List<DocVo>> listNoArchive(@Valid @RequestBody Request<Integer> request) {
        List<DocVo> result = docManageProcessor.listNoArchive(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "歸檔")
    @ApiResponse(responseCode = "0", description = "成功碼")
    @PostMapping("/archive")
    public Response<Boolean> archive(@Valid @RequestBody Request<ArchiveParams> request) {
        Boolean result = docManageProcessor.archive(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "發佈")
    @ApiResponse(responseCode = "0", description = "成功碼")
    @PostMapping("/publish")
    public Response<Boolean> publish(@Valid @RequestBody Request<Boolean> request) {
        Boolean result = true;
        return ResponseUtils.success(result, request.getTraceId());
    }
}
