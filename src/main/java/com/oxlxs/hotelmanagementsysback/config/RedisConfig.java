package com.oxlxs.hotelmanagementsysback.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // 创建 ObjectMapper 并注册 JavaTimeModule
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());  // 注册 Java 8 日期时间模块

        // 使用自定义 ObjectMapper 创建 GenericJackson2JsonRedisSerializer
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(objectMapper);

        // 设置 key 和 value 的序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);  // 使用支持 Java 8 时间类型的 Jackson 序列化器

        return template;
    }
}
