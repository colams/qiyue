package com.foxconn.sw.service.controller.project.mp;

import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.project.mp.HandoverListVo;
import com.foxconn.sw.data.dto.entity.state.StateHandoverVo;
import com.foxconn.sw.data.dto.request.project.mp.HandoverSearchParams;
import com.foxconn.sw.data.dto.request.state.StateIdParams;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "量產交接")
@RestController
@RequestMapping("api/pj/hd")
public class MpController {
    @Operation(summary = "發起交接")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/save")
    public Response<Boolean> saveMpHandover(@Valid @RequestBody Request<HandoverSearchParams> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "階段信息")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/info")
    public Response<List<StateHandoverVo>> listState(@Valid @RequestBody Request<StateIdParams> request) throws IOException {
        List<StateHandoverVo> list = new ArrayList<>();
        return ResponseUtils.success(list, request.getTraceId());
    }

    @Operation(summary = "交接文件列表")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<List<HandoverListVo>> listHandover(@Valid @RequestBody Request<HandoverSearchParams> request) throws IOException {
        List<HandoverListVo> list = new ArrayList<>();
        return ResponseUtils.success(list, request.getTraceId());
    }
}
