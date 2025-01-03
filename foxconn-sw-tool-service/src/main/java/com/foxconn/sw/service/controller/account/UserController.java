package com.foxconn.sw.service.controller.account;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.acount.EmployeeParams;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.acount.UserParams;
import com.foxconn.sw.data.dto.entity.universal.StringParams;
import com.foxconn.sw.data.dto.request.account.QuerySubEmpParams;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.acount.QueryMemberProcessor;
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
    @Autowired
    QueryMemberProcessor queryMemberProcessor;

    @Operation(summary = "用户信息列表", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<List<UserInfo>> userList(@Valid @RequestBody Request<UserParams> request) {
        List<UserInfo> userList = queryUsersProcessor.queryUsers(request.getData());
        return ResponseUtils.success(userList, request.getTraceId());
    }

    @Operation(summary = "用户信息", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/userInfo")
    public Response<UserInfo> userInfo(@Valid @RequestBody Request<StringParams> request) {
        UserInfo userInfo = queryUsersProcessor.queryUsers(request.getData());
        return ResponseUtils.success(userInfo, request.getTraceId());
    }

    @Operation(summary = "查询员工信息", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/queryEmployee")
    public Response<List<EmployeeVo>> queryEmployee(@Valid @RequestBody Request<EmployeeParams> request) {
        List<EmployeeVo> employeeVos = userBusiness.queryEmployees(request.getData());
        return ResponseUtils.success(employeeVos, request.getTraceId());
    }

    @Permission
    @Operation(summary = "获取主管下属员工", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/queryMembers")
    public Response<List<EmployeeVo>> queryMembers(@Valid @RequestBody Request<QuerySubEmpParams> request) {
        List<EmployeeVo> vos = queryMemberProcessor.queryMembers(request.getData());
        return ResponseUtils.success(vos, request.getTraceId());
    }
}
