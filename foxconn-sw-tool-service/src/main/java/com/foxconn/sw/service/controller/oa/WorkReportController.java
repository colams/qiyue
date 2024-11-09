package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.oa.ReportSearchParams;
import com.foxconn.sw.data.dto.entity.oa.WorkReportParams;
import com.foxconn.sw.data.dto.entity.oa.WorkReportVo;
import com.foxconn.sw.data.dto.entity.universal.StringParams;
import com.foxconn.sw.data.dto.request.report.ScoreParams;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.ReportAuthorityProcessor;
import com.foxconn.sw.service.processor.report.*;
import com.foxconn.sw.service.utils.ExcelWorkReportUtils;
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

@CrossOrigin
@RestController
@RequestMapping("api/oa/report")
public class WorkReportController {

    @Autowired
    ListReportProcessor listReport;
    @Autowired
    SubmitReportProcessor submitReport;
    @Autowired
    ReportAuthorityProcessor reportAuthority;
    @Autowired
    ExportStatusProcessor exportStatus;
    @Autowired
    ScoreReportProcessor scoreReport;
    @Autowired
    ReportFileNameProcessor fileNameProcessor;
    @Autowired
    HttpServletResponse response;

    @Permission
    @Operation(summary = "獲取週報權限信息", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/authority")
    public Response<Boolean> reportAuthority(@Valid @RequestBody Request request) {
        boolean result = reportAuthority.reportAuthority();
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "获取工作汇报", tags = TagsConstants.REPORT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<List<WorkReportVo>> listReport(@Valid @RequestBody Request<ReportSearchParams> request) {
        List<WorkReportVo> vos = listReport.listReport(request.getData());
        return ResponseUtils.success(vos, request.getTraceId());
    }

    @Permission
    @Operation(summary = "提交工作汇报", tags = TagsConstants.REPORT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/submit")
    public Response submitReport(@Valid @RequestBody Request<List<WorkReportParams>> request) {
        submitReport.submitReport(request.getData());
        return ResponseUtils.success(request.getTraceId());
    }

    @Permission
    @Operation(summary = "更新导出工作锁定状态", tags = TagsConstants.REPORT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/updateExport")
    public Response updateExport(@Valid @RequestBody Request<StringParams> request) {
        exportStatus.updateExport(request.getData());
        return ResponseUtils.success(request.getTraceId());
    }

    @Permission
    @Operation(summary = "取消导出工作汇报", tags = TagsConstants.REPORT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/exportStatus")
    public Response<Boolean> exportStatus(@Valid @RequestBody Request<StringParams> request) {
        boolean result = exportStatus.exportStatus(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "周报打分接口", tags = TagsConstants.REPORT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/score")
    public Response<Boolean> score(@Valid @RequestBody Request<ScoreParams> request) {
        boolean result = scoreReport.score(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "導出周报信息", tags = TagsConstants.REPORT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @CrossOrigin(exposedHeaders = {"Content-Disposition"})
    @PostMapping("/export")
    public ResponseEntity export(@Valid @RequestBody Request<ReportSearchParams> request) throws IOException {
        // 设置响应头
        response.setContentType("application/vnd.ms-excel");
        String fileName = fileNameProcessor.generatorFileName(request.getData().getStartDate());
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        List<WorkReportVo> vos = listReport.listReport(request.getData(), true);
        if (CollectionUtils.isEmpty(vos)) {
            return ResponseEntity.ok().body(null);
        }
        // 使用Apache POI生成Excel文件
        Workbook workbook = ExcelWorkReportUtils.generateExcel(vos, request.getData().getStartDate(), request.getData().getEndDate());

        // 将Excel文件写入响应输出流
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();

        workbook.close();
        return ResponseEntity.ok().body(null);
    }
}
