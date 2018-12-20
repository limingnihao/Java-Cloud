package org.limingnihao.config.provider.hibernate;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by lishiming on 2016/9/29.
 */
@ConfigurationProperties(prefix = "rpc")
public class MyProviderMotanProperties {

    private String address;
    private String port;
    private String group;
    private String module;
    private String application;
    private String zookeeper;

    @Override
    public String toString() {
        return "MyProviderMotanProperties{" +
                "\n, address='" + address + '\'' +
                "\n, port='" + port + '\'' +
                "\n, group='" + group + '\'' +
                "\n, module='" + module + '\'' +
                "\n, application='" + application + '\'' +
                "\n, zookeeper='" + zookeeper + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getZookeeper() {
        return zookeeper;
    }

    public void setZookeeper(String zookeeper) {
        this.zookeeper = zookeeper;
    }
}