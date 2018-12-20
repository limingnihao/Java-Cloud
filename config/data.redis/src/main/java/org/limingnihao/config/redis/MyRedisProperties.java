package org.limingnihao.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.Resource;

/**
 * Created by lishiming on 2018/2/9.
 * 实现了spring-boot-starter-data-redis的功能，
 * 使用spring-boot-starter-data-redis以后，直接使用RedisTemplate就可以了
 */
@ConfigurationProperties
@Resource
public class MyRedisProperties {

    @Value("${spring.redis.host}")
    private String redis_host;

    @Value("${spring.redis.port}")
    private String redis_port;

    @Value("${spring.redis.password}")
    private String redis_password;

    @Override
    public String toString() {
        return "MyRedisProperties{" + ", redis_host='" + redis_host + '\'' + ", redis_port='" + redis_port + '\'' + ", redis_password='" + redis_password + '\'' + '}';
    }

    public String getRedis_host() {
        return redis_host;
    }

    public void setRedis_host(String redis_host) {
        this.redis_host = redis_host;
    }

    public String getRedis_port() {
        return redis_port;
    }

    public void setRedis_port(String redis_port) {
        this.redis_port = redis_port;
    }

    public String getRedis_password() {
        return redis_password;
    }

    public void setRedis_password(String redis_password) {
        this.redis_password = redis_password;
    }
}
