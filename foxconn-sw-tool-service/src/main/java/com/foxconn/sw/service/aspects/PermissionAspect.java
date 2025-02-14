package com.foxconn.sw.service.aspects;

import com.foxconn.sw.business.LogBusiness;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.common.utils.SecurityUtils;
import com.foxconn.sw.common.utils.ServletUtils;
import com.foxconn.sw.common.utils.UUIDUtils;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.entity.acount.LoginParams;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Objects;

/**
 * 权限验证切面
 */
@Component
@Aspect
public class PermissionAspect {
    private static final Logger logger = LoggerFactory.getLogger(PermissionAspect.class);

    @Autowired
    CommonUserUtils commonUserUtils;
    @Autowired
    LogBusiness logBusiness;
    @Autowired
    private ServletUtils servletUtils;
    @Autowired
    HttpServletRequest servletRequest;
    @Autowired
    HttpServletResponse servletResponse;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    private void checkPermission() {
    }

    @Around("checkPermission() && args(request,..)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint, Object request) throws Throwable {
        Object retValue = null;
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            readCookie();
            contextInit(request, joinPoint);
            retValue = joinPoint.proceed();
//            writeCookie();
            stopWatch.stop();
        } catch (Throwable throwable) {
            logger.warn("call service throwable", throwable);
            throw throwable;
        } finally {
            logParam(joinPoint, stopWatch.getTotalTimeMillis(), servletUtils.getRemoteIp(), request);
            RequestContext.remove();
        }
        return retValue;
    }


    private void readCookie() {
        Cookie[] cookies = servletRequest.getCookies();
        String s = UUIDUtils.getUuid();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(" \t \t" +
                        s + "  "
                        + cookie.getName()
                        + "："
                        + SecurityUtils.decodeURL(cookie.getValue()));
            }
        }
    }

//    private void writeCookie() {
//        Cookie myCookie = new Cookie("myCookieName", "myCookieValue");
//        myCookie.setMaxAge(60 * 60 * 24 * 7); // 设置cookie有效期为1小时
//        myCookie.setPath("/"); // 设置cookie在所有路径下有效
//        servletResponse.addCookie(myCookie); // 将cookie添加到响应中
//    }


    private void contextInit(Object obj, ProceedingJoinPoint joinPoint) {
        String signatureName = joinPoint.getSignature().getName();
        String traceId = "";
        String token = "";

        if ("upload".equalsIgnoreCase(signatureName)) {
            if (Objects.nonNull(joinPoint.getArgs()[2])) {
                token = joinPoint.getArgs()[2].toString();
                traceId = token;
            }
        } else {

            Request request;
            if (obj instanceof Request) {
                request = (Request) obj;
            } else {
                request = JsonUtils.deserialize((String) obj, Request.class);
            }

            if (Objects.nonNull(request)
                    && Objects.nonNull(request.getHead())
                    && StringUtils.isNotEmpty(request.getHead().getToken())) {
                token = request.getHead().getToken();
                traceId = request.getTraceId();
            }

            if (request.getData() instanceof LoginParams) {
                token = "";
                RequestContext.put(RequestContext.ContextKey.EmployeeNo, ((LoginParams) request.getData()).getEmployeeNo());
            } else {
                RequestContext.put(RequestContext.ContextKey.EmployeeNo, request.getHead().getToken());
            }

        }

        if (StringUtils.isNotEmpty(token)) {
            UserInfo userInfo = commonUserUtils.queryUserInfo(token);
            String nameEmployeeNo = String.format("%s(%s)", userInfo.getEmployeeName(), userInfo.getEmployeeNo());
            RequestContext.put(RequestContext.ContextKey.USER_INFO, userInfo);
            RequestContext.put(RequestContext.ContextKey.NameEmployeeNo, nameEmployeeNo);
            RequestContext.put(RequestContext.ContextKey.EmployeeNo, userInfo.getEmployeeNo());
            RequestContext.put(RequestContext.ContextKey.OperateType, signatureName);
            RequestContext.put(RequestContext.ContextKey.TraceID, traceId);
            RequestContext.put(RequestContext.ContextKey.Token, token);
        }
    }

    private void logParam(ProceedingJoinPoint joinPoint, long intervals, String ip, Object request) {
        try {
            String operator = RequestContext.getEmployeeNo();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(joinPoint.getTarget().getClass().getSimpleName());
            stringBuilder.append(".");
            stringBuilder.append(joinPoint.getSignature().getName());
            String remark = JsonUtils.serialize(request);
            logBusiness.log(operator, stringBuilder.toString(), remark, intervals, ip);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
