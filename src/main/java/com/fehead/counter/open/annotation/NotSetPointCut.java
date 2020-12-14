package com.fehead.counter.open.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记该方法，不统计其调用次数
 * @Author Verge
 * @Date 2020/12/14 16:33
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotSetPointCut {
}
