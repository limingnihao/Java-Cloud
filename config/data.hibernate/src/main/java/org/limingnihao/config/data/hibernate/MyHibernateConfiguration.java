package org.limingnihao.config.data.hibernate;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by lishiming on 2016/2/9.
 */

// 开启事务检查，等同于<tx:annotation-driven />
@EnableTransactionManagement
@Configuration
@EnableConfigurationProperties(MyHibernateJdbcProperties.class)
public class MyHibernateConfiguration {

    public static final Logger logger = LoggerFactory.getLogger(MyHibernateConfiguration.class);

    @Autowired
    private MyHibernateJdbcProperties properties;

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * ===========================================================================================
     * =======================================druid配置============================================
     * ===========================================================================================
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        logger.info("init Druid Servlet Configuration ");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // IP白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // IP黑名单(共同存在时，deny优先于allow)
        //        servletRegistrationBean.addInitParameter("deny", "192.168.1.100");
        //控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        //是否能够重置数据 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    /**
     * ===========================================================================================
     * =======================================数据库配置============================================
     * ===========================================================================================
     */
//    @Bean
//    public BasicDataSource dataSource() {
//        logger.info("===============dataSource===============" + this.properties.dataSource());
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(this.properties.getJdbc_driver());
//        dataSource.setUrl(this.properties.getJdbc_url());
//        dataSource.setUsername(this.properties.getJdbc_username());
//        dataSource.setPassword(this.properties.getJdbc_password());
//        return dataSource;
//    }
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.properties.getJdbc_driver());
        dataSource.setUrl(this.properties.getJdbc_url());
        dataSource.setUsername(this.properties.getJdbc_username());
        dataSource.setPassword(this.properties.getJdbc_password());

        //configuration
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(3);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(60000);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(30000);
        dataSource.setValidationQuery("select 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        dataSource.setConnectionProperties("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000");
        try {
            dataSource.setFilters("stat,wall,slf4j");
        } catch (Exception e) {
            System.err.println("druid configuration initialization filter: " + e);
            logger.error(e.toString());
            e.printStackTrace();
        }
        return dataSource;
    }

    /**
     * 持久层会话工厂类
     */
    @Bean
    public FactoryBean<SessionFactory> sessionFactory() throws Exception {
        logger.info("===============sessionFactory===============" + this.properties.toString());
        return new LocalSessionFactoryBean() {
            {
                setDataSource(dataSource());
                setPackagesToScan(new String[]{"org.limingnihao.*"});
                setHibernateProperties(new Properties() {
                    private static final long serialVersionUID = 1L;

                    {
                        setProperty("hibernate.dialect", properties.getHibernate_dialect());
                        setProperty("hibernate.show_sql", properties.getHibernate_show_sql());
                        setProperty("hibernate.format_sql", properties.getHibernate_format_sql());
                        setProperty("hibernate.hbm2ddl.auto", properties.getHibernate_hbm2ddl_auto());
                        setProperty("hibernate.query.substitutions", properties.getHibernate_query_substitutions());

                        setProperty("hibernate.jdbc.fetch_size", properties.getHibernate_jdbc_fetch_size());
                        setProperty("hibernate.jdbc.batch_size", properties.getHibernate_jdbc_batch_size());
                        setProperty("hibernate.connection.autocommit", properties.getHibernate_connection_autocommit());
                        setProperty("hibernate.transaction.coordinator_class", properties.getHibernate_transaction_coordinator_class());
                        setProperty("hibernate.current_session_context_class", properties.getHibernate_current_session_context_class());
                        setProperty("hibernate.cache.region.factory_class", properties.getHibernate_cache_region_factory_class());
                        setProperty("hibernate.cache.use_second_level_cache", properties.getHibernate_cache_use_second_level_cache());
                        setProperty("hibernate.cache.use_query_cache", properties.getHibernate_cache_use_queruse_query_cachey_cache());
                        setProperty("hibernate.cache.provider_configuration_file_resource_path", properties.getHibernate_cache_provider_configuration_file_resource_path());
                    }
                });
            }
        };
    }

    /**
     * Hibernate事务管理
     *
     * @return
     */
    @Bean
    public HibernateTransactionManager hibernateTransactionManager() {
        return new HibernateTransactionManager() {
            private static final long serialVersionUID = 1L;

            {
                setSessionFactory(sessionFactory);
            }
        };
    }

}
