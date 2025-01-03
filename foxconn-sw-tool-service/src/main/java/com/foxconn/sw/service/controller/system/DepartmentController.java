package com.foxconn.sw.service.controller.system;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.system.DepartmentVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.deparment.DepartmentParams;
import com.foxconn.sw.service.processor.department.GetDepartListProcessor;
import com.foxconn.sw.service.processor.department.UpdateDepartmentProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/department")
public class DepartmentController {

    @Autowired
    GetDepartListProcessor getDepartListProcessor;
    @Autowired
    UpdateDepartmentProcessor updateDepartment;


    @Operation(summary = "所有部门信息树", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/departList")
    public Response<List<DepartmentVo>> getDepartList(@Valid @RequestBody Request request) {
        List<DepartmentVo> departmentVos = getDepartListProcessor.getDepartList();
        return ResponseUtils.success(departmentVos, request.getTraceId());
    }


    @Operation(summary = "更新部门信息", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/update")
    public Response<Boolean> update(@Valid @RequestBody Request<DepartmentParams> request) {
        boolean result = updateDepartment.updateDepartment(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "删除部门信息", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/delete")
    public Response<Boolean> delete(@Valid @RequestBody Request<IntegerParams> request) {
        Boolean result = updateDepartment.deleteDepartment(request.getData().getParams());
        return ResponseUtils.success(result, request.getTraceId());
    }

}
