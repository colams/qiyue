package com.foxconn.sw.service.controller.account;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.acount.AddressBookParams;
import com.foxconn.sw.data.dto.entity.acount.AddressBookVo;
import com.foxconn.sw.data.dto.entity.universal.StringParams;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/profile")
public class AddressBookController {

    @Operation(summary = "通讯录信息", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<List<AddressBookVo>> list(@Valid @RequestBody Request<AddressBookParams> request) {
        return ResponseUtils.success(null, request.getTraceId());
    }

    @Operation(summary = "关注通讯信息", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/keep")
    public Response keep(@Valid @RequestBody Request<StringParams> request) {
        return ResponseUtils.success(null, request.getTraceId());
    }

    @Operation(summary = "取关通讯信息", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/discard")
    public Response discard(@Valid @RequestBody Request<StringParams> request) {
        return ResponseUtils.success(null, request.getTraceId());
    }

    @Operation(summary = "通讯录信息", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/export")
    public Response export(@Valid @RequestBody Request<AddressBookParams> request) {
        return ResponseUtils.success(null, request.getTraceId());
    }
}
