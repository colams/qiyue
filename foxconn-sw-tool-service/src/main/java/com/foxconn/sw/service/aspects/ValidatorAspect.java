package com.foxconn.sw.service.aspects;

import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.service.utils.ServletUtils;
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

import java.util.Set;

@Component
@Aspect
public class ValidatorAspect {

    private static final Logger logger = LoggerFactory.getLogger(ValidatorAspect.class);

    @Resource
    private LocalValidatorFactoryBean localValidatorFactoryBean;
    @Autowired
    private ServletUtils servletUtils;


    @Before("parameterPointcut() && args(request,..)")
    public void before(JoinPoint joinPoint, Request request) {
        Set<ConstraintViolation<Request>> validatorErrors = this.localValidatorFactoryBean.validate(request, new Class[]{Default.class});
        // 处理校验结果
        // todo 权限校验
        System.out.println("時間戳：" + DateTimeUtils.formatNow2() + "   =====," + servletUtils.getRemoteIp() + "====," + request.getTraceId());
        System.out.println("before signature=========================:" + joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName());
    }

    @Around("parameterPointcut() && args(request,..)")
    public Object around(ProceedingJoinPoint joinPoint, Request request) throws Throwable {
        System.out.println("時間戳：" + DateTimeUtils.formatNow2() + "   =====," + servletUtils.getRemoteIp() + "====," + request.getTraceId());
        System.out.println("around signature=========================:" + joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName());
        Object object = joinPoint.proceed();
        System.out.println("return：" + DateTimeUtils.formatNow2() + "   =====," + servletUtils.getRemoteIp() + "====," + request.getTraceId());
        return object;
    }


    @After("parameterPointcut() && args(request,..)")
    public void after(JoinPoint joinPoint, Request request) {
        System.out.println("after 時間戳：" + DateTimeUtils.formatNow2() + "   =====," + servletUtils.getRemoteIp() + "====," + request.getTraceId());
        System.out.println("after signature=========================:" + joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName());
    }


    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    private void parameterPointcut() {

    }

}
