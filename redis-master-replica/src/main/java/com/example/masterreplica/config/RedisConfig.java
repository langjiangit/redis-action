package com.example.masterreplica.config;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

/**
 * @Description:redis配置
 * @Author:irving
 * @Date:2020/7/2 4:37 下午
 **/
public class RedisConfig {

    public RedisConnectionFactory getRedisConnFactory(RedisProperties redisProperties) {
        //redis配置
        RedisConfiguration redisConfiguration = new
                RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
        ((RedisStandaloneConfiguration) redisConfiguration).setDatabase(redisProperties.getDatabase());
        ((RedisStandaloneConfiguration) redisConfiguration).setPassword(redisProperties.getPassword());


        //连接池配置
        GenericObjectPoolConfig genericObjectPoolConfig =
                new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(redisProperties.getPool().getMaxIdle());
        genericObjectPoolConfig.setMinIdle(redisProperties.getPool().getMinIdle());
        genericObjectPoolConfig.setMaxTotal(redisProperties.getPool().getMaxActive());
        genericObjectPoolConfig.setMaxWaitMillis(redisProperties.getPool().getMaxWait());

        //redis客户端配置
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder
                builder =  LettucePoolingClientConfiguration.builder().
                commandTimeout(Duration.ofMillis(redisProperties.getTimeout()));

        builder.shutdownTimeout(Duration.ofMillis(redisProperties.getPool().getShutdownTimeout()));
        builder.poolConfig(genericObjectPoolConfig);
        LettuceClientConfiguration lettuceClientConfiguration = builder.build();

        //根据配置和客户端配置创建连接
        LettuceConnectionFactory lettuceConnectionFactory = new
                LettuceConnectionFactory(redisConfiguration,lettuceClientConfiguration);
        return lettuceConnectionFactory;

    }




    public RedisTemplate buildRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置序列化策略
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    public StringRedisTemplate buildStringRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        StringRedisTemplate stringRedisTemplate=new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringRedisTemplate;
    }
}
