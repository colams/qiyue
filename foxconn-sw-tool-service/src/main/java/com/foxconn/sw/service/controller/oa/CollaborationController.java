package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.collaboration.CollaborationVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationDetailParams;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationEvaluationParams;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationUpdateParams;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.collaboration.CollaborationDetailProcessor;
import com.foxconn.sw.service.processor.collaboration.CollaborationImportProcessor;
import com.foxconn.sw.service.processor.collaboration.CollaborationUpdateProcessor;
import com.foxconn.sw.service.utils.ExcelCollaborationUtils;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@CrossOrigin
@RestController
@RequestMapping("api/collaboration")
public class CollaborationController {

    @Autowired
    CollaborationDetailProcessor collaborationDetail;
    @Autowired
    CollaborationUpdateProcessor collaborationUpdate;
    @Autowired
    CollaborationImportProcessor collaborationImport;
    @Autowired
    HttpServletResponse response;

    @Permission
    @Operation(summary = "协作平台-獲取協作工作內容", tags = TagsConstants.COLLABORATION)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/detail")
    public Response<CollaborationVo> detail(@Valid @RequestBody Request<CollaborationDetailParams> request) throws FileNotFoundException {
        CollaborationVo collaborationVo = collaborationDetail.detail(request.getData());
        return ResponseUtils.success(collaborationVo, request.getTraceId());
    }

    @Permission
    @Operation(summary = "协作平台-更新或者新增協作 工作內容", tags = TagsConstants.COLLABORATION)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/update")
    public Response<Boolean> update(@Valid @RequestBody Request<CollaborationUpdateParams> request) {
        Boolean result = collaborationUpdate.update(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "协作平台-通過或者駁回協作", tags = TagsConstants.COLLABORATION)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/evaluation")
    public Response<Boolean> evaluation(@Valid @RequestBody Request<CollaborationEvaluationParams> request) {
        Boolean result = collaborationUpdate.evaluation(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "协作平台-提交工作", tags = TagsConstants.COLLABORATION)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/submit")
    public Response<Boolean> submit(@Valid @RequestBody Request<CollaborationDetailParams> request) {
        Boolean result = collaborationUpdate.submit(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "协作平台-导入工作", tags = TagsConstants.COLLABORATION)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/import")
    public Response<Boolean> importExcel(@Valid @RequestParam("request") String reqJson,
                                         @RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
        Request<IntegerParams> request = JsonUtils.deserialize(reqJson, Request.class, IntegerParams.class);
        Boolean result = collaborationImport.importExcel(request.getData(), multipartFile);
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "export-導出本次工作列表", tags = TagsConstants.COLLABORATION)
    @ApiResponse(responseCode = "0", description = "成功码")
    @CrossOrigin(exposedHeaders = {"Content-Disposition"})
    @PostMapping("/export")
    public ResponseEntity export(@Valid @RequestBody Request<CollaborationDetailParams> request) throws IOException {
        CollaborationVo collaborationVo = collaborationDetail.detail(request.getData());
        if (!CollectionUtils.isEmpty(collaborationVo.getContent())) {
            response.setContentType("application/vnd.ms-excel");
            String fileName = URLEncoder.encode(collaborationVo.getResource().getName(), "UTF8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        } else {
            return ResponseEntity.ok().body(null);
        }
        // 使用Apache POI生成Excel文件
        Workbook workbook = ExcelCollaborationUtils.generateExcel(collaborationVo);

        // 将Excel文件写入响应输出流
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();

        workbook.close();
        return ResponseEntity.ok().body(null);
    }

}
