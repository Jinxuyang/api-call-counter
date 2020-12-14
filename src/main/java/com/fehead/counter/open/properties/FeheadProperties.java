package com.fehead.counter.open.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Verge
 * @Date 2020/11/20 13:35
 * @Version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "fehead.api-call-counter")
public class FeheadProperties {
    @Data
    public static class Redis{
        private String host;
        private String password;
        private int port = 6379;
        private int database = 0;
    }

    private Redis redis;
}
