package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.collaboration.CollaborationDetailLogVo;
import com.foxconn.sw.data.dto.entity.collaboration.CollaborationVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.collaboration.*;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.collaboration.*;
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
import java.util.List;
import java.util.concurrent.ExecutionException;

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
    CollaborationUpdateHistoryProcessor updateHistoryProcessor;
    @Autowired
    HttpServletResponse response;
    @Autowired
    CollaborationUpdateCellProcessor collaborationUpdateCellProcessor;

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
    @Operation(summary = "协作平台-更新或者新增協作 工作內容", tags = TagsConstants.COLLABORATION)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/updateCell")
    public Response<Boolean> updateCell(@Valid @RequestBody Request<CollaborationUpdateCellParams> request) {
        Boolean result = collaborationUpdateCellProcessor.updateCell(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "协作平台-更新或者新增協作 工作內容", tags = TagsConstants.COLLABORATION)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/saveUpdate")
    public Response<Boolean> saveUpdate(@Valid @RequestBody Request<CollaborationSaveUpdateParams> request) {
        Boolean result = collaborationUpdateCellProcessor.saveUpdate(request.getData());
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
    @Operation(summary = "协作平台-记录表格更新记录", tags = TagsConstants.COLLABORATION)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/log")
    public Response<List<CollaborationDetailLogVo>> log(@Valid @RequestBody Request<CollaborationDetailLogParams> request) {
        List<CollaborationDetailLogVo> detailLogVoList = updateHistoryProcessor.log(request.getData());
        return ResponseUtils.success(detailLogVoList, request.getTraceId());
    }
//
//    @Permission
//    @Operation(summary = "协作平台-导入工作", tags = TagsConstants.COLLABORATION)
//    @ApiResponse(responseCode = "0", description = "成功码")
//    @PostMapping("/clearBg")
//    public Response<Boolean> clearBg(@Valid @RequestBody Request<CollaborationDetailParams> request) {
//        Boolean result = collaborationUpdate.clearBg(request.getData());
//        return ResponseUtils.success(result, request.getTraceId());
//    }

    @Permission
    @Operation(summary = "协作平台-导入工作", tags = TagsConstants.COLLABORATION)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/import")
    public Response<Boolean> importExcel(@Valid @RequestParam("request") String reqJson,
                                         @RequestParam("multipartFile") MultipartFile multipartFile)
            throws IOException, ExecutionException, InterruptedException {
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
        CollaborationVo collaborationVo = collaborationDetail.detail(request.getData(), true);
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
