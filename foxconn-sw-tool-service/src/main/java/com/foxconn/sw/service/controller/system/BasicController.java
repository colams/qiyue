package com.foxconn.sw.service.controller.system;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.system.DepartmentVo;
import com.foxconn.sw.data.dto.entity.universal.OptionsVo;
import com.foxconn.sw.data.dto.response.basic.DepartAndEmployeeOptionVo;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.department.GetDepartListProcessor;
import com.foxconn.sw.service.processor.system.MeetingRoomProcessor;
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
    MeetingRoomProcessor meetingRoom;
    @Autowired
    GetDepartListProcessor getDepartListProcessor;

    @Operation(summary = "所有部门信息树", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/departList")
    public Response<List<DepartmentVo>> getDepartList(@Valid @RequestBody Request request) {
        List<DepartmentVo> departmentVos = getDepartListProcessor.getDepartList();
        return ResponseUtils.success(departmentVos, request.getTraceId());
    }

    @Permission
    @Operation(summary = "获取下属部门信息", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/subDepts")
    public Response<List<DepartmentVo>> subDepts(@Valid @RequestBody Request request) {
        List<DepartmentVo> departmentVos = getDepartListProcessor.subDepts();
        return ResponseUtils.success(departmentVos, request.getTraceId());
    }

    @Permission
    @Operation(summary = "获取会议室信息", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/rooms")
    public Response<List<OptionsVo>> meetRoom(@Valid @RequestBody Request request) {
        List<OptionsVo> departmentVos = meetingRoom.rooms();
        return ResponseUtils.success(departmentVos, request.getTraceId());
    }

    @Permission
    @Operation(summary = "部门员工选项接口", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/selectDepartAndEmployee")
    public Response<DepartAndEmployeeOptionVo> selectDepartAndEmployee(@Valid @RequestBody Request request) {
        DepartAndEmployeeOptionVo departmentVos = null;
        return ResponseUtils.success(departmentVos, request.getTraceId());
    }
}
