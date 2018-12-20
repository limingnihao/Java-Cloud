package org.limingnihao.framework.mybatis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.Resource;

/**
 * Created by lishiming on 2016/9/29.
 */
@ConfigurationProperties
@Resource
public class MyMyBatisProperties {


    @Value("${jdbc.driver}")
    private String jdbc_driver;

    @Value("${jdbc.url}")
    private String jdbc_url;

    @Value("${jdbc.username}")
    private String jdbc_username;

    @Value("${jdbc.password}")
    private String jdbc_password;

    @Value("${mybatis.typeAliasesPackage}")
    private String mybatis_typeAliasesPackage;

    @Value("${mybatis.mapperLocations}")
    private String mybatis_mapperLocations;

    @Value("${mybatis.configLocation}")
    private String mybatis_configLocation;


    public String dataSource() {
        return "\n  MyMyBatisProperties-jdbc{" + "\n, jdbc_driver='" + jdbc_driver + '\'' + "\n, jdbc_url='" + jdbc_url + '\'' + "\n, jdbc_username='" + jdbc_username + '\'' + "\n, jdbc_password='" + jdbc_password + '\'' + "\n}";
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

    public String getMybatis_typeAliasesPackage() {
        return mybatis_typeAliasesPackage;
    }

    public void setMybatis_typeAliasesPackage(String mybatis_typeAliasesPackage) {
        this.mybatis_typeAliasesPackage = mybatis_typeAliasesPackage;
    }

    public String getMybatis_mapperLocations() {
        return mybatis_mapperLocations;
    }

    public void setMybatis_mapperLocations(String mybatis_mapperLocations) {
        this.mybatis_mapperLocations = mybatis_mapperLocations;
    }

    public String getMybatis_configLocation() {
        return mybatis_configLocation;
    }

    public void setMybatis_configLocation(String mybatis_configLocation) {
        this.mybatis_configLocation = mybatis_configLocation;
    }
}