package org.limingnihao.beans;

import java.io.Serializable;
import java.util.List;

public class UserBean implements Serializable {

    private Long userId;
    private String username;
    private Integer age;
    private boolean isVip;
    private List<String> menus;

    public UserBean(Long userId, String username, Integer age, boolean isVip) {
        this.userId = userId;
        this.username = username;
        this.age = age;
        this.isVip = isVip;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public List<String> getMenus() {
        return menus;
    }

    public void setMenus(List<String> menus) {
        this.menus = menus;
    }
}
