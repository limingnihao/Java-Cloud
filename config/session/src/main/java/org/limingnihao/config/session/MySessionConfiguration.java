package org.limingnihao.config.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by limingnihao on 2017/7/20.
 */
//@Configuration
//@EnableTransactionManagement
//@EnableScheduling
//@EnableConfigurationProperties(MySessionProperties.class)
@EnableRedisHttpSession
public class MySessionConfiguration {

    private Logger logger = LoggerFactory.getLogger(MySessionConfiguration.class);

    //    @Autowired
    //    private MySessionProperties properties;

    //    @Bean
    //    public CookieSerializer cookieSerializer() {
    //        logger.info("=============== cookieSerializer =============== " + this.properties.toString());
    //        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
    //        serializer.setCookieName(this.properties.getCookieName());
    //        serializer.setCookiePath(this.properties.getCookiePath());
    //        serializer.setDomainName(this.properties.getCookieDomain());
    //        return serializer;
    //    }
    //
    //    @Bean
    //    RedisHttpSessionConfiguration redisHttpSessionConfiguration(){
    //        RedisHttpSessionConfiguration configuration = new RedisHttpSessionConfiguration();
    //        return configuration;
    //    }
    //    @Bean
    //    public HttpSessionStrategy httpSessionStrategy() {
    //        HeaderHttpSessionStrategy headerHttpSessionStrategy = new HeaderHttpSessionStrategy();
    //        return headerHttpSessionStrategy;
    //    }

}
