package org.limingnihao.samples.client;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@DubboComponentScan(basePackages = "org.limingnihao")
public class DubboClientMain {

    public static final Logger logger = LoggerFactory.getLogger(DubboClientMain.class);

    public static void main(String[] args) throws Exception {

        logger.info("-----start-----");
        SpringApplication.run(DubboClientMain.class, args);
        logger.info("-----over---");
    }
}
