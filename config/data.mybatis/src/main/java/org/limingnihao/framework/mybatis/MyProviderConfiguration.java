package org.limingnihao.framework.mybatis;

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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by lishiming on 2016/9/30.
 */
@Configuration
@EnableTransactionManagement
@EnableScheduling
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@EnableConfigurationProperties(MyMyBatisProperties.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MyProviderConfiguration {

    public static final Logger logger = LoggerFactory.getLogger(MyProviderConfiguration.class);

    @Autowired
    private MyMyBatisProperties properties;

    @Autowired
    private ResourceLoader resourceLoader;


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

