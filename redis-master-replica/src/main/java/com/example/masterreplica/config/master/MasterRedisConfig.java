package com.example.masterreplica.config.master;
import com.example.masterreplica.config.RedisConfig;
import com.example.masterreplica.config.RedisProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
/**
 * @Description:主库配置
 * @Author:irving
 * @Date:2020/7/2 5:05 下午
 **/
@Configuration
public class MasterRedisConfig extends RedisConfig {


    @Primary
    @Bean(name = "masterRedisConnectionFactory")
    @Override
    public RedisConnectionFactory getRedisConnFactory(@Qualifier("masterRedisProperties") RedisProperties redisProperties) {
        return super.getRedisConnFactory(redisProperties);
    }


    @Bean(name = "masterRedisTemplate")
    public RedisTemplate buildRedisTemplate(@Qualifier("masterRedisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        return super.buildRedisTemplate(redisConnectionFactory);
    }

    @Bean(name = "masterStringRedisTemplate")
    @Override
    public StringRedisTemplate buildStringRedisTemplate(@Qualifier("masterRedisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        return super.buildStringRedisTemplate(redisConnectionFactory);
    }

    @Bean(name = "masterRedisProperties")
    @ConfigurationProperties(prefix = "spring.redis.master")
    public RedisProperties getBaseDBProperties() {
        return new RedisProperties();
    }
}
