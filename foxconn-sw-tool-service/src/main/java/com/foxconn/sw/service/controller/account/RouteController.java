package com.foxconn.sw.service.controller.account;

import com.foxconn.sw.business.account.MenuBusiness;
import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.acount.MenuDTO;
import com.foxconn.sw.data.dto.entity.acount.RouteParams;
import com.foxconn.sw.service.processor.acount.ListRouteProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("api/route")
public class RouteController {

    @Autowired
    MenuBusiness menuBusiness;
    @Autowired
    ListRouteProcessor listRouteProcessor ;

    @Operation(summary = "保存路由信息", tags = TagsConstants.ROUTE)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/save")
    public Response save(@RequestBody Request<MenuDTO> request) {
        System.out.println("menu save");
        int result = menuBusiness.save(request.getData());
        Response response = ResponseUtils.success(result, request.getTraceId());
        return response;
    }

    @Operation(summary = "获取路由信息", tags = TagsConstants.ROUTE)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response list(@RequestBody Request<RouteParams> request) {
        List<MenuDTO> sysMenus = listRouteProcessor.list(request.getData());
        Response response = ResponseUtils.success(sysMenus, request.getTraceId());
        return response;
    }

}
