package com.foxconn.sw.service.aspects;

import com.foxconn.sw.common.utils.JsonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 权限验证切面
 */
@Component
@Aspect
public class PermissionAspect {
    private static final Logger logger = LoggerFactory.getLogger(PermissionAspect.class);


    @Pointcut("@annotation(com.foxconn.sw.service.aspects.Permission)")
    private void checkPermission() {
    }

    @Around("checkPermission() && @annotation(permission)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint, Permission permission) throws Throwable {
        Object retValue = null;
        String result = "fail";
        try {
            retValue = joinPoint.proceed();
            result = "success";
        } catch (Throwable throwable) {
            logger.warn("call service throwable", throwable);
            throw throwable;
        } finally {
            logParam(result, joinPoint, retValue);
        }
        return retValue;
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
