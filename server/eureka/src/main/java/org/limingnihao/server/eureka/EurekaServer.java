package org.limingnihao.server.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.io.File;

/**
 * Created by lishiming on 16/2/17.
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {

    public static final Logger logger = LoggerFactory.getLogger(EurekaServer.class);

    public static void main(String[] args) {
        logger.info("-----start-----");

        SpringApplication.run(EurekaServer.class, args);

        logger.info("-----over-----");
    }
}
