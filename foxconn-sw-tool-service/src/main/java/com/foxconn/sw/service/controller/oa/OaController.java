package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.oa.OAOptionVo;
import com.foxconn.sw.data.dto.entity.universal.StringParams;
import com.foxconn.sw.service.processor.AssignEmployeesProcessor;
import com.foxconn.sw.service.processor.universal.OptionListProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/oa/basic")
public class OaController {

    @Autowired
    OptionListProcessor optionListProcessor;
    @Autowired
    AssignEmployeesProcessor assignEmployeesProcessor;

    @Operation(summary = "OA 分类信息", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/oaOptionList")
    public Response<OAOptionVo> oaOptionList(@Valid @RequestBody Request<StringParams> request) {
        OAOptionVo result = optionListProcessor.getOptions(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "OA 分类信息", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/assignEmployees")
    public Response<List<EmployeeVo>> assignEmployees(@Valid @RequestBody Request request) {
        List<EmployeeVo> vos = assignEmployeesProcessor.assignEmployees();
        return ResponseUtils.success(vos, request.getTraceId());
    }

    @Operation(summary = "OA 分类信息", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/assignByType")
    public Response<List<EmployeeVo>> assignByType(@Valid @RequestBody Request<StringParams> request) {
        List<EmployeeVo> vos = assignEmployeesProcessor.assignByType(request.getData());
        return ResponseUtils.success(vos, request.getTraceId());
    }

}
