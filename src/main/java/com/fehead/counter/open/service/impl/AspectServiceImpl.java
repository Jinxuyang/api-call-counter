package com.fehead.counter.open.service.impl;

import com.fehead.counter.open.service.AspectService;
import com.fehead.counter.open.utils.RedisUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author Verge
 * @Date 2020/11/13 19:04
 * @Version 1.0
 */
@Aspect
@Service
public class AspectServiceImpl implements AspectService {
    @Autowired
    RedisUtils redisUtils;

    @Value("${spring.application.name}")
    String applicationName;

    @Override
    @After("execution(* *.*.*.controller..*.*(..))")
    public void afterCall(JoinPoint point) {
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        String key = applicationName +":"+ className +"."+ methodName+".total";
        redisUtils.incr(key,1);
    }

    @Override
    @AfterThrowing("execution(* *.*.*.controller..*.*(..))")
    public void afterThrowException(JoinPoint point) {
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        String key = applicationName +":"+ className+"."+methodName+".fail";
        redisUtils.incr(key,1);
    }
}
