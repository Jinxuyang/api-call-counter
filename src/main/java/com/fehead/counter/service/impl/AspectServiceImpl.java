package com.fehead.counter.service.impl;

import com.fehead.counter.service.AspectService;
import com.fehead.counter.utils.RedisUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    @After("execution(* *.*.*.controller..*.*(..))")
    public void afterCall(JoinPoint point) {
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        String key = className +"."+ methodName+".total";
        redisUtils.incr(key,1);
    }

    @Override
    @AfterThrowing("execution(* *.*.*.controller..*.*(..))")
    public void afterThrowException(JoinPoint point) {
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        String key = className+"."+methodName+".fail";
        redisUtils.incr(key,1);
    }
}
