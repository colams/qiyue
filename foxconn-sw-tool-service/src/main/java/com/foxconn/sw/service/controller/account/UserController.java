package com.foxconn.sw.service.controller.account;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.acount.EmployeeParams;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.acount.UserParams;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.acount.QueryUsersProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("api/account")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserBusiness userBusiness;
    @Autowired
    QueryUsersProcessor queryUsersProcessor;
    @Autowired
    EmployeeBusiness employeeBusiness;

    @Operation(summary = "用户信息列表", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<List<UserInfo>> userList(@Valid @RequestBody Request<UserParams> request) {
        List<UserInfo> userList = queryUsersProcessor.queryUsers(request.getData());
        return ResponseUtils.success(userList, request.getTraceId());
    }

    @Operation(summary = "查询员工信息", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/queryEmployee")
    public Response<List<EmployeeVo>> queryEmployee(@Valid @RequestBody Request<EmployeeParams> request) {
        List<EmployeeVo> employeeVos = userBusiness.queryEmployees(request.getData());
        return ResponseUtils.success(employeeVos, request.getTraceId());
    }

    @Permission
    @Operation(summary = "获取主管下级所有成员", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/queryMembers")
    public Response<List<EmployeeVo>> queryMembers(@Valid @RequestBody Request request) {
        List<SwEmployee> employeeVos = employeeBusiness.queryMembers(RequestContext.getEmployeeNo());
        List<EmployeeVo> vos = employeeVos.stream().map(e -> {
            EmployeeVo vo = new EmployeeVo();
            vo.setName(e.getName());
            vo.setEmployeeNo(e.getEmployeeNo());
            return vo;
        }).collect(Collectors.toList());

        return ResponseUtils.success(vos, request.getTraceId());
    }
}
