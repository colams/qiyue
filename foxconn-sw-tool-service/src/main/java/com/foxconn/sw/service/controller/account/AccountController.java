package com.foxconn.sw.service.controller.account;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.acount.LoginParams;
import com.foxconn.sw.data.dto.entity.acount.LoginStateVo;
import com.foxconn.sw.data.dto.entity.acount.UserBriefParams;
import com.foxconn.sw.service.processor.acount.LoginProcessor;
import com.foxconn.sw.service.processor.acount.RegisterProcessor;
import com.foxconn.sw.service.processor.acount.ResetPwdProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/account")
public class AccountController {

    @Autowired
    LoginProcessor loginProcessor;
    @Autowired
    RegisterProcessor registerProcessor;
    @Autowired
    ResetPwdProcessor resetPwdProcessor;


    @Operation(summary = "注册账号", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/register")
    public Response<LoginStateVo> register(@Valid @RequestBody Request<UserBriefParams> request) {
        LoginStateVo result = registerProcessor.register(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "重置密码", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/resetPwd")
    public Response<LoginStateVo> resetPwd(@Valid @RequestBody Request<UserBriefParams> request) {
        LoginStateVo result = resetPwdProcessor.resetPwd(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "登录", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/login")
    public Response login(@Valid @RequestBody Request<LoginParams> request) {
        LoginStateVo result = loginProcessor.login(request.getData());
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
    public Response login3(@Valid @RequestBody Request<LoginParams> request) {
        LoginStateVo result = loginProcessor.login(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

}
