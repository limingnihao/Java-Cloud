package org.limingnihao.config.data.hibernate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by lishiming on 2016/9/29.
 */
@ConfigurationProperties
public class MyHibernateJdbcProperties {

    @Value("${jdbc.driver}")
    private String jdbc_driver;

    @Value("${jdbc.url}")
    private String jdbc_url;

    @Value("${jdbc.username}")
    private String jdbc_username;

    @Value("${jdbc.password}")
    private String jdbc_password;

    @Value("${jdbc.hibernate.dialect}")
    private String hibernate_dialect = "";

    @Value("${jdbc.hibernate.show_sql}")
    private String hibernate_show_sql = "";

    @Value("${jdbc.hibernate.format_sql}")
    private String hibernate_format_sql = "";

    @Value("${jdbc.hibernate.hbm2ddl.auto}")
    private String hibernate_hbm2ddl_auto = "";

    @Value("${jdbc.hibernate.query.substitutions}")
    private String hibernate_query_substitutions = "";

    @Value("${jdbc.hibernate.jdbc.fetch_size}")
    private String hibernate_jdbc_fetch_size = "";

    @Value("${jdbc.hibernate.jdbc.batch_size}")
    private String hibernate_jdbc_batch_size = "";

    @Value("${jdbc.hibernate.connection.autocommit}")
    private String hibernate_connection_autocommit = "";

    @Value("${jdbc.hibernate.transaction.coordinator_class}")
    private String hibernate_transaction_coordinator_class = "";

    @Value("${jdbc.hibernate.current_session_context_class}")
    private String hibernate_current_session_context_class;

    @Value("${jdbc.hibernate.cache.use_second_level_cache}")
    private String hibernate_cache_use_second_level_cache = "";

    @Value("${jdbc.hibernate.cache.use_queruse_query_cachey_cache}")
    private String hibernate_cache_use_queruse_query_cachey_cache = "";

    @Value("${jdbc.hibernate.cache.provider_configuration_file_resource_path}")
    private String hibernate_cache_provider_configuration_file_resource_path = "";

    @Value("${jdbc.hibernate.cache.region.factory_class}")
    private String hibernate_cache_region_factory_class = "";

    @Override
    public String toString() {
        return "MyHibernateJdbcProperties{" +
                "\n, jdbc_driver='" + jdbc_driver + '\'' +
                "\n, jdbc_url='" + jdbc_url + '\'' +
                "\n, jdbc_username='" + jdbc_username + '\'' +
                "\n, jdbc_password='" + jdbc_password + '\'' +
                "\n, hibernate_dialect='" + hibernate_dialect + '\'' +
                "\n, hibernate_show_sql='" + hibernate_show_sql + '\'' +
                "\n, hibernate_format_sql='" + hibernate_format_sql + '\'' +
                "\n, hibernate_hbm2ddl_auto='" + hibernate_hbm2ddl_auto + '\'' +
                "\n, hibernate_query_substitutions='" + hibernate_query_substitutions + '\'' +
                "\n, hibernate_jdbc_fetch_size='" + hibernate_jdbc_fetch_size + '\'' +
                "\n, hibernate_jdbc_batch_size='" + hibernate_jdbc_batch_size + '\'' +
                "\n, hibernate_connection_autocommit='" + hibernate_connection_autocommit + '\'' +
                "\n, hibernate_transaction_coordinator_class='" + hibernate_transaction_coordinator_class + '\'' +
                "\n, hibernate_current_session_context_class='" + hibernate_current_session_context_class + '\'' +
                "\n, hibernate_cache_use_second_level_cache='" + hibernate_cache_use_second_level_cache + '\'' +
                "\n, hibernate_cache_use_queruse_query_cachey_cache='" + hibernate_cache_use_queruse_query_cachey_cache + '\'' +
                "\n, hibernate_cache_provider_configuration_file_resource_path='" + hibernate_cache_provider_configuration_file_resource_path + '\'' +
                "\n, hibernate_cache_region_factory_class='" + hibernate_cache_region_factory_class + '\'' +
                '}';
    }

    public String getJdbc_driver() {
        return jdbc_driver;
    }

    public void setJdbc_driver(String jdbc_driver) {
        this.jdbc_driver = jdbc_driver;
    }

    public String getJdbc_url() {
        return jdbc_url;
    }

    public void setJdbc_url(String jdbc_url) {
        this.jdbc_url = jdbc_url;
    }

