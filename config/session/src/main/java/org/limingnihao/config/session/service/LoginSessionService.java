package org.limingnihao.config.session.service;

import org.limingnihao.base.manager.bean.application.MUserBasicBean;

import javax.servlet.http.HttpSession;

/**
 * Created by limingnihao on 2017/7/20.
 */
public interface LoginSessionService {


    void saveUser(HttpSession session, MUserBasicBean userBean, Integer maxInactiveInterval);

    MUserBasicBean getUser(HttpSession session);

    void cleanUser(HttpSession session);

}
