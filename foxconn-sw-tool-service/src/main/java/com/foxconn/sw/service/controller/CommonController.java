package com.foxconn.sw.service.controller;

import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.FilePathUtils;
import com.foxconn.sw.common.utils.UUIDUtils;
import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.constants.enums.retcode.RetCode;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.entity.universal.UploadResult;
import com.foxconn.sw.service.utils.ResponseUtils;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/universal")
public class CommonController {

    @Autowired
    Environment environment;
    @Autowired
    FilePathUtils filePathUtils;
    @Autowired
    HttpServletResponse response;

    @Operation(summary = "下载文件", tags = TagsConstants.UNIVERSAL)
    @GetMapping("/down/{id}/{fileName}")
    public ResponseEntity<Resource> down(@PathVariable Integer id, @PathVariable String fileName) throws Exception {
        Resource resource = UrlResource.from("");
        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(0)
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
        return ResponseUtils.response(Lists.newArrayList(), RetCode.SUCCESS, UUIDUtils.getUuid());
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
}
