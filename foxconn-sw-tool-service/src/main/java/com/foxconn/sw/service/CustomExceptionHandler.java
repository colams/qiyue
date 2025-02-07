package com.foxconn.sw.service;

import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.common.utils.ServletUtils;
import com.foxconn.sw.common.utils.UUIDUtils;
import com.foxconn.sw.data.constants.enums.retcode.RetCode;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.service.utils.ResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
@EnableAsync
@RestControllerAdvice
public class CustomExceptionHandler implements AsyncConfigurer {

    private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private ServletUtils servletUtils;

    /**
     * 捕获API调用接口的异常类
     *
     * @param e 业务异常参数
     * @return
     */
    @ExceptionHandler(BizException.class)
    public Response abstractApiException(BizException e) {
        log.warn("abstractApiException =========== " + servletUtils.getRemoteIp() + servletUtils.getRequestURL(), e.getMessage());
        return ResponseUtils.failure(e.getCode(), e.getMessage(), UUIDUtils.getUuid());
    }

    @ExceptionHandler(ClientAbortException.class)
    public void handleClientAbortException(ClientAbortException e) {
        log.debug("Client aborted the connection", e.getMessage());
    }

    /**
     * 参数验证处理
     *
     * @param e 捕获的异常，封装返回的对象
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("MethodArgumentNotValidException =========== " + servletUtils.getRemoteIp() + servletUtils.getRequestURL(), e);
        if (Objects.nonNull(e.getBindingResult()) && !CollectionUtils.isEmpty(e.getBindingResult().getAllErrors())) {
            List<String> errors = new ArrayList<>();
            e.getBindingResult().getAllErrors().forEach(error -> {
                errors.add(error.getDefaultMessage());
            });
            return ResponseUtils.failure(RetCode.VALIDATE_FAILED.getCode(), JsonUtils.serialize(errors), UUIDUtils.getUuid());
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
        log.warn("handleException =========== " + servletUtils.getRemoteIp() + servletUtils.getRequestURL(), e);
        return ResponseUtils.failure(RetCode.SYSTEM_EXCEPTION, UUIDUtils.getUuid());
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new CustomAsyncExceptionHandler();
    }

    public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable ex, Method method, Object... params) {
            log.warn("异步任务出现异常.....:" + ex.toString());
            // 在这里记录异常或者进行其他处理逻辑
            System.err.println("异步任务出现异常：" + ex.getMessage());
        }
    }

}
