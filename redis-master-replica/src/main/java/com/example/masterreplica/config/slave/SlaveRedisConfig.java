package com.example.masterreplica.config.slave;
import com.example.masterreplica.config.RedisConfig;
import com.example.masterreplica.config.RedisProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author irving
 */
@Configuration
public class SlaveRedisConfig extends RedisConfig {

    @Bean("slaveRedisConnectionFactory")
    @Override
    public RedisConnectionFactory getRedisConnFactory(@Qualifier("slaveRedisProperties") RedisProperties redisProperties) {
        return super.getRedisConnFactory(redisProperties);
    }

    @Bean("slaveRedisTemplate")
    @Override
    public RedisTemplate buildRedisTemplate(@Qualifier("slaveRedisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        return super.buildRedisTemplate(redisConnectionFactory);
    }


    @Bean("slaveStringRedisTemplate")
    @Override
    public StringRedisTemplate buildStringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return super.buildStringRedisTemplate(redisConnectionFactory);
    }

    @Bean(name = "slaveRedisProperties")
    @ConfigurationProperties(prefix = "spring.redis.slave")
    public RedisProperties getBaseDBProperties() {
        return new RedisProperties();
    }
}
