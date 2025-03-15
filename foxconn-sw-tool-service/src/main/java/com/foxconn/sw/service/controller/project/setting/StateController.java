package com.foxconn.sw.service.controller.project.setting;

import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.state.StateInfoVo;
import com.foxconn.sw.data.dto.request.state.StateAddParams;
import com.foxconn.sw.data.dto.request.state.StateIdParams;
import com.foxconn.sw.service.utils.ResponseUtils;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@Tag(name = "專案管理--階段維護")
@RestController
@RequestMapping("api/pj/doc/state")
public class StateController {
    @Operation(summary = "專案設置-階段維護-更新")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/update")
    public Response<Boolean> saveState(@Valid @RequestBody Request<StateAddParams> request) throws IOException {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "專案設置-階段維護-獲取階段信息")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/info")
    public Response<List<StateInfoVo>> listState(@Valid @RequestBody Request<StateIdParams> request) throws IOException {
        List<StateInfoVo> list = Lists.newArrayList();
        return ResponseUtils.success(list, request.getTraceId());
    }
}
