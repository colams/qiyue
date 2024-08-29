package com.foxconn.sw.service.controller.system;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.system.DepartmentVo;
import com.foxconn.sw.data.dto.entity.system.PostVo;
import com.foxconn.sw.service.processor.system.GetDepartListProcessor;
import com.foxconn.sw.service.processor.system.GetPostListProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/basic")
public class BasicController {


    @Autowired
    GetDepartListProcessor getDepartListProcessor;
    @Autowired
    GetPostListProcessor getPostListProcessor;

    @Operation(summary = "部门信息树", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/departList")
    public Response<List<DepartmentVo>> getDepartList(@Valid @RequestBody Request request) {
        List<DepartmentVo> departmentVos = getDepartListProcessor.getDepartList();
        return ResponseUtils.success(departmentVos, request.getTraceId());
    }


    @Operation(summary = "职位信息", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/postList")
    public Response<List<PostVo>> getPostList(@Valid @RequestBody Request request) {
        List<PostVo> postVos = getPostListProcessor.getPostList();
        return ResponseUtils.success(postVos, request.getTraceId());
    }




}
