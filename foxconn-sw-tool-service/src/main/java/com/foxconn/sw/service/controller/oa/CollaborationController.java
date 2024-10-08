package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.collaboration.CollaborationVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.collaboration.CollaborationDetailProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/collaboration")
public class CollaborationController {

    @Autowired
    CollaborationDetailProcessor collaborationDetail;

    @Permission
    @Operation(summary = "协作平台信息", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/detail")
    public Response<CollaborationVo> detail(@Valid @RequestBody Request<IntegerParams> request) {
        CollaborationVo collaborationVo = collaborationDetail.detail(request.getData());
        return ResponseUtils.success(collaborationVo, request.getTraceId());
    }

}
