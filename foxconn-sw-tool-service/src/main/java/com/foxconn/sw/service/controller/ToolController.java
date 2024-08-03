package com.foxconn.sw.service.controller;

import com.foxconn.sw.business.tools.ToolCategoryBusiness;
import com.foxconn.sw.business.tools.ToolsBusiness;
import com.foxconn.sw.business.tools.ToolsHistoryBusiness;
import com.foxconn.sw.common.utils.*;
import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.constants.enums.FileAttrTypeEnums;
import com.foxconn.sw.data.constants.enums.retcode.RetCode;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.CategoryDTO;
import com.foxconn.sw.data.dto.entity.common.IDParams;
import com.foxconn.sw.data.dto.entity.tool.RunToolParams;
import com.foxconn.sw.data.dto.entity.tool.SwToolDTO;
import com.foxconn.sw.data.dto.entity.tool.ToolSearchParams;
import com.foxconn.sw.data.entity.SwTools;
import com.foxconn.sw.service.utils.FilePathUtils;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

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

    @Operation(summary = "工具信息", tags = TagsConstants.TOOL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/detail")
    public Response detail(@RequestBody Request<IDParams> request) {

        System.out.println("tool detail");
        SwToolDTO toolDTO = toolsBusiness.detail(request.getData().getId());
        Response response = ResponseUtils.success(toolDTO, request.getTraceId());
        return response;
    }

    @Operation(summary = "查询上传工具", tags = TagsConstants.TOOL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/search")
    public Response search(@RequestBody Request<PageParams<ToolSearchParams>> request) {
        PageParams<ToolSearchParams> pageParams = request.getData();
        System.out.println(JsonUtils.serialize(pageParams));
        List<SwToolDTO> toolDTOs = toolsBusiness.searchByParams(pageParams);
        int totalCount = toolsBusiness.getTotalCountByParams(pageParams);
        PageEntity<SwToolDTO> pageEntity = new PageEntity<>(totalCount, toolDTOs);

        Response response = ResponseUtils.success(pageEntity, request.getTraceId());
        return response;
    }

    @Operation(summary = "保存工具上传记录", tags = TagsConstants.TOOL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping(value = "/saveTool")
    public Response saveTool(@RequestBody Request<SwToolDTO> request) {
        System.out.println("tool save");
        Response response;
        SwToolDTO swToolDTO = request.getData();
        SwTools swTools = toolsBusiness.saveTool(swToolDTO);
        if (Objects.nonNull(swTools) && swTools.getId() > 0) {
            toolsHistoryBusiness.saveToolHistory(swTools);
            response = ResponseUtils.success(swTools.getId(), request.getTraceId());
        } else {
            response = ResponseUtils.failure(request.getTraceId());
        }
        return response;
    }

    @Operation(summary = "工具上传记录历史", tags = TagsConstants.TOOL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/history")
    public Response listToolHistory(@RequestBody Request<PageParams<Integer>> request) {
        PageParams<Integer> pageParams = request.getData();
        List<SwToolDTO> results = toolsHistoryBusiness.searchByToolId(request.getData());
        int totalCount = toolsHistoryBusiness.countByToolID(pageParams);
        PageEntity<SwToolDTO> pageEntity = new PageEntity<>(totalCount, results);
        return ResponseUtils.success(pageEntity, request.getTraceId());
    }

    @Operation(summary = "运行文件", tags = TagsConstants.TOOL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/run")
    public Response runTool(@RequestBody Request<RunToolParams> request) throws FileNotFoundException {
        RunToolParams toolParams = request.getData();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String filePath = filePathUtils.getFilePath(FileAttrTypeEnums.TOOL.getSymbol()) + toolParams.getToolPath();
        String result = ExecToolUtils.execTool(filePath, toolParams.getToolParams());
        stopWatch.stop();
        toolsBusiness.saveRunResult(toolParams.getToolPath(), result, "system", "執行耗時：" + stopWatch.getTotalTimeMillis());
        String resultPath = DomainRetrieval.getDomain() + "/result/" + result;
        return ResponseUtils.response(resultPath, RetCode.SUCCESS, request.getTraceId());
    }

    @Operation(summary = "工具分类", tags = TagsConstants.TOOL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/category")
    public Response categoryList() {
        LOGGER.info("logger list category");
        List<CategoryDTO> results = listToolCategoryBusiness.listAll();
        Response response = ResponseUtils.success(results, UUIDUtils.getUuid());
        return response;
    }

}
