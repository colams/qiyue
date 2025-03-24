package com.foxconn.sw.service.controller.project.mp;

import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.project.HandoverHistoryVo;
import com.foxconn.sw.data.dto.entity.project.HandoverVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.project.mp.HandoverSettingSaveParams;
import com.foxconn.sw.data.dto.request.project.mp.HandoverTypeParams;
import com.foxconn.sw.data.dto.request.project.mp.HandoverUpdateParams;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "專案設置-交接維護")
@RestController
@RequestMapping("api/pj/mp")
public class MpHandoverController {
    @Operation(summary = "保存")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/save")
    public Response<Boolean> saveMpHandover(@Valid @RequestBody Request<HandoverSettingSaveParams> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "編輯")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/update")
    public Response<Boolean> updateMpHandover(@Valid @RequestBody Request<HandoverUpdateParams> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "列表")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<List<HandoverVo>> listMpHandover(@Valid @RequestBody Request<HandoverTypeParams> request) {
        List<HandoverVo> result = new ArrayList<>();
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
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "导出")
    @ApiResponse(responseCode = "0", description = "成功码")
    @CrossOrigin(exposedHeaders = {"Content-Disposition"})
    @PostMapping("/export")
    public ResponseEntity export(@Valid @RequestBody Request<HandoverTypeParams> request) throws IOException {

        return ResponseEntity.ok().body(null);
    }

    @Operation(summary = "更新歷史")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/history-list")
    public Response<List<HandoverHistoryVo>> listHistory(@Valid @RequestBody Request<HandoverTypeParams> request) {
        List<HandoverHistoryVo> result = new ArrayList<>();
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "刪除導入歷史")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/history-delete")
    public Response<Boolean> deleteHistory(@Valid @RequestBody Request<IntegerParams> request) {
        Boolean result = false;
        return ResponseUtils.success(result, request.getTraceId());
    }
}
