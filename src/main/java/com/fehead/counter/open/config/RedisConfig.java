package com.fehead.counter.open.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * @Author Verge
 * @Date 2020/11/13 20:01
 * @Version 1.0
 */
@Configuration
public class RedisConfig {

    @Value("${fehead.api-call-counter.redis.host}")
    String host;
    @Value("${fehead.api-call-counter.redis.password:null}")
    String password;
    @Value("${fehead.api-call-counter.redis.port:6379}")
    int port;
    @Value("${fehead.api-call-counter.redis.database:0}")
    int database;
    /**
     * 设置redis连接
     * @return
     */
    @Bean
    public LettuceConnectionFactory getLettuceConnectionFactory(){
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(host);
        redisConfig.setDatabase(database);
        redisConfig.setPort(port);
        if (!password.equals("null")){
            System.out.println("有密码： "+password);
            RedisPassword redisPassword = RedisPassword.of(password);
            redisConfig.setPassword(redisPassword);
        }
        return new LettuceConnectionFactory(redisConfig);
    }

    /**
     * 自定义RedisTemplate
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> getRedisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        //om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
