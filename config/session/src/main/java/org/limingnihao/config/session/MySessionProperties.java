package org.limingnihao.config.session;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by limingnihao on 2017/7/20.
 */
//@ConfigurationProperties
//@Resource
public class MySessionProperties {

    @Value("${cookie.name}")
    private String cookieName;

    @Value("${cookie.path}")
    private String cookiePath;

    @Value("${cookie.domain}")
    private String cookieDomain;

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getCookiePath() {
        return cookiePath;
    }

    public void setCookiePath(String cookiePath) {
        this.cookiePath = cookiePath;
    }

    public String getCookieDomain() {
        return cookieDomain;
    }

    public void setCookieDomain(String cookieDomain) {
        this.cookieDomain = cookieDomain;
    }

    @Override
    public String toString() {
        return "MySessionProperties{" + "cookieName='" + cookieName + '\'' + ", cookiePath='" + cookiePath + '\'' + ", cookieDomain='" + cookieDomain + '\'' + '}';
    }
}
