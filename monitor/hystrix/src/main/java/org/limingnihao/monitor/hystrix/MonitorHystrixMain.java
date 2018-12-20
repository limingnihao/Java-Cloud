package org.limingnihao.monitor.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringCloudApplication
public class MonitorHystrixMain {

    public static final Logger logger = LoggerFactory.getLogger(MonitorHystrixMain.class);

    public static void main(String[] args) {
        logger.info("-----start-----");
        SpringApplication.run(MonitorHystrixMain.class, args);
        logger.info("-----over-----");
    }
}
