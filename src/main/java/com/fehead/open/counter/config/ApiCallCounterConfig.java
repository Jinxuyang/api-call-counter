package com.fehead.open.counter.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Verge
 * @Date 2020/11/13 18:59
 * @Version 1.0
 */
@EnableConfigurationProperties
@ComponentScan({"com.fehead.counter"})
@Configuration
public class ApiCallCounterConfig {
}
