package com.foxconn.sw.service.controller.system;


import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.system.BasicPropertyVo;
import com.foxconn.sw.data.dto.entity.system.PropertiesParams;
import com.foxconn.sw.data.dto.entity.system.PropertyVo;
import com.foxconn.sw.service.processor.system.GetPropertiesProcessor;
import com.foxconn.sw.service.processor.system.PropertyCategoryProcessor;
import com.foxconn.sw.service.processor.system.SavePropertyProcessor;
import com.foxconn.sw.service.processor.universal.PropertyProcessor;
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
@RequestMapping("api/property")
public class PropertyController {

    private final Logger LOGGER = LoggerFactory.getLogger(PropertyController.class);

    @Autowired
    GetPropertiesProcessor getPropertiesProcessor;
    @Autowired
    SavePropertyProcessor savePropertyProcessor;
    @Autowired
    PropertyCategoryProcessor categoryProcessor;
    @Autowired
    PropertyProcessor propertyProcessor;

    @Operation(summary = "获取基础属性数据信息", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/getPropertiesPage")
    public Response<PageEntity<PropertyVo>> getPropertiesPage(@Valid @RequestBody Request<PageParams<PropertiesParams>> request) {
        PageEntity<PropertyVo> propertyVos = getPropertiesProcessor.getSwPropertiesPage(request.getData());
        return ResponseUtils.success(propertyVos, request.getTraceId());
    }

    @Operation(summary = "获取基础属性数据信息", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/getProperties")
    public Response<List<PropertyVo>> getProperties(@Valid @RequestBody Request<PropertiesParams> request) {
        List<PropertyVo> propertyVos = getPropertiesProcessor.getSwProperties(request.getData());
        return ResponseUtils.success(propertyVos, request.getTraceId());
    }

    @Operation(summary = "保存基础数据信息", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/save")
    public Response<Boolean> saveProperty(@Valid @RequestBody Request<PropertyVo> request) {
        boolean result = savePropertyProcessor.saveProperty(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "基础属性分类", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/basicProperties")
    public Response<List<BasicPropertyVo>> basicProperties(@Valid @RequestBody Request request) {
        List<BasicPropertyVo> result = propertyProcessor.properties(request.getHead());
        return ResponseUtils.success(result, request.getTraceId());
    }
}
