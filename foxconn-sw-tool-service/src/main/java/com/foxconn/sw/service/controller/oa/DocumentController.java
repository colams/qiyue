package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.request.document.CreateDocParams;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.document.CreateDocProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/document")
public class DocumentController {

    @Autowired
    CreateDocProcessor createDoc;

    @Permission
    @Operation(summary = "新增文档信息", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/create")
    public Response create(@Valid @RequestBody Request<CreateDocParams> request) {
        boolean result = createDoc.create(request.getData());
        return ResponseUtils.success(request.getTraceId());
    }

}
