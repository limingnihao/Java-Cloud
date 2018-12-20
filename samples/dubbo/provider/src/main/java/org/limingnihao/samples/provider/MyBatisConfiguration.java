package org.limingnihao.samples.provider;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by lishiming on 2016/9/30.
 */
@Configuration
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
//@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MyBatisConfiguration {

    public static final Logger logger = LoggerFactory.getLogger(MyBatisConfiguration.class);

    private String jdbc_driver = "com.mysql.jdbc.Driver";

    private String jdbc_url = "jdbc:mysql://127.0.0.1:3306/dhcc_application?useUnicode=true&characterEncoding=utf-8&characterSetResults=UTF-8&serverTimezone=GMT%2b8";

    private String jdbc_username = "admin";

    private String jdbc_password = "admin";

    private String mybatis_typeAliasesPackage;

    private String mybatis_mapperLocations;

    private String mybatis_configLocation = "classpath:/mybatis-config.xml";


    /**
     * ===========================================================================================
     * =======================================数据库配置============================================
     * ===========================================================================================
     */
    @Bean
    public BasicDataSource dataSource() {
        logger.info("===============dataSource===============" + this.jdbc_driver);
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(this.jdbc_driver);
        dataSource.setUrl(this.jdbc_url);
        dataSource.setUsername(this.jdbc_username);
        dataSource.setPassword(this.jdbc_password);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sessionFactoryBean(DataSource dataSource) throws Exception {
        logger.info("===============sessionFactoryBean===============, config_location=" + this.mybatis_configLocation);
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage(this.mybatis_typeAliasesPackage);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(this.mybatis_mapperLocations));
        sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(this.mybatis_configLocation));

        return sessionFactory.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapper = new MapperScannerConfigurer();
        mapper.setBasePackage("org.limingnihao.data");
        return mapper;
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

