package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.oa.ReportSearchParams;
import com.foxconn.sw.data.dto.entity.oa.WorkReportParams;
import com.foxconn.sw.data.dto.entity.oa.WorkReportVo;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.oa.ListReportProcessor;
import com.foxconn.sw.service.processor.oa.ReportAuthorityProcessor;
import com.foxconn.sw.service.processor.oa.SubmitReportProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Permission
    @Operation(summary = "獲取週報權限信息", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/authority")
    public Response<Boolean> reportAuthority(@Valid @RequestBody Request request) {
        boolean result = reportAuthority.reportAuthority();
        return ResponseUtils.success(result, request.getTraceId());
    }


    @Permission
    @Operation(summary = "获取工作汇报", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<List<WorkReportVo>> listReport(@Valid @RequestBody Request<ReportSearchParams> request) {
        List<WorkReportVo> vos = listReport.listReport(request.getData());
        return ResponseUtils.success(vos, request.getTraceId());
    }

    @Permission
    @Operation(summary = "提交工作汇报", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/submit")
    public Response submitReport(@Valid @RequestBody Request<List<WorkReportParams>> request) {
        submitReport.submitReport(request.getData());
        return ResponseUtils.success(request.getTraceId());
    }


}
