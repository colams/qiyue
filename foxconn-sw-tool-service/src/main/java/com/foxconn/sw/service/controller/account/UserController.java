package com.foxconn.sw.service.controller.account;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.acount.UserParams;
import com.foxconn.sw.data.dto.entity.common.StringParams;
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

@CrossOrigin
@RestController
@RequestMapping("api/account")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserBusiness userBusiness;
    @Autowired
    QueryUsersProcessor queryUsersProcessor;

    @Operation(summary = "用户信息列表", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<List<UserInfo>> userList(@Valid @RequestBody Request<UserParams> request) {
        List<UserInfo> userList = queryUsersProcessor.queryUsers(request.getData());
        return ResponseUtils.success(userList, request.getTraceId());
    }

    @Operation(summary = "用户详情", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/user")
    public Response<UserInfo> queryByEmployeeNo(@Valid @RequestBody Request<StringParams> request) {
        UserInfo userInfo = userBusiness.queryUserInfo(request.getData().getParams());
        return ResponseUtils.success(userInfo, request.getTraceId());
    }
}
