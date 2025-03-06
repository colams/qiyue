package com.foxconn.sw.service.controller;

import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.request.file.SearchFileParams;
import com.foxconn.sw.data.dto.response.file.SearchFileResult;
import com.foxconn.sw.service.utils.ResponseUtils;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/file")
public class FileController {

    @Operation(summary = "文档管理，文档搜索", tags = "changeLog")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/searchFile")
    public Response<List<SearchFileResult>> searchFile(@Valid @RequestBody Request<SearchFileParams> request) {
        SearchFileResult result = new SearchFileResult();
        result.setFileName("");
        result.setSize(0);
        result.setFileType("");
        result.setFileId(0);
        result.setPhase("");
        result.setAuthor("");
        result.setCreator("");
        result.setUpdateTime("");
        List<SearchFileResult> results = Lists.newArrayList(result);
        return ResponseUtils.success(results, request.getTraceId());
    }
}
