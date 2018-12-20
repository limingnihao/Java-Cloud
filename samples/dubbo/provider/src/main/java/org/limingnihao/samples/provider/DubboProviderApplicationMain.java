package org.limingnihao.samples.provider;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@DubboComponentScan(basePackages = "org.limingnihao.samples")
public class DubboProviderApplicationMain {

    public static final Logger logger = LoggerFactory.getLogger(DubboProviderApplicationMain.class);

    public static void main(String[] args) throws Exception {
        logger.info("-----start-----");
        SpringApplication.run(DubboProviderApplicationMain.class, args);
        logger.info("-----over---");
    }
}
