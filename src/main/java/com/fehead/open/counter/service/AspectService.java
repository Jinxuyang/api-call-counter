package com.fehead.open.counter.service;

import org.aspectj.lang.JoinPoint;

/**
 * @Author Verge
 * @Date 2020/11/13 19:02
 * @Version 1.0
 */

public interface AspectService {
    /**
     * api调用后执行
     * @param point 切点
     */
    void afterCall(JoinPoint point);

    /**
     * api调用出现异常时执行
     * @param point
     */
    void afterThrowException(JoinPoint point);
}
