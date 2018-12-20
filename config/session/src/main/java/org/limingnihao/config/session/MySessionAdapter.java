package org.limingnihao.config.session;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by limingnihao on 2017/7/20.
 */
@Configuration
public class MySessionAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        MySessionInterceptor loginCheckInterceptor = new MySessionInterceptor();
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("PUT", "DELETE", "POST", "GET", "PATCH", "OPTIONS", "HEAD");
    }
}
