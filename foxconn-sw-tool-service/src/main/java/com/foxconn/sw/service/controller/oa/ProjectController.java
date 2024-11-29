package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.project.ProjectListVo;
import com.foxconn.sw.data.dto.request.project.ProjectSaveParams;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.project.ProjectImportProcessor;
import com.foxconn.sw.service.processor.project.ProjectListProcessor;
import com.foxconn.sw.service.processor.project.ProjectSaveProcessor;
import com.foxconn.sw.service.utils.ExcelProjectUtils;
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
import java.time.LocalDateTime;

@CrossOrigin
@RestController
@RequestMapping("api/oa/project")
public class ProjectController {

    @Autowired
    ProjectListProcessor projectListProcessor;
    @Autowired
    ProjectSaveProcessor projectSaveProcessor;
    @Autowired
    ProjectImportProcessor projectImportProcessor;
    @Autowired
    HttpServletResponse response;
    @Autowired
    ExcelProjectUtils excelProjectUtils;

    @Permission
    @Operation(summary = "获取专案列表", tags = TagsConstants.PROJECT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<ProjectListVo> list(@Valid @RequestBody Request request) throws IOException {
        ProjectListVo listVo = projectListProcessor.list();
        return ResponseUtils.success(listVo, request.getTraceId());
    }

    @Permission
    @Operation(summary = "保存专案信息", tags = TagsConstants.PROJECT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/save")
    public Response<Boolean> save(@Valid @RequestBody Request<ProjectSaveParams> request) {
        Boolean result = projectSaveProcessor.save(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "导入专案信息", tags = TagsConstants.PROJECT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/import")
    public Response<Boolean> importExcel(@Valid @RequestBody Request request) throws IOException {
        Boolean result = projectImportProcessor.importExcel(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "导出专案", tags = TagsConstants.PROJECT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/export")
    public ResponseEntity export(@Valid @RequestBody Request request) throws IOException {
        // 设置响应头
        response.setContentType("application/vnd.ms-excel");
        String fileName = "project list " + LocalDateTime.now() + ".xlsx";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        ProjectListVo listVo = projectListProcessor.list();
        if (CollectionUtils.isEmpty(listVo.getProjectItems())) {
            return ResponseEntity.ok().body(null);
        }
        // 使用Apache POI生成Excel文件
        Workbook workbook = excelProjectUtils.generateExcel(listVo);

        // 将Excel文件写入响应输出流
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();

        workbook.close();
        return ResponseEntity.ok().body(null);
    }


}
