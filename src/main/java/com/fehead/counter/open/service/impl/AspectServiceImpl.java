package com.fehead.counter.open.service.impl;

import com.fehead.counter.open.service.AspectService;
import com.fehead.counter.open.utils.RedisUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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

    @Pointcut("execution(* *..controller..*.*(..))")
    private void inController() {}

    /*@Pointcut("execution(* *..service..*.*(..))")
    private void inService() {}*/

    @Pointcut("@annotation(com.fehead.counter.open.annotation.SetPointCut)")
    private void withSetPointCut() {}

    @Pointcut("!@annotation(com.fehead.counter.open.annotation.NotSetPointCut)")
    private void withoutNotSetPointCut() {}

    @Override
    @After("(inController() || withSetPointCut()) && withoutNotSetPointCut()")
    public void afterCall(JoinPoint point) {
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        String key = applicationName +":"+ className +"."+ methodName+".total";
        redisUtils.incr(key,1);
    }

    @Override
    @AfterThrowing("(inController() || withSetPointCut()) && withoutNotSetPointCut()")
    public void afterThrowException(JoinPoint point) {
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        String key = applicationName +":"+ className+"."+methodName+".fail";
        redisUtils.incr(key,1);
    }
}
