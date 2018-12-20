package org.limingnihao.config.session.service;

import org.limingnihao.base.manager.bean.application.MUserBasicBean;
import com.google.gson.reflect.TypeToken;
import org.limingnihao.util.GsonUtil;
import org.limingnihao.util.NumberUtil;
import org.limingnihao.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by limingnihao on 2017/7/20.
 */
@Service
public class LoginSessionServiceImpl implements LoginSessionService {

    private Logger logger = LoggerFactory.getLogger(LoginSessionServiceImpl.class);

    String LOGIN_USER_KEY = "USER_LOGIN_INFO";

    @Override
    public void saveUser(HttpSession session, MUserBasicBean userBean, Integer maxInactiveInterval) {
        session.setAttribute(LOGIN_USER_KEY, GsonUtil.toJson(userBean));
        if (NumberUtil.isSignless(maxInactiveInterval)) {
            session.setMaxInactiveInterval(maxInactiveInterval);
        }
    }

    @Override
    public MUserBasicBean getUser(HttpSession session) {
        MUserBasicBean userBean = null;
        Object userObject = session.getAttribute(LOGIN_USER_KEY);
        //        logger.info("getUser - " + userObject);
        if (userObject != null) {
            String userInfoJson = userObject.toString();
            if (StringUtil.isNotBlank(userInfoJson)) {
                userBean = GsonUtil.fromJson(userInfoJson, new TypeToken<MUserBasicBean>() {
                }.getType());
            }
        }
        return userBean;
    }

    @Override
    public void cleanUser(HttpSession session) {
        session.removeAttribute(LOGIN_USER_KEY);
    }
}
