package com.foxconn.sw.service.controller.account;

import com.foxconn.sw.common.utils.UUIDUtils;
import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.constants.enums.retcode.RetCode;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.acount.LoginParams;
import com.foxconn.sw.data.dto.entity.acount.UpdatePwdParams;
import com.foxconn.sw.data.dto.entity.acount.UserProfileBrief;
import com.foxconn.sw.data.dto.entity.acount.UserProfileVo;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.acount.*;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("api/profile")
public class ProfileController {

    @Autowired
    ValidatePwdProcessor validatePwdProcessor;
    @Autowired
    UpdatePwdProcessor updatePwdProcessor;
    @Autowired
    ProfileProcessor profileProcessor;
    @Autowired
    UpdateProfileProcessor updateProfileProcessor;
    @Autowired
    AvatarUploadProcessor avatarUpload;


    @Operation(summary = "验证密码是否正确", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/validatePwd")
    public Response validatePwd(@Valid @RequestBody Request<LoginParams> request) {
        validatePwdProcessor.validatePwd(request.getData());
        return ResponseUtils.success(request.getTraceId());
    }

    @Operation(summary = "更新密码接口", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/updatePwd")
    public Response updatePwd(@Valid @RequestBody Request<UpdatePwdParams> request) {
        updatePwdProcessor.updatePwd(request.getData());
        return ResponseUtils.success(request.getTraceId());
    }

    @Operation(summary = "获取用户详情", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/profile")
    public Response<UserProfileVo> profile(@Valid @RequestBody Request request) {
        UserProfileVo profileVo = profileProcessor.profile(request.getHead());
        return ResponseUtils.success(profileVo, request.getTraceId());
    }

    @Operation(summary = "更新用户详情", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/update")
    public Response updateProfile(@Valid @RequestBody Request<UserProfileBrief> request) {
        updateProfileProcessor.updateProfile(request.getHead().getToken(), request.getData());
        return ResponseUtils.success(request.getTraceId());
    }

    @Permission
    @Operation(summary = "更新用户详情", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/avatar")
    public Response<String> updateAvatar(@RequestParam("request") String request, @RequestParam("avatar") MultipartFile avatar) throws FileNotFoundException {
        String avatarUrl = avatarUpload.updateAvatar(avatar);
        return ResponseUtils.response(avatarUrl, RetCode.SUCCESS, UUIDUtils.getUuid());
    }
}
