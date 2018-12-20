package org.limingnihao.samples.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan("org.limingnihao")
@ServletComponentScan
@SpringBootApplication
@RestController
public class ProviderApplicationMain {

    public static final Logger logger = LoggerFactory.getLogger(ProviderApplicationMain.class);

    public static void main(String[] args) throws Exception {
        logger.info("-----start-----");
        SpringApplication.run(ProviderApplicationMain.class, args);
        logger.info("-----over---");
    }
}
