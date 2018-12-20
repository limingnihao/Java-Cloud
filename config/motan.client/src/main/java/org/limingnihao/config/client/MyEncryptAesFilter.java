package org.limingnihao.config.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by lishiming on 2016/10/10.
 */
@WebFilter(filterName = "myFilter", urlPatterns = "/*")
public class MyEncryptAesFilter implements Filter {

    public static final Logger logger = LoggerFactory.getLogger(MyEncryptAesFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("===============MyEncryptAesFilter - init===============");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 生产模式，开启解密
//        String accessToken = request.getParameter("accessToken");
//        logger.info("===============MyEncryptAesFilter - doFilter===============, accessToken=" + accessToken);
//        MyHttpServletRequestWrapper request2 = new MyHttpServletRequestWrapper((HttpServletRequest) request);
//        request2.setKey(accessToken);
//        chain.doFilter(request2, response);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("===============MyEncryptAesFilter - destroy===============");
    }

}
