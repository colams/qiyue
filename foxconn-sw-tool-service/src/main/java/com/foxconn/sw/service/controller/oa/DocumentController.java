package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.document.DocumentDetailVo;
import com.foxconn.sw.data.dto.entity.document.DocumentVo;
import com.foxconn.sw.data.dto.entity.document.HistoryVo;
import com.foxconn.sw.data.dto.entity.system.OptionClassVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.document.*;
import com.foxconn.sw.data.dto.request.system.OptionParams;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.document.CreateDocProcessor;
import com.foxconn.sw.service.processor.document.DetailDocProcessor;
import com.foxconn.sw.service.processor.document.GetOptionsProcessor;
import com.foxconn.sw.service.processor.document.ListDocProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
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
    CreateDocProcessor createDoc;
    @Autowired
    ListDocProcessor listDoc;
    @Autowired
    DetailDocProcessor detailDoc;
    @Autowired
    GetOptionsProcessor getOptionsProcessor;

    @Permission
    @Operation(summary = "新增文档信息", tags = TagsConstants.DOCUMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/create")
    public Response create(@Valid @RequestBody Request<CreateDocParams> request) {
        boolean result = createDoc.create(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "新增文档信息", tags = TagsConstants.DOCUMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/createPersonalDoc")
    public Response createPersonalDoc(@Valid @RequestBody Request<CreatePersonalDocParams> request) {
        boolean result = createDoc.createPersonalDoc(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "更新文档版本", tags = TagsConstants.DOCUMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/revise")
    public Response revise(@Valid @RequestBody Request<ReviseDocParams> request) {
        boolean result = createDoc.revise(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "更新文档版本", tags = TagsConstants.DOCUMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/updateInfo")
    public Response updateInfo(@Valid @RequestBody Request<UpdateDocParams> request) {
        boolean result = createDoc.update(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "删除文档信息", tags = TagsConstants.DOCUMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/delete")
    public Response delete(@Valid @RequestBody Request<DeleteDocParams> request) {
        boolean result = createDoc.delete(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "删除文档版本信息", tags = TagsConstants.DOCUMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/deleteHistory")
    public Response deleteHistory(@Valid @RequestBody Request<IntegerParams> request) {
        boolean result = createDoc.deleteHistory(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "文档信息列表", tags = TagsConstants.DOCUMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<PageEntity<List<DocumentVo>>> list(@Valid @RequestBody Request<PageParams<SearchDocParams>> request) {
        PageEntity<List<DocumentVo>> result = listDoc.list(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "文档信息列表", tags = TagsConstants.DOCUMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/detail")
    public Response<DocumentDetailVo> detailVoResponse(@Valid @RequestBody Request<IntegerParams> request) {
        DocumentDetailVo result = detailDoc.detail(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "历史文档信息列表", tags = TagsConstants.DOCUMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/history")
    public Response<List<HistoryVo>> history(@Valid @RequestBody Request<IntegerParams> request) {
        List<HistoryVo> result = listDoc.history(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "选项获取接口", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/getOptions")
    public Response<List<OptionClassVo>> getOptions(@Valid @RequestBody Request<List<OptionParams>> request) {
        List<OptionClassVo> vos = getOptionsProcessor.getOptions(request.getData());
        return ResponseUtils.success(vos, request.getTraceId());
    }

}
