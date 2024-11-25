package com.foxconn.sw.common.aspects;

import com.foxconn.sw.common.utils.ServletUtils;
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
public class IntervalsAspect {
    private static final Logger logger = LoggerFactory.getLogger(IntervalsAspect.class);

    @Autowired
    private ServletUtils servletUtils;

    @Pointcut("@annotation(com.foxconn.sw.common.aspects.Intervals)")
    private void logIntervals() {
    }


    @Around("logIntervals() ")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object retValue;
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            retValue = joinPoint.proceed();
            stopWatch.stop();
        } catch (Throwable throwable) {
            logger.warn("call service throwable", throwable);
            throw throwable;
        } finally {
            logParam(joinPoint, stopWatch.getTotalTimeMillis(), servletUtils.getRemoteIp());
        }
        return retValue;
    }

    private void logParam(ProceedingJoinPoint joinPoint, long intervals, String ip) {
        try {
            String operateType = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();
            String message = String.format("logIntervals ============ %s,%s intervals: %s", operateType, ip, intervals);
            logger.info(message);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
