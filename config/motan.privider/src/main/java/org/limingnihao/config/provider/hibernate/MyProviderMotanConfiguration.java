package org.limingnihao.config.provider.hibernate;

import com.weibo.api.motan.config.springsupport.AnnotationBean;
import com.weibo.api.motan.config.springsupport.BasicServiceConfigBean;
import com.weibo.api.motan.config.springsupport.ProtocolConfigBean;
import com.weibo.api.motan.config.springsupport.RegistryConfigBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.StringUtils;

/**
 * Created by lishiming on 2016/2/9.
 */

// 开启事务检查，等同于<tx:annotation-driven />

// 开启对计划任务的支持，使用@Scheduled可以增加定时任务
@Configuration
@EnableScheduling
@EnableConfigurationProperties(MyProviderMotanProperties.class)
public class MyProviderMotanConfiguration {

    public static final Logger logger = LoggerFactory.getLogger(MyProviderMotanConfiguration.class);

    @Autowired
    private MyProviderMotanProperties properties;


    /**
     * ===========================================================================================
     * =======================================motan配置============================================
     * ===========================================================================================
     */
    @Bean
    public static AnnotationBean motanAnnotationBean() {
        AnnotationBean motanAnnotationBean = new AnnotationBean();
        motanAnnotationBean.setPackage("org.limingnihao");
        return motanAnnotationBean;
    }

    @Bean(name = "protocolConfigBean")
    public ProtocolConfigBean protocolConfig1() {
        ProtocolConfigBean config = new ProtocolConfigBean();
        config.setDefault(true);
        config.setName("motan");
        return config;
    }

    @Bean(name = "registryConfigBean")
    public RegistryConfigBean registryConfigBean() {
        logger.info("===============rpc registry===============" + this.properties.toString());
        RegistryConfigBean registryConfigBean = new RegistryConfigBean();
        registryConfigBean.setRegProtocol("zookeeper");
        registryConfigBean.setAddress(this.properties.getZookeeper());
        return registryConfigBean;
    }

    @Bean(name = "baseServiceConfigBean")
    public BasicServiceConfigBean baseServiceConfig() {
        logger.info("===============rpc service===============" + this.properties.getAddress());
        BasicServiceConfigBean config = new BasicServiceConfigBean();
        config.setGroup(this.properties.getGroup());
        config.setModule(this.properties.getModule());
        config.setApplication(this.properties.getApplication());
        config.setExport("protocolConfigBean:" + this.properties.getPort());
        config.setRegistry("registryConfigBean");
        if (!StringUtils.isEmpty(this.properties.getAddress()) && !"127.0.0.1".equals(this.properties.getAddress()) && !"localhost".equals(this.properties.getAddress())) {
            config.setHost(this.properties.getAddress());
        }
        config.setAccessLog(false);
        config.setShareChannel(true);
        return config;
    }

    /**
     * ===========================================================================================
     * =======================================web配置============================================
     * ===========================================================================================
     */
    //
    //    @Bean
    //    public ServletRegistrationBean servletRegistration(DispatcherServlet dispatcherServlet) {
    //        logger.info("===============servletRegistration===============" + dispatcherServlet);
    //        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
    //        registration.getUrlMappings().clear();
    //        registration.addUrlMappings("*.do");
    //        return registration;
    //    }
    //
    //    @Bean
    //    public CharacterEncodingFilter characterEncodingFilter(ServletContext servletContext) {
    //        logger.info("===============characterEncodingFilter===============" + servletContext);
    //        CharacterEncodingFilter filter = new CharacterEncodingFilter();
    //        filter.setEncoding("UTF-8");
    //        filter.setForceEncoding(true);
    //        filter.setBeanName("characterEncodingFilter");
    //        filter.setServletContext(servletContext);
    //        return filter;
    //    }
    //
    //    @Bean
    //    public IntrospectorCleanupListener introspectorCleanupListener(ServletContext servletContext) {
    //        logger.info("===============introspectorCleanupListener===============" + servletContext);
    //        IntrospectorCleanupListener listener = new IntrospectorCleanupListener();
    //        listener.contextInitialized(new ServletContextEvent(servletContext));
    //        return listener;
    //    }
    //

    //    @Bean
    //    public OpenSessionInViewFilter openSessionInViewFilter(ServletContext servletContext) {
    //        logger.info("===============openSessionInViewFilter===============" + servletContext);
    //        OpenSessionInViewFilter filter = new OpenSessionInViewFilter();
    //        filter.setBeanName("openSessionInViewFilter");
    //        filter.setServletContext(servletContext);
    //        return filter;
    //    }

}
