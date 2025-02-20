package com.foxconn.sw.service.controller.account;

import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.acount.AddressBookParams;
import com.foxconn.sw.data.dto.entity.acount.AddressBookVo;
import com.foxconn.sw.data.dto.entity.acount.AddressGatherParams;
import com.foxconn.sw.data.dto.entity.universal.StringParams;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.acount.GatherAddressBookProcessor;
import com.foxconn.sw.service.processor.acount.ListAddressBookProcessor;
import com.foxconn.sw.service.utils.ExcelAddressBookUtils;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("api/profile")
public class AddressBookController {

    @Autowired
    ListAddressBookProcessor listAddressBookProcessor;
    @Autowired
    GatherAddressBookProcessor gatherAddressBookProcessor;
    @Autowired
    HttpServletResponse response;

    @Permission
    @Operation(summary = "通讯录信息", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<List<AddressBookVo>> list(@Valid @RequestBody Request<AddressBookParams> request) {
        List<AddressBookVo> bookVoList = listAddressBookProcessor.list(request.getHead(), request.getData());
        return ResponseUtils.success(bookVoList, request.getTraceId());
    }

    @Permission
    @Operation(summary = "通讯录信息", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/addressByEno")
    public Response<AddressBookVo> addressByEno(@Valid @RequestBody Request<StringParams> request) {
        AddressBookVo bookVoList = listAddressBookProcessor.list(request.getData().getParams());
        return ResponseUtils.success(bookVoList, request.getTraceId());
    }

    @Operation(summary = "关注通讯信息", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/gather")
    public Response gather(@Valid @RequestBody Request<AddressGatherParams> request) {
        gatherAddressBookProcessor.gather(request.getHead(), request.getData());
        return ResponseUtils.success(null, request.getTraceId());
    }

    @Permission
    @Operation(summary = "導出通讯录信息", tags = TagsConstants.ACCOUNT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/export")
    public ResponseEntity export(@Valid @RequestBody Request<AddressBookParams> request) throws IOException {
        // 设置响应头
        response.setContentType("application/vnd.ms-excel");
        String fileName = DateTimeUtils.getTimeStamp() + ".xlsx";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        List<AddressBookVo> bookVoList = listAddressBookProcessor.list(request.getHead(), request.getData());
        if (CollectionUtils.isEmpty(bookVoList)) {
            return ResponseEntity.ok().body(null);
        }
        // 使用Apache POI生成Excel文件
        Workbook workbook = ExcelAddressBookUtils.generateExcel(bookVoList);

        // 将Excel文件写入响应输出流
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();

        workbook.close();
        return ResponseEntity.ok().body(null);
    }
}
