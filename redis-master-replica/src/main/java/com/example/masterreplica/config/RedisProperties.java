package com.example.masterreplica.config;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @Description:redis配置
 * @Author:irving
 * @Date:2020/7/2 4:30 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedisProperties {

    private Integer database;
    private String host;
    private Integer port;
    private String password;
    private Integer timeout;
    private Pool pool;

    @Data
    public static class Pool {
        private Integer maxActive;
        private Integer minIdle;
        private Integer maxIdle;
        private Integer maxWait;
        private Integer shutdownTimeout;
    }
}
