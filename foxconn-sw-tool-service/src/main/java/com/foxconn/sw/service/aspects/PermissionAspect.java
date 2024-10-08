package com.foxconn.sw.service.aspects;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.Request;
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

/**
 * 权限验证切面
 */
@Component
@Aspect
public class PermissionAspect {
    private static final Logger logger = LoggerFactory.getLogger(PermissionAspect.class);

    @Autowired
    CommonUserUtils commonUserUtils;

    @Pointcut("@annotation(com.foxconn.sw.service.aspects.Permission)")
    private void checkPermission() {
    }


    @Around("checkPermission() && @annotation(permission) && args(request,..)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint, Permission permission, Object request) throws Throwable {
        Object retValue = null;
        String result = "fail";
        try {
            contextInit(request, joinPoint.getSignature().getName());
            retValue = joinPoint.proceed();
            result = "success";
        } catch (Throwable throwable) {
            logger.warn("call service throwable", throwable);
            throw throwable;
        } finally {
            RequestContext.remove();
            logParam(result, joinPoint, retValue);
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

    private void logParam(String result, ProceedingJoinPoint joinPoint, Object retValue) {
        try {
            String message = String.format("logParam ============ class:%s;method:%s;result:%s;param:%s;retValue:%s",
                    joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName(), result,
                    JsonUtils.serialize(joinPoint.getArgs()), JsonUtils.serialize(retValue));
            logger.info(message);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


}
