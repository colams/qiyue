package com.foxconn.sw.service.controller.universal;

import com.foxconn.sw.common.utils.ExecToolUtils;
import com.foxconn.sw.common.utils.UUIDUtils;
import com.foxconn.sw.common.utils.UploadUtils;
import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.constants.enums.FileAttrTypeEnums;
import com.foxconn.sw.data.constants.enums.RetCode;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.common.IDParams;
import com.foxconn.sw.data.interfaces.IResult;
import com.foxconn.sw.service.utils.FilePathUtils;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/universal")
public class CommonController {

    private final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);


    @Autowired
    Environment environment;
    @Autowired
    FilePathUtils filePathUtils;

    @Operation(summary = "下载文件", tags = TagsConstants.UNIVERSAL)
    @GetMapping("/down/{fileName}")
    public ResponseEntity<Resource> down(@PathVariable String fileName) throws Exception {
        String filePath = filePathUtils.getFilePath(FileAttrTypeEnums.TOOL.getSymbol()) + fileName;
        System.out.println(filePath);
        File file = new File(filePath);
        // 创建资源对象
        Resource resource = new UrlResource(file.toURI());

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .body(resource);
    }

    @Operation(summary = "上传文件", tags = TagsConstants.UNIVERSAL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/upload")
    public Response upload(@RequestParam("file") MultipartFile file,
                           @RequestParam("uploadType") String uploadType) throws FileNotFoundException {
        if (file.isEmpty()) {
            return ResponseUtils.failure(RetCode.EMPTY_FILE_ERROR, UUIDUtils.getUuid());
        }

        String fileBaseUrl = filePathUtils.getFilePath(uploadType);
        String path = UploadUtils.upload(fileBaseUrl, file, uploadType);

        IResult code = StringUtils.isBlank(path) ? RetCode.UPLOAD_FILE_ERROR : RetCode.SUCCESS;

        return ResponseUtils.response(path, code, UUIDUtils.getUuid());
    }

    @Operation(summary = "test 接口", tags = TagsConstants.UNIVERSAL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/test")
    public Response test() throws FileNotFoundException {

        List<String> results = new ArrayList<>();
        results.add("test 000:");
        results.add("test 001:");

        String contextPath = environment.getProperty("server.servlet.context-path");

        String fileName = ExecToolUtils.outputResult(results);
        return ResponseUtils.response(fileName, RetCode.SUCCESS, UUIDUtils.getUuid());
    }

    @Operation(summary = "常用入口信息", tags = TagsConstants.UNIVERSAL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/entrance")
    public Response entrance(Request<IDParams> request) {
        Response response = ResponseUtils.success(request.getTraceId());
        return response;
    }


}
