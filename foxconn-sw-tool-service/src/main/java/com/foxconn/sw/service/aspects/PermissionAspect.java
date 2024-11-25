package com.foxconn.sw.service.aspects;

import com.foxconn.sw.business.LogBusiness;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.common.utils.ServletUtils;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

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

    @Pointcut("@annotation(com.foxconn.sw.service.aspects.Permission)")
    private void checkPermission() {
    }

    @Around("checkPermission() && @annotation(permission) && args(request,..)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint, Permission permission, Object request) throws Throwable {
        Object retValue = null;
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            contextInit(request, joinPoint.getSignature().getName());
            retValue = joinPoint.proceed();
            stopWatch.stop();
        } catch (Throwable throwable) {
            logger.warn("call service throwable", throwable);
            throw throwable;
        } finally {
            logParam(joinPoint, retValue, stopWatch.getTotalTimeMillis(), servletUtils.getRemoteIp(), request);
            RequestContext.remove();
        }
        return retValue;
    }


    private void contextInit(Object obj, String signatureName) {
        Request request;
        if (obj instanceof Request) {
            request = (Request) obj;
        } else {
            request = JsonUtils.deserialize((String) obj, Request.class);
        }
        UserInfo userInfo = commonUserUtils.queryUserInfo(request.getHead().getToken());
        String nameEmployeeNo = String.format("%s(%s)", userInfo.getEmployeeName(), userInfo.getEmployeeNo());
        RequestContext.put(RequestContext.ContextKey.USER_INFO, userInfo);
        RequestContext.put(RequestContext.ContextKey.NameEmployeeNo, nameEmployeeNo);
        RequestContext.put(RequestContext.ContextKey.EmployeeNo, userInfo.getEmployeeNo());
        RequestContext.put(RequestContext.ContextKey.OperateType, signatureName);
    }

    private void logParam(ProceedingJoinPoint joinPoint, Object retValue, long intervals, String ip, Object request) {
        try {
            String message = "logParam ============ retValue:";
            if (retValue instanceof Response && ((Response) retValue).getCode() != 0) {
                message += JsonUtils.serialize(retValue);
            } else {
                message += "----------";
            }

            logger.info(message);
            String operator = RequestContext.getEmployeeNo();
            String operateType = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();
            String remark = JsonUtils.serialize(request);
            logBusiness.log(operator, operateType, remark, intervals, ip);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
