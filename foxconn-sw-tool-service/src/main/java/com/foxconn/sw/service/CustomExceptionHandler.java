package com.foxconn.sw.service;

import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.common.utils.UUIDUtils;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.constants.enums.retcode.RetCode;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.service.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestControllerAdvice
public class CustomExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    /**
     * 捕获API调用接口的异常类
     *
     * @param e 业务异常参数
     * @return
     */
    @ExceptionHandler(BizException.class)
    public Response abstractApiException(BizException e) {
        log.warn("abstractApiException ===================================:" + e.getMessage(), e);
        return ResponseUtils.failure(e.getCode(), e.getMessage(), UUIDUtils.getUuid());
    }

    /**
     * 参数验证处理
     *
     * @param e 捕获的异常，封装返回的对象
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("MethodArgumentNotValidException ===================================:" + e.getMessage(), e);
        if (Objects.nonNull(e.getBindingResult()) && !CollectionUtils.isEmpty(e.getBindingResult().getAllErrors())) {
            List<String> errors = new ArrayList<>();
            e.getBindingResult().getAllErrors().forEach(error -> {
                errors.add(error.getDefaultMessage());
            });
            return ResponseUtils.success(JsonUtils.serialize(errors), UUIDUtils.getUuid());
        }
        return ResponseUtils.failure(RetCode.SYSTEM_EXCEPTION, UUIDUtils.getUuid());
    }

    /**
     * 通用异常捕获
     *
     * @param e 捕获的异常，封装返回的对象
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        log.warn("handleException ===================================:" + e.getMessage(), e);
        return ResponseUtils.failure(RetCode.SYSTEM_EXCEPTION, UUIDUtils.getUuid());
    }
}
