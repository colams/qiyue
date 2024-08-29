package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.oa.MonthlyWorkParams;
import com.foxconn.sw.data.dto.entity.oa.MyWorks;
import com.foxconn.sw.service.processor.oa.MonthlyWorkProcessor;
import com.foxconn.sw.service.processor.oa.SummaryWorkProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/oa")
public class WorkController {

    @Autowired
    SummaryWorkProcessor summaryWorkProcessor;
    @Autowired
    MonthlyWorkProcessor monthlyWorkProcessor;

    @Operation(summary = "我的工作汇总信息", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/summary")
    public Response<MyWorks> summaryWork(@Valid @RequestBody Request request) {
        MyWorks myWorks = summaryWorkProcessor.summary(request.getHead());
        return ResponseUtils.success(myWorks, request.getTraceId());
    }

    @Operation(summary = "工作list月度分布 按年份查询", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/monthly")
    public Response<Map<String, Integer>> monthlyWork(@Valid @RequestBody Request<MonthlyWorkParams> request) {
        Map<String, Integer> mapUtils = monthlyWorkProcessor.monthlyWork(request.getData());
        return ResponseUtils.success(mapUtils, request.getTraceId());
    }
}
