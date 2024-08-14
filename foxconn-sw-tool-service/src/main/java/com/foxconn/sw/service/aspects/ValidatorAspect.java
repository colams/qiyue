package com.foxconn.sw.service.aspects;

import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.Request;
import jakarta.annotation.Resource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.groups.Default;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Set;

@Component
@Aspect
public class ValidatorAspect {

    private static final Logger logger = LoggerFactory.getLogger(ValidatorAspect.class);

    @Resource
    private LocalValidatorFactoryBean localValidatorFactoryBean;

    @Before("parameterPointcut() && args(request,..)")
    public void validateParameter(JoinPoint joinPoint, Request request) {
        Set<ConstraintViolation<Request>> validatorErrors = this.localValidatorFactoryBean.validate(request, new Class[]{Default.class});
        // 处理校验结果
        // todo 权限校验
        System.out.println("時間戳：" + DateTimeUtils.getTimeStamp());
        System.out.println("class=============================:\r\n" + request.getClass());
        System.out.println("signature=========================:\r\n" + joinPoint.getSignature());
        System.out.println("param=============================:\r\n" + JsonUtils.serialize(request));
    }


    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    private void parameterPointcut() {

    }

}
