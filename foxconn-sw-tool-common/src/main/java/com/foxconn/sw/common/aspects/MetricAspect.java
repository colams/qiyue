package com.foxconn.sw.common.aspects;

import com.foxconn.sw.common.context.RequestContext;
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
public class MetricAspect {
    private static final Logger logger = LoggerFactory.getLogger(MetricAspect.class);

    @Autowired
    private ServletUtils servletUtils;

    @Pointcut("@annotation(com.foxconn.sw.common.aspects.Metric)")
    private void log() {
    }


    @Around("log()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object retValue;
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            retValue = joinPoint.proceed();
            stopWatch.stop();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            metric(joinPoint, stopWatch.getTotalTimeMillis(), servletUtils.getRemoteIp());
        }
        return retValue;
    }

    private void metric(ProceedingJoinPoint joinPoint, long intervals, String ip) {
        try {
            String operateType = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();
            logger.info(String.format("%s metric ============ %s,%s intervals: %s", RequestContext.getTraceID(), operateType, ip, intervals));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
