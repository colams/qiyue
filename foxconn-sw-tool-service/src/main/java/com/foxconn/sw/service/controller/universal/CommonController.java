package com.foxconn.sw.service.controller.universal;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.common.utils.*;
import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.constants.enums.retcode.RetCode;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.entity.universal.UploadResult;
import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.universal.ConvertProcessor;
import com.foxconn.sw.service.processor.universal.UploadProcessor;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
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
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/universal")
public class CommonController {

    @Autowired
    Environment environment;
    @Autowired
    FilePathUtils filePathUtils;
    @Autowired
    SwAppendResourceBusiness resourceBusiness;
    @Autowired
    CommonUserUtils userUtils;
    @Autowired
    UploadProcessor uploadProcessor;
    @Autowired
    ExecToolUtils execToolUtils;
    @Autowired
    ConvertProcessor convertProcessor;
    @Autowired
    HttpServletResponse response;

    @Operation(summary = "下载文件", tags = TagsConstants.UNIVERSAL)
    @GetMapping("/down/{id}/{fileName}")
    public ResponseEntity<Resource> down(@PathVariable Integer id, @PathVariable String fileName) throws Exception {
        SwAppendResource appendResource = resourceBusiness.getAppendResources(id);
        String filePath = filePathUtils.getFilePath(appendResource.getUploadType()) + fileName;
        File file = new File(filePath);
        // 创建资源对象
        Resource resource = new UrlResource(file.toURI());
        String filename = file.getName();
        if (StringUtils.isNotEmpty(appendResource.getOriginName())) {
            filename = appendResource.getOriginName();
        }
        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .body(resource);
    }

    @Operation(summary = "上传文件new", tags = TagsConstants.UNIVERSAL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/upload")
    public Response<List<UploadResult>> upload(@RequestParam("file") MultipartFile[] file,
                                               @RequestParam("uploadType") String uploadType,
                                               @RequestParam(name = "token", required = false) String token,
                                               @RequestParam(name = "request", required = false) String reqJson)
            throws FileNotFoundException {
        List<UploadResult> results = uploadProcessor.uploadFiles(file, uploadType, reqJson);
        return ResponseUtils.response(results, RetCode.SUCCESS, UUIDUtils.getUuid());
    }

    @Operation(summary = "常用入口信息", tags = TagsConstants.UNIVERSAL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/entrance")
    public Response entrance(@Valid @RequestBody Request<IntegerParams> request) {
        Response response = ResponseUtils.success(request.getTraceId());
        return response;
    }

    @Operation(summary = "设备管理", tags = TagsConstants.UNIVERSAL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/valid")
    public Response<String> valid(@Valid @RequestBody Request request) {
        String dateTime = "CMBU" + DateTimeUtils.format(LocalDateTime.now(), "yyyyMMddHHmm");
        int length = dateTime.length();
        String crc = crc16(dateTime, length);
        return ResponseUtils.success(dateTime + crc, request.getTraceId());
    }

    public static String crc16(String ptr, int len) {
        int crc = (int) 0xffff;
        char[] arrays = ptr.toCharArray();
        for (int i = 0; i < len; i++) {
            int ascii = arrays[i];
            crc ^= ascii;
            for (int j = 0; j < 8; j++) {
                if ((crc & 0x0001) != 0) {
                    crc = (short) ((crc >> 1) ^ 0xA001);
                } else {
                    crc = (short) (crc >> 1);
                }
                crc = crc & 0xFFFF;
            }
        }
        int crc1 = (crc >> 8) & 0xFFFF;
        int crc2 = (crc << 8) & 0xFFFF;
        crc = crc1 | crc2;
        return String.valueOf(String.format("%04X", crc));
    }

    @Operation(summary = "test 接口", tags = TagsConstants.UNIVERSAL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/test")
    public Response test() throws FileNotFoundException {

        List<String> results = new ArrayList<>();
        results.add("test 000:");
        results.add("test 001:");

        String contextPath = environment.getProperty("server.servlet.context-path");

        String fileName = execToolUtils.outputResult(results);
        return ResponseUtils.response(fileName, RetCode.SUCCESS, UUIDUtils.getUuid());
    }


    @Permission
    @Operation(summary = "文档转换接口", tags = TagsConstants.UNIVERSAL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @CrossOrigin(exposedHeaders = {"Content-Disposition"})
    @PostMapping("/convert")
    public ResponseEntity convert(@Valid @RequestBody Request<IntegerParams> request) throws IOException {
        Integer fileId = request.getData().getParams();
        byte[] resource = convertProcessor.convertFile(fileId);
        // 字节写入响应输出流
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(resource);
        outputStream.close();
        return ResponseEntity.ok().body(null);
    }

    @Operation(summary = "郵件發送接口", tags = TagsConstants.UNIVERSAL)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/mail")
    public ResponseEntity mailUtils() {
        MailUtils.sendEnclosureEmail();
        return ResponseEntity.ok("success");
    }


}
