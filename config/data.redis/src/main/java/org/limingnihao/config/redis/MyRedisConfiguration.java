package org.limingnihao.config.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by lishiming on 2018/2/9.
 */
@Configuration
@EnableTransactionManagement
@EnableScheduling
@EnableConfigurationProperties(MyRedisProperties.class)
public class MyRedisConfiguration {

    public static final Logger logger = LoggerFactory.getLogger(MyRedisConfiguration.class);

    @Autowired
    private MyRedisProperties properties;

    @Bean
    JedisPoolConfig poolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大空闲数
        config.setMaxIdle(300);
        // 连接池的最大数据库连接数
        config.setMaxTotal(1000);
        // 最大建立连接等待时间
        config.setMaxWaitMillis(1000);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        config.setMinEvictableIdleTimeMillis(300000);
        // 每次逐出检查时 逐出的最大数目
        config.setNumTestsPerEvictionRun(1024);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程
        config.setTimeBetweenEvictionRunsMillis(30000);
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        config.setTestOnBorrow(true);
        // 在空闲时检查有效性, 默认false
        config.setTestWhileIdle(true);
        return config;
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        logger.info("=============== jedisConnectionFactory =============== " + this.properties.toString());
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setPoolConfig(this.poolConfig());
        factory.setHostName(this.properties.getRedis_host());
        factory.setPort(Integer.parseInt(this.properties.getRedis_port()));
        factory.setPassword(this.properties.getRedis_password());
        return factory;
    }

    @Bean
    RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> template = new RedisTemplate();
        template.setConnectionFactory(this.jedisConnectionFactory());

        // key序列化方式
        template.setKeySerializer(new org.springframework.data.redis.serializer.StringRedisSerializer());

        // value序列化
        template.setValueSerializer(new org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer());

        // hashmap序列化
        template.setHashKeySerializer(new org.springframework.data.redis.serializer.StringRedisSerializer());
        template.setHashValueSerializer(new org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer());
        // 开启事务
        template.setEnableTransactionSupport(true);
        return template;
    }

    @Bean
    public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        return cacheManager;
    }

}
