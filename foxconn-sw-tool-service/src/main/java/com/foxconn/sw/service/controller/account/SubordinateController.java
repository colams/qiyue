package com.foxconn.sw.service.controller.account;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.request.account.CreateAccountParams;
import com.foxconn.sw.data.dto.request.account.SetLeaveParams;
import com.foxconn.sw.data.dto.response.user.SubordinateVo;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.acount.EmployeeManagerProcessor;
import com.foxconn.sw.service.processor.acount.SubordinateProcessor;
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
@RequestMapping("api/account")
public class SubordinateController {

    @Autowired
    SubordinateProcessor subordinateProcessor;
    @Autowired
    EmployeeManagerProcessor employeeManagerProcessor;

    @Permission
    @Operation(summary = "获取下属员工", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/subordinate")
    public Response<List<SubordinateVo>> subordinate(@Valid @RequestBody Request request) {
        List<SubordinateVo> vos = subordinateProcessor.subordinateList();
        return ResponseUtils.success(vos, request.getTraceId());
    }

    @Operation(summary = "新增员工信息", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/create")
    public Response<Boolean> create(@Valid @RequestBody Request<CreateAccountParams> request) {
        Boolean result = employeeManagerProcessor.create(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "新增员工信息", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/leave")
    public Response<Boolean> setLeave(@Valid @RequestBody Request<SetLeaveParams> request) {
        Boolean result = employeeManagerProcessor.setLeave(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }
}
