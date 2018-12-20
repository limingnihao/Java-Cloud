package org.limingnihao.config.provider;

import com.weibo.api.motan.config.springsupport.AnnotationBean;
import com.weibo.api.motan.config.springsupport.BasicServiceConfigBean;
import com.weibo.api.motan.config.springsupport.ProtocolConfigBean;
import com.weibo.api.motan.config.springsupport.RegistryConfigBean;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.IntrospectorCleanupListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.sql.DataSource;

/**
 * Created by lishiming on 2016/9/30.
 */
@Configuration
@EnableTransactionManagement
@EnableScheduling
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@EnableConfigurationProperties(MyProviderProperties.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MyProviderConfiguration {

    public static final Logger logger = LoggerFactory.getLogger(MyProviderConfiguration.class);

    @Autowired
    private MyProviderProperties properties;

    @Autowired
    private ResourceLoader resourceLoader;

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
        logger.info("===============rpc===============" + this.properties.rpc());
        RegistryConfigBean registryConfigBean = new RegistryConfigBean();
        registryConfigBean.setRegProtocol("zookeeper");
        registryConfigBean.setAddress(this.properties.getRpc_zookeeper());
        return registryConfigBean;
    }

    @Bean(name = "baseServiceConfigBean")
    public BasicServiceConfigBean baseServiceConfig() {
        BasicServiceConfigBean config = new BasicServiceConfigBean();
        config.setGroup(this.properties.getRpc_group());
        config.setModule(this.properties.getRpc_module());
        config.setApplication(this.properties.getRpc_application());

        config.setExport("protocolConfigBean:" + this.properties.getRpc_port());
        config.setRegistry("registryConfigBean");

        if (!StringUtils.isEmpty(this.properties.getServer_address()) && !"127.0.0.1".equals(this.properties.getServer_address()) && !"localhost".equals(this.properties.getServer_address())) {
            config.setHost(this.properties.getServer_address());
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

    @Bean
    public ServletRegistrationBean servletRegistration(DispatcherServlet dispatcherServlet) {
        logger.info("===============servletRegistration===============" + dispatcherServlet);
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
        registration.getUrlMappings().clear();
        registration.addUrlMappings("*.do");
        return registration;
    }

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
    public IntrospectorCleanupListener introspectorCleanupListener(ServletContext servletContext) {
        logger.info("===============introspectorCleanupListener===============" + servletContext);
        IntrospectorCleanupListener listener = new IntrospectorCleanupListener();
        listener.contextInitialized(new ServletContextEvent(servletContext));
        return listener;
    }

    /**
     * ===========================================================================================
     * =======================================数据库配置============================================
     * ===========================================================================================
     */
    @Bean
    public BasicDataSource dataSource() {
        logger.info("===============dataSource===============" + this.properties.dataSource());
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(this.properties.getJdbc_driver());
        dataSource.setUrl(this.properties.getJdbc_url());
        dataSource.setUsername(this.properties.getJdbc_username());
        dataSource.setPassword(this.properties.getJdbc_password());
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sessionFactoryBean(DataSource dataSource) throws Exception {
        logger.info("===============sessionFactoryBean===============, config_location=" + this.properties.getMybatis_configLocation());
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        //        sessionFactory.setVfs(SpringBootVFS.class);
        //        Resource configResource = this.resourceLoader.getResource(this.properties.getMybatis_configLocation());
        //        factory.setConfigLocation(configResource);


        sessionFactory.setTypeAliasesPackage(this.properties.getMybatis_typeAliasesPackage());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(this.properties.getMybatis_mapperLocations()));
        sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(this.properties.getMybatis_configLocation()));

        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}

