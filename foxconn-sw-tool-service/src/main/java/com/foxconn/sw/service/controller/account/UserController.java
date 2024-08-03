package com.foxconn.sw.service.controller.account;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.acount.LoginParams;
import com.foxconn.sw.data.dto.entity.acount.LoginResponseType;
import com.foxconn.sw.data.dto.entity.acount.UserDTO;
import com.foxconn.sw.data.dto.entity.common.IDParams;
import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.service.processor.acount.LoginProcessor;
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
    LoginProcessor loginProcessor;

    @Operation(summary = "注册账号", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/register")
    public Response register(@Valid Request<UserDTO> request) {
        System.out.println("register user");
        SwUser sysUser = userBusiness.save(request.getData());
        Response response = ResponseUtils.success(sysUser, request.getTraceId());
        return response;
    }

    @Operation(summary = "重置密码", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/resetPwd")
    public Response resetPwd(Request<UserDTO> request) {
        System.out.println("reset password user");
        SwUser sysUser = userBusiness.save(request.getData());
        Response response = ResponseUtils.success(sysUser, request.getTraceId());
        return response;
    }

    @Operation(summary = "登录", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/login")
    public Response login(@Valid @RequestBody Request<LoginParams> request) {
        LoginResponseType result = loginProcessor.login(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    /**
     * todo 登录验证 修改validate 待测试 自定义参数验证
     *
     * @param request
     * @return
     */
    @Operation(summary = "登录--登录验证 修改validate 待测试 自定义参数验证", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/login3")
    public Response login3(@RequestBody Request<LoginParams> request) {
        LoginResponseType result = loginProcessor.login(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "用户信息列表", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response userList(Request request) {
        List<UserDTO> userList = userBusiness.list();
        return ResponseUtils.success(userList, request.getTraceId());
    }

    @Operation(summary = "用户详情", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/info")
    public Response userDetail(Request<IDParams> request) {
        System.out.println("user list");
        UserDTO userDTO = userBusiness.detail(request.getData().getId());
        Response response = ResponseUtils.success(userDTO, request.getTraceId());
        return response;
    }


}
