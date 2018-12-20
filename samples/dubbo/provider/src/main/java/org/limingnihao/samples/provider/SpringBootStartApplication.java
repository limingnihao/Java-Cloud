package org.limingnihao.samples.provider;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by lishiming on 2017/5/2.
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        logger.info("-----start-----启动");
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(DubboProviderApplicationMain.class);
    }
}
