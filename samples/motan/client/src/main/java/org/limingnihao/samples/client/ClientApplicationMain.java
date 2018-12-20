package org.limingnihao.samples.client;

import org.limingnihao.model.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan("org.limingnihao")
@SpringBootApplication
@ServletComponentScan
@RestController
public class ClientApplicationMain {

    public static final Logger logger = LoggerFactory.getLogger(ClientApplicationMain.class);

    @RequestMapping(value = "/", produces = "application/json")
    public ResultBean<String> info() {
        String info = this.getClass().getPackage().getName() + "-" + this.getClass().getName();
        logger.info(info);
        return new ResultBean<String>(info);
    }

    public static void main(String[] args) throws Exception {

        logger.info("-----start-----");
        SpringApplication.run(ClientApplicationMain.class, args);
        logger.info("-----over---");
    }
}
