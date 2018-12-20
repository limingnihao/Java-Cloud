package org.limingnihao.config.client;

import com.weibo.api.motan.config.springsupport.AnnotationBean;
import com.weibo.api.motan.config.springsupport.BasicRefererConfigBean;
import com.weibo.api.motan.config.springsupport.ProtocolConfigBean;
import com.weibo.api.motan.config.springsupport.RegistryConfigBean;
import org.limingnihao.util.NumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.util.IntrospectorCleanupListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * Created by lishiming on 2016/10/11.
 */
@Configuration
@EnableScheduling
public class MyClientConfiguration {

    public static final Logger logger = LoggerFactory.getLogger(MyClientConfiguration.class);

    @Value("${rpc.zookeeper}")
    private String rpc_zookeeper;

    @Value("${rpc.requestTimeout}")
    private String rpc_requestTimeout;

    @Value("${rpc.maxContentLength}")
    private String rpc_maxContentLength;

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

    /**
     * 协议与连接（motan:protocol)
     *
     * @return
     */
    @Bean(name = "protocolConfigBean")
    public ProtocolConfigBean protocolConfigBean() {
        logger.info("===============rpc===============, requestTimeout=" + this.rpc_requestTimeout + ", maxContentLength=" + this.rpc_maxContentLength);

        ProtocolConfigBean config = new ProtocolConfigBean();
        config.setDefault(true);

        //rpc协议为Motan协议
        config.setName("motan");

        //负载均衡模式，默认ActiveWeight低并发度优先，
        //random随机，按权重设置随机概率。
        //roundrobin轮循，按公约后的权重设置轮循比率
        //localFirst， 本地服务优先获取策略
        //consistent，一致性 Hash，相同参数的请求总是发到同一提供者
        //configurableWeight， 权重可配置的负载均衡策略
        config.setLoadbalance("activeWeight");

        //容错策略
        //failover, 失败自动切换，当出现失败，重试其它服务器。
        //failfast, 快速失败，只发起一次调用，失败立即报错。
        config.setHaStrategy("failfast");

        //请求响应包的最大长度限制
        config.setMaxContentLength(NumberUtil.parseInt(this.rpc_maxContentLength, 10485760));
        //超时时间，默认200
        config.setRequestTimeout(NumberUtil.parseInt(this.rpc_requestTimeout, 10000));

        return config;
    }

    /**
     * 注册中心与服务发现(motan:registry)
     *
     * @return
     */
    @Bean(name = "registryConfigBean")
    public RegistryConfigBean registryConfigBean() {
        logger.info("===============rpc===============, zookeeper=" + this.rpc_zookeeper);

        RegistryConfigBean config = new RegistryConfigBean();
        //direct,不使用注册中心
        //zookeeper为单节点
        config.setRegProtocol("zookeeper");
        config.setAddress(this.rpc_zookeeper);
        return config;
    }

    @Bean(name = "refererConfigBean")
    public BasicRefererConfigBean baseRefererConfig() {
        BasicRefererConfigBean config = new BasicRefererConfigBean();

        config.setProtocol("protocolConfigBean");
        config.setRegistry("registryConfigBean");
        config.setCheck(false);
        config.setAccessLog(true);
        config.setRetries(2);
        config.setThrowException(true);
        return config;
    }

    /**
     * ===========================================================================================
     * =======================================web配置============================================
     * ===========================================================================================
     */
    //    @Bean
    //    public ServletRegistrationBean servletRegistration(DispatcherServlet dispatcherServlet) {
    //        logger.info("===============servletRegistration===============" + dispatcherServlet);
    //        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
    //        registration.getUrlMappings().clear();
    //        registration.addUrlMappings("*.do");
    //        return registration;
    //    }
    @Bean
    public CharacterEncodingFilter characterEncodingFilter(ServletContext servletContext) {
        logger.info("===============characterEncodingFilter===============" + servletContext);
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        filter.setBeanName("characterEncodingFilter");
        filter.setServletContext(servletContext);
        return filter;
    }

    @Bean
    public MyEncryptAesFilter encryptAesFilter() {
        logger.info("===============encryptAesFilter===============");
        MyEncryptAesFilter filter = new MyEncryptAesFilter();
        return filter;
    }

    @Bean
    public IntrospectorCleanupListener introspectorCleanupListener(ServletContext servletContext) {
        logger.info("===============introspectorCleanupListener===============" + servletContext);
        IntrospectorCleanupListener listener = new IntrospectorCleanupListener();
        listener.contextInitialized(new ServletContextEvent(servletContext));
        return listener;
    }

}
