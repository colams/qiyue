package com.foxconn.sw.service.controller.system;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.system.ModuleVo;
import com.foxconn.sw.service.processor.system.ListModuleProcessor;
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
@RequestMapping("api/system/module")
public class ModuleController {

    private final Logger LOGGER = LoggerFactory.getLogger(ModuleController.class);

    @Autowired
    ListModuleProcessor listModuleProcessor;


    @Operation(summary = "基础数据信息", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<List<ModuleVo>> getProperties(@Valid @RequestBody Request request) {
        List<ModuleVo> propertyVos = listModuleProcessor.list();
        return ResponseUtils.success(propertyVos, request.getTraceId());
    }


}
