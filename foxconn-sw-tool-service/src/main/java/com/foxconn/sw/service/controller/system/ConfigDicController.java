package com.foxconn.sw.service.controller.system;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.request.config.ListParams;
import com.foxconn.sw.data.entity.SwConfigDic;
import com.foxconn.sw.service.processor.system.ConfigDicProcessor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/config")
public class ConfigDicController {

    @Autowired
    ConfigDicProcessor configDicProcessor;


    @Operation(summary = "所有部门信息树", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public ResponseEntity list(ListParams listParams) {
        List<SwConfigDic> dicList = configDicProcessor.getConfigDicList(listParams);
        return ResponseEntity.ok(dicList);
    }

}
