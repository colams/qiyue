package com.foxconn.sw.service.controller;

import com.foxconn.sw.business.tools.ToolCategoryBusiness;
import com.foxconn.sw.business.tools.ToolsBusiness;
import com.foxconn.sw.business.tools.ToolsHistoryBusiness;
import com.foxconn.sw.common.utils.ConfigReader;
import com.foxconn.sw.common.utils.FilePathUtils;
import com.foxconn.sw.common.utils.UUIDUtils;
import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.constants.enums.retcode.RetCode;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.CategoryDTO;
import com.foxconn.sw.data.dto.entity.tool.RunToolParams;
import com.foxconn.sw.data.dto.entity.tool.SwToolDTO;
import com.foxconn.sw.data.dto.entity.tool.ToolSearchParams;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.service.processor.tool.RunToolProcessor;
import com.foxconn.sw.service.processor.tool.SaveToolProcessor;
import com.foxconn.sw.service.processor.tool.SearchToolProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.script.ScriptException;
import java.io.IOException;
import java.util.List;

/**
 * 參考：https://blog.51cto.com/u_14023400/8627126
 * 参数
 * 审核开放在线使用
 */
@CrossOrigin
@RestController
@RequestMapping("api/tool")
public class ToolController {

    private final Logger LOGGER = LoggerFactory.getLogger(ToolController.class);
    @Autowired
    ToolsBusiness toolsBusiness;
    @Autowired
    ToolsHistoryBusiness toolsHistoryBusiness;
    @Autowired
    ToolCategoryBusiness listToolCategoryBusiness;
    @Autowired
    ConfigReader configReader;
    @Autowired
    FilePathUtils filePathUtils;
    @Autowired
    SaveToolProcessor toolProcessor;
    @Autowired
    RunToolProcessor runToolProcessor;
    @Autowired
    SearchToolProcessor searchToolProcessor;

    @Operation(summary = "保存工具上传记录", tags = TagsConstants.TOOL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping(value = "/saveTool")
    public Response saveTool(@Valid @RequestBody Request<SwToolDTO> request) {
        toolProcessor.toolSave(request.getData(), request.getHead());
        return ResponseUtils.success(request.getTraceId());
    }

    @Operation(summary = "工具信息", tags = TagsConstants.TOOL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/detail")
    public Response detail(@Valid @RequestBody Request<IntegerParams> request) {
        SwToolDTO toolDTO = toolsBusiness.detail(request.getData().getParams());
        return ResponseUtils.success(toolDTO, request.getTraceId());
    }

    @Operation(summary = "查询上传工具", tags = TagsConstants.TOOL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/search")
    public Response<PageEntity<SwToolDTO>> search(@Valid @RequestBody Request<PageParams<ToolSearchParams>> request) {
        PageEntity<SwToolDTO> pageEntity = searchToolProcessor.search(request.getData());
        return ResponseUtils.success(pageEntity, request.getTraceId());
    }

    @Operation(summary = "工具上传记录历史", tags = TagsConstants.TOOL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/history")
    public Response<PageEntity<SwToolDTO>> listToolHistory(
            @Valid @RequestBody Request<PageParams<IntegerParams>> request) {
        PageParams<IntegerParams> pageParams = request.getData();
        List<SwToolDTO> results = toolsHistoryBusiness.searchByToolId(request.getData());
        Long totalCount = toolsHistoryBusiness.countByToolID(pageParams.getParams());
        PageEntity<SwToolDTO> pageEntity = new PageEntity<>(totalCount, results);
        return ResponseUtils.success(pageEntity, request.getTraceId());
    }

    @Operation(summary = "运行文件", tags = TagsConstants.TOOL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/run")
    public Response runTool(@Valid @RequestBody Request<RunToolParams> request) throws IOException, ScriptException {
        String resultPath = runToolProcessor.runTool(request.getData(), request.getHead());
        return ResponseUtils.response(resultPath, RetCode.SUCCESS, request.getTraceId());
    }

    @Operation(summary = "工具分类", tags = TagsConstants.TOOL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/category")
    public Response categoryList(@Valid @RequestBody Request<IntegerParams> request) {
        List<CategoryDTO> results = listToolCategoryBusiness.listByType(1);
        return ResponseUtils.success(results, UUIDUtils.getUuid());
    }

}
