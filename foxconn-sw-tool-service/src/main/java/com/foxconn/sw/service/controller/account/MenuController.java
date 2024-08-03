package com.foxconn.sw.service.controller.account;

import com.foxconn.sw.business.account.MenuBusiness;
import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.acount.MenuDTO;
import com.foxconn.sw.data.dto.entity.acount.MenuParams;
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

    @Operation(summary = "获取页面路由信息", tags = TagsConstants.MENU)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response list(@Valid @RequestBody Request<MenuParams> request) {
        List<MenuDTO> sysMenus = menuBusiness.list(request.getData());
        Response response = ResponseUtils.success(sysMenus, request.getTraceId());
        return response;
    }

    @Operation(summary = "获取授权的菜单列表", tags = TagsConstants.MENU)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/authList")
    public Response authList(@Valid @RequestBody Request<MenuParams> request) {
        System.out.println("authList list");
        List<MenuDTO> sysMenus = menuBusiness.authList(request.getData(), request.getHead());
        Response response = ResponseUtils.success(sysMenus, request.getTraceId());
        return response;
    }


}
