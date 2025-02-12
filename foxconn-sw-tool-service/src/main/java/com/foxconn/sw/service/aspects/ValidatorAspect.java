package com.foxconn.sw.service.aspects;

import com.foxconn.sw.business.LogBusiness;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.common.utils.ServletUtils;
import com.foxconn.sw.data.dto.Request;
import jakarta.annotation.Resource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.groups.Default;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDateTime;
import java.util.Set;

@Component
@Aspect
public class ValidatorAspect {

    private static final Logger logger = LoggerFactory.getLogger(ValidatorAspect.class);

    @Resource
    private LocalValidatorFactoryBean localValidatorFactoryBean;
    @Autowired
    private ServletUtils servletUtils;

    @Autowired
    LogBusiness logBusiness;

    @Before("parameterPointcut() && args(request,..)")
    public void before(JoinPoint joinPoint, Request request) {
        Set<ConstraintViolation<Request>> validatorErrors = this.localValidatorFactoryBean.validate(request, new Class[]{Default.class});
        // todo 权限校验
        LocalDateTime localDateTime = LocalDateTime.now();
        logParam(joinPoint, request, servletUtils.getRemoteIp());
    }

    @Around("parameterPointcut() && args(request,..)")
    public Object around(ProceedingJoinPoint joinPoint, Request request) throws Throwable {
        Object object = joinPoint.proceed();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\t\r\n" + request.getTraceId());
        stringBuffer.append("----[" + joinPoint.getTarget().getClass().getSimpleName() + ".");
        stringBuffer.append(joinPoint.getSignature().getName() + "]----");
        stringBuffer.append(servletUtils.getRemoteIp());
        logger.info(stringBuffer.toString());
        return object;
    }

    @After("parameterPointcut() && args(request,..)")
    public void after(JoinPoint joinPoint, Request request) {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    private void parameterPointcut() {

    }

    private void logParam(JoinPoint joinPoint, Request request, String ip) {
        try {
            String operator = ip;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(joinPoint.getTarget().getClass().getSimpleName());
            stringBuilder.append(".");
            stringBuilder.append(joinPoint.getSignature().getName());
            String remark = JsonUtils.serialize(request);
            logBusiness.log(operator, stringBuilder.toString(), remark, 0L, ip);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
