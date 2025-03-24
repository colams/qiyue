package com.foxconn.sw.service.controller.project.mp;

import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.project.HandoverHistoryVo;
import com.foxconn.sw.data.dto.entity.project.HandoverVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.project.mp.HandoverSettingSaveParams;
import com.foxconn.sw.data.dto.request.project.mp.HandoverTypeParams;
import com.foxconn.sw.data.dto.request.project.mp.HandoverUpdateParams;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.project.mp.MpHandoverProcessor;
import com.foxconn.sw.service.processor.universal.UploadProcessor;
import com.foxconn.sw.service.utils.ExcelHandoverUtils;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Tag(name = "專案設置-交接維護")
@RestController
@RequestMapping("api/pj/mp")
public class MpHandoverController {
    @Autowired
    private MpHandoverProcessor mpHandoverProcessor;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private UploadProcessor uploadProcessor;
//    @Permission
//    @Operation(summary = "保存")
//    @ApiResponse(responseCode = "0", description = "成功码")
//    @PostMapping("/save")
//    public Response<Boolean> saveMpHandover(@Valid @RequestBody Request<HandoverSettingParams> request) {
//        Boolean result = mpHandoverProcessor.save(request.getData());
//        return ResponseUtils.success(result, request.getTraceId());
//    }
    @Permission
    @Operation(summary = "保存")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/save")
    public Response<Boolean> saveMpHandover(@Valid @RequestBody Request<HandoverSettingSaveParams> request) {
        Boolean result = mpHandoverProcessor.save(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "編輯")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/update")
    public Response<Boolean> updateMpHandover(@Valid @RequestBody Request<HandoverUpdateParams> request) {
        Boolean result = mpHandoverProcessor.update(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "列表")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<List<HandoverVo>> listMpHandover(@Valid @RequestBody Request<HandoverTypeParams> request) {
        List<HandoverVo> result = mpHandoverProcessor.list(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "導入")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/import")
    public Response<Boolean> importExcel(@Valid @RequestParam("request") String reqJson, @RequestParam("multipartFile")
    MultipartFile multipartFile) throws IOException {
        Request<HandoverTypeParams> request = JsonUtils.deserialize(reqJson, Request.class, HandoverTypeParams.class);
        InputStream inputStream = multipartFile.getInputStream();
        //TODO:上傳文件
        Boolean result = mpHandoverProcessor.processExcelFile(request.getData(),inputStream,multipartFile.getOriginalFilename(),null);
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "导出")
    @ApiResponse(responseCode = "0", description = "成功码")
    @CrossOrigin(exposedHeaders = {"Content-Disposition"})
    @PostMapping("/export")
    public ResponseEntity export(@Valid @RequestBody Request<HandoverTypeParams> request) throws IOException {
        // 设置响应头
        response.setContentType("application/vnd.ms-excel");
        String fileName = DateTimeUtils.getTimeStamp() + ".xlsx";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        List<HandoverVo> handoverVoList = mpHandoverProcessor.list(request.getData());
        if (CollectionUtils.isEmpty(handoverVoList)) {
            return ResponseEntity.ok().body(null);
        }
        // 使用Apache POI生成Excel文件
        Workbook workbook = ExcelHandoverUtils.generateExcel(handoverVoList);

        // 将Excel文件写入响应输出流
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();

        workbook.close();
        return ResponseEntity.ok().body(null);
    }

    @Operation(summary = "更新歷史")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/history-list")
    public Response<List<HandoverHistoryVo>> listHistory(@Valid @RequestBody Request<HandoverTypeParams> request) {
        List<HandoverHistoryVo> result = mpHandoverProcessor.listHistory(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "刪除導入歷史")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/history-delete")
    public Response<Boolean> deleteHistory(@Valid @RequestBody Request<IntegerParams> request) {
        Boolean result = mpHandoverProcessor.deleteHistory(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }
}
