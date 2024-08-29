package com.foxconn.sw.service.controller.account;

import com.foxconn.sw.business.account.MenuBusiness;
import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.acount.MenuBriefVo;
import com.foxconn.sw.data.dto.entity.acount.MenuParams;
import com.foxconn.sw.data.dto.entity.acount.MenuParams2;
import com.foxconn.sw.data.dto.entity.acount.MenuVo;
import com.foxconn.sw.data.dto.entity.system.ModuleVo;
import com.foxconn.sw.service.processor.acount.ListMenuProcessor;
import com.foxconn.sw.service.processor.acount.ModuleIndexProcessor;
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
@RequestMapping("api/menu")
public class MenuController {
    private final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    MenuBusiness menuBusiness;
    @Autowired
    ListMenuProcessor listMenuProcessor;
    @Autowired
    ModuleIndexProcessor moduleIndexProcessor;

    @Operation(summary = "获取页面路由信息", tags = TagsConstants.MENU)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response list(@Valid @RequestBody Request<MenuParams> request) {
        List<MenuBriefVo> sysMenus = listMenuProcessor.list(request.getData());
        return ResponseUtils.success(sysMenus, request.getTraceId());
    }

    @Operation(summary = "获取授权的菜单列表", tags = TagsConstants.MENU)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/authList")
    public Response authList(@Valid @RequestBody Request<MenuParams> request) {
        System.out.println("authList list");
        List<MenuBriefVo> sysMenus = menuBusiness.authList(request.getData(), request.getHead());
        Response response = ResponseUtils.success(sysMenus, request.getTraceId());
        return response;
    }

    @Operation(summary = "获取所有模块页模块首页信息", tags = TagsConstants.MENU)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/moduleIndex")
    public Response<List<ModuleVo>> moduleIndex(@Valid @RequestBody Request request) {
        List<ModuleVo> propertyVos = moduleIndexProcessor.list();
        return ResponseUtils.success(propertyVos, request.getTraceId());
    }

    @Operation(summary = "获取所有菜单信息", tags = TagsConstants.MENU)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/searchMenus")
    public Response<List<MenuVo>> searchMenus(@Valid @RequestBody Request<MenuParams2> request) {
        List<MenuVo> propertyVos = listMenuProcessor.searchMenus(request.getData());
        return ResponseUtils.success(propertyVos, request.getTraceId());
    }
}
