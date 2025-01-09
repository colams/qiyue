package com.foxconn.sw.service.controller.system;

import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.ChangeLogVo;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.system.ChangeLogProcessor;
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
@RequestMapping("api/changelog")
public class ChangeLogController {

    @Autowired
    ChangeLogProcessor changeLogProcessor;

    @Permission
    @Operation(summary = "所有更新信息列表", tags = "changeLog")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<List<ChangeLogVo>> list(@Valid @RequestBody Request request) {
        List<ChangeLogVo> departmentVos = changeLogProcessor.list();
        return ResponseUtils.success(departmentVos, request.getTraceId());
    }


}
