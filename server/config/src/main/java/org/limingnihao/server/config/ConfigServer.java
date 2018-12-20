package org.limingnihao.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lishiming on 16/1/24.
 */
@Configuration
@EnableAutoConfiguration
@EnableConfigServer
public class ConfigServer {

    public static final Logger logger = LoggerFactory.getLogger(ConfigServer.class);

    public static void main(String[] args) {
        logger.info("-----start-----");
        SpringApplication.run(ConfigServer.class, args);
        logger.info("-----over-----");
    }

}
