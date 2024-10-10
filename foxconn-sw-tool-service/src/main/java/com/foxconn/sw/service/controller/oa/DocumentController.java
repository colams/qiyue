package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.document.DocumentVo;
import com.foxconn.sw.data.dto.entity.document.HistoryVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.document.CreateDocParams;
import com.foxconn.sw.data.dto.request.document.DeleteDocParams;
import com.foxconn.sw.data.dto.request.document.SearchDocParams;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.document.CreateDocProcessor;
import com.foxconn.sw.service.processor.document.ListDocProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/document")
public class DocumentController {

    @Autowired
    CreateDocProcessor createDoc;
    @Autowired
    ListDocProcessor listDoc;

    @Permission
    @Operation(summary = "新增文档信息", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/create")
    public Response create(@Valid @RequestBody Request<CreateDocParams> request) {
        boolean result = createDoc.create(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "更新文档版本", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/revise")
    public Response revise(@Valid @RequestBody Request<CreateDocParams> request) {
        boolean result = createDoc.create(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "删除文档信息", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/delete")
    public Response delete(@Valid @RequestBody Request<DeleteDocParams> request) {
        boolean result = createDoc.delete(request.getData());
        return ResponseUtils.success(request.getTraceId());
    }

    @Permission
    @Operation(summary = "删除文档版本信息", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/deleteHistory")
    public Response deleteHistory(@Valid @RequestBody Request<IntegerParams> request) {
        boolean result = createDoc.deleteHistory(request.getData());
        return ResponseUtils.success(request.getTraceId());
    }

    @Permission
    @Operation(summary = "新增文档信息", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<List<DocumentVo>> list(@Valid @RequestBody Request<SearchDocParams> request) {
        List<DocumentVo> result = listDoc.list(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "新增文档信息", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/history")
    public Response<List<HistoryVo>> history(@Valid @RequestBody Request<IntegerParams> request) {
        List<HistoryVo> result = listDoc.history(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }
}
