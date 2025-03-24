package com.foxconn.sw.service.controller;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.document.DocumentDetailVo;
import com.foxconn.sw.data.dto.entity.document.DocumentVo;
import com.foxconn.sw.data.dto.entity.system.OptionClassVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.document.*;
import com.foxconn.sw.data.dto.request.system.OptionParams;
import com.foxconn.sw.service.processor.document.GetOptionsProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/document")
public class DocumentController {
    @Autowired
    GetOptionsProcessor getOptionsProcessor;

    @Operation(summary = "新增文档信息", tags = TagsConstants.DOCUMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/create")
    public Response create(@Valid @RequestBody Request<CreateDocParams> request) {
        return ResponseUtils.success(false, request.getTraceId());
    }

    @Operation(summary = "更新文档版本", tags = TagsConstants.DOCUMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/revise")
    public Response revise(@Valid @RequestBody Request<ReviseDocParams> request) {
        return ResponseUtils.success(false, request.getTraceId());
    }

    @Operation(summary = "更新文档版本", tags = TagsConstants.DOCUMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/updateInfo")
    public Response updateInfo(@Valid @RequestBody Request<UpdateDocParams> request) {
        return ResponseUtils.success(false, request.getTraceId());
    }

    @Operation(summary = "删除文档信息", tags = TagsConstants.DOCUMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/delete")
    public Response delete(@Valid @RequestBody Request<DeleteDocParams> request) {
        return ResponseUtils.success(true, request.getTraceId());
    }


    @Operation(summary = "文档信息列表", tags = TagsConstants.DOCUMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<PageEntity<List<DocumentVo>>> list(@Valid @RequestBody Request<PageParams<SearchDocParams>> request) {
        return ResponseUtils.success(new PageEntity(0L, Lists.newArrayList()), request.getTraceId());
    }

    @Operation(summary = "文档信息列表", tags = TagsConstants.DOCUMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/detail")
    public Response<DocumentDetailVo> detailVoResponse(@Valid @RequestBody Request<IntegerParams> request) {
        return ResponseUtils.success(new DocumentDetailVo(), request.getTraceId());
    }

    @Operation(summary = "选项获取接口", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/getOptions")
    public Response<List<OptionClassVo>> getOptions(@Valid @RequestBody Request<List<OptionParams>> request) {
        List<OptionClassVo> vos = getOptionsProcessor.getOptions(request.getData());
        return ResponseUtils.success(vos, request.getTraceId());
    }

}
