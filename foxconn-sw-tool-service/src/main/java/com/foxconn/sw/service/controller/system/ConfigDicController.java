package com.foxconn.sw.service.controller.system;

import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.request.config.ConfigDicParams;
import com.foxconn.sw.data.dto.request.config.ListParams;
import com.foxconn.sw.data.entity.SwConfigDic;
import com.foxconn.sw.service.processor.system.ConfigDicProcessor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/config")
public class ConfigDicController {

    @Autowired
    ConfigDicProcessor configDicProcessor;


    @Operation(summary = "获取所有配置信息", tags = "config")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public ResponseEntity list(@RequestBody Request<ListParams> request) {
        List<SwConfigDic> dicList = configDicProcessor.getConfigDicList(request.getData());
        return ResponseEntity.ok(dicList);
    }

    @Operation(summary = "保存配置信息", tags = "config")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Request<ConfigDicParams> request) {
        Boolean result = configDicProcessor.save(request.getData());
        return ResponseEntity.ok(result);
    }
}
