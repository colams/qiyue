package com.foxconn.sw.service.controller.system;


import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.system.BasicPropertyVo;
import com.foxconn.sw.data.dto.entity.system.PropertyParams;
import com.foxconn.sw.data.dto.entity.system.PropertyVo;
import com.foxconn.sw.service.processor.system.GetPropertiesProcessor;
import com.foxconn.sw.service.processor.system.PropertyCategoryProcessor;
import com.foxconn.sw.service.processor.system.SavePropertyProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/system/property")
public class PropertyController {

    private final Logger LOGGER = LoggerFactory.getLogger(PropertyController.class);

    @Autowired
    GetPropertiesProcessor getPropertiesProcessor;
    @Autowired
    SavePropertyProcessor savePropertyProcessor;
    @Autowired
    PropertyCategoryProcessor categoryProcessor;

    @Operation(summary = "基础数据信息", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<PageEntity<PropertyVo>> getProperties(@RequestBody Request<PageParams<PropertyParams>> request) {
        PageEntity<PropertyVo> propertyVos = getPropertiesProcessor.getSwProperties(request.getData());
        return ResponseUtils.success(propertyVos, request.getTraceId());
    }

    @Operation(summary = "保存基础数据信息", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/save")
    public Response<Boolean> saveProperty(@RequestBody Request<PropertyVo> request) {
        boolean result = savePropertyProcessor.saveProperty(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "基础属性分类", tags = TagsConstants.SYSTEM)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/category")
    public Response<Boolean> propertyCategory(@RequestBody Request request) {
        List<BasicPropertyVo> result = categoryProcessor.propertyCategory();
        return ResponseUtils.success(result, request.getTraceId());
    }


}