    public String getJdbc_username() {
        return jdbc_username;
    }

    public void setJdbc_username(String jdbc_username) {
        this.jdbc_username = jdbc_username;
    }

    public String getJdbc_password() {
        return jdbc_password;
    }

    public void setJdbc_password(String jdbc_password) {
        this.jdbc_password = jdbc_password;
    }

    public String getHibernate_dialect() {
        return hibernate_dialect;
    }

    public void setHibernate_dialect(String hibernate_dialect) {
        this.hibernate_dialect = hibernate_dialect;
    }

    public String getHibernate_show_sql() {
        return hibernate_show_sql;
    }

    public void setHibernate_show_sql(String hibernate_show_sql) {
        this.hibernate_show_sql = hibernate_show_sql;
    }

    public String getHibernate_format_sql() {
        return hibernate_format_sql;
    }

    public void setHibernate_format_sql(String hibernate_format_sql) {
        this.hibernate_format_sql = hibernate_format_sql;
    }

    public String getHibernate_hbm2ddl_auto() {
        return hibernate_hbm2ddl_auto;
    }

    public void setHibernate_hbm2ddl_auto(String hibernate_hbm2ddl_auto) {
        this.hibernate_hbm2ddl_auto = hibernate_hbm2ddl_auto;
    }

    public String getHibernate_query_substitutions() {
        return hibernate_query_substitutions;
    }

    public void setHibernate_query_substitutions(String hibernate_query_substitutions) {
        this.hibernate_query_substitutions = hibernate_query_substitutions;
    }

    public String getHibernate_jdbc_fetch_size() {
        return hibernate_jdbc_fetch_size;
    }

    public void setHibernate_jdbc_fetch_size(String hibernate_jdbc_fetch_size) {
        this.hibernate_jdbc_fetch_size = hibernate_jdbc_fetch_size;
    }

    public String getHibernate_jdbc_batch_size() {
        return hibernate_jdbc_batch_size;
    }

    public void setHibernate_jdbc_batch_size(String hibernate_jdbc_batch_size) {
        this.hibernate_jdbc_batch_size = hibernate_jdbc_batch_size;
    }

    public String getHibernate_connection_autocommit() {
        return hibernate_connection_autocommit;
    }

    public void setHibernate_connection_autocommit(String hibernate_connection_autocommit) {
        this.hibernate_connection_autocommit = hibernate_connection_autocommit;
    }

    public String getHibernate_transaction_coordinator_class() {
        return hibernate_transaction_coordinator_class;
    }

    public void setHibernate_transaction_coordinator_class(String hibernate_transaction_coordinator_class) {
        this.hibernate_transaction_coordinator_class = hibernate_transaction_coordinator_class;
    }

    public String getHibernate_current_session_context_class() {
        return hibernate_current_session_context_class;
    }

    public void setHibernate_current_session_context_class(String hibernate_current_session_context_class) {
        this.hibernate_current_session_context_class = hibernate_current_session_context_class;
    }

    public String getHibernate_cache_use_second_level_cache() {
        return hibernate_cache_use_second_level_cache;
    }

    public void setHibernate_cache_use_second_level_cache(String hibernate_cache_use_second_level_cache) {
        this.hibernate_cache_use_second_level_cache = hibernate_cache_use_second_level_cache;
    }

    public String getHibernate_cache_use_queruse_query_cachey_cache() {
        return hibernate_cache_use_queruse_query_cachey_cache;
    }

    public void setHibernate_cache_use_queruse_query_cachey_cache(String hibernate_cache_use_queruse_query_cachey_cache) {
        this.hibernate_cache_use_queruse_query_cachey_cache = hibernate_cache_use_queruse_query_cachey_cache;
    }

    public String getHibernate_cache_provider_configuration_file_resource_path() {
        return hibernate_cache_provider_configuration_file_resource_path;
    }

    public void setHibernate_cache_provider_configuration_file_resource_path(String hibernate_cache_provider_configuration_file_resource_path) {
        this.hibernate_cache_provider_configuration_file_resource_path = hibernate_cache_provider_configuration_file_resource_path;
    }

    public String getHibernate_cache_region_factory_class() {
        return hibernate_cache_region_factory_class;
    }

    public void setHibernate_cache_region_factory_class(String hibernate_cache_region_factory_class) {
        this.hibernate_cache_region_factory_class = hibernate_cache_region_factory_class;
    }
}