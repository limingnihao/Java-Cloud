package org.limingnihao.config.session;

import org.limingnihao.base.help.ErrorHelp;
import org.limingnihao.base.manager.bean.application.MUserBasicBean;
import org.limingnihao.base.rpc.bean.IResultBean;
import org.limingnihao.config.session.service.LoginSessionService;
import org.limingnihao.config.session.service.LoginSessionServiceImpl;
import org.limingnihao.util.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by limingnihao on 2017/7/20.
 */

public class MySessionInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(MySessionInterceptor.class);

    private LoginSessionService loginSessionService = new LoginSessionServiceImpl();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath = request.getServletPath();
        String method = request.getMethod();
        HttpSession session = request.getSession();
        List<String> names = new ArrayList<>();

        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            names.add(attributeNames.nextElement());
        }
        //        logger.info("preHandle - servletPath=" + servletPath + ", method=" + method + ", names=" + Arrays.toString(names.toArray()));
        if (servletPath == null)
            return false;
        if ("OPTIONS".equals(method)) {
            return true;
        }
        if (servletPath.contains("/login") || servletPath.equals("/get") || servletPath.equals("/set")) {
            return true;
        } else {
            String origin = request.getHeader("origin");
            MUserBasicBean userBean = loginSessionService.getUser(request.getSession());
            logger.info("preHandle - servletPath=" + servletPath + ", method=" + method + ", userBean=" + userBean);
            if (userBean == null) {
                response.setHeader("content-type", "text/json;charset=UTF-8");
                response.setStatus(401);
                //这里填写你允许进行跨域的主机ip
                response.setHeader("Access-Control-Allow-Origin", origin);
                //允许的访问方法
                response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
                response.setHeader("Access-Control-Allow-Credentials", "true");

                IResultBean resultBean = new IResultBean();
                resultBean.setSuccess(false);
                resultBean.setMessage("用户未登陆！");
                resultBean.setErrorCode(ErrorHelp.CRM_ACCOUNT_NULL);
                resultBean.setEncrypt(false);

                OutputStream os = response.getOutputStream();
                os.write(GsonUtil.toJson(resultBean).getBytes());
                os.flush();
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
