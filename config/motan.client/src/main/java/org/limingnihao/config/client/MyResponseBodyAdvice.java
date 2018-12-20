package org.limingnihao.config.client;

import org.limingnihao.model.ResultBean;
import org.limingnihao.util.CryptAESUtil;
import org.limingnihao.util.GsonUtil;
import org.limingnihao.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created by lishiming on 2016/10/11.
 */
@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice<ResultBean> {

    public static final Logger logger = LoggerFactory.getLogger(MyResponseBodyAdvice.class);

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        //        logger.info("supports - returnType=" + returnType + ", converterType=" + converterType);
        return true;
    }

    /**
     * 对返回结果进行加密
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public ResultBean beforeBodyWrite(ResultBean body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        try {
            ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request;
            String accessToken = servletServerHttpRequest.getServletRequest().getParameter("accessToken");
            logger.info("beforeBodyWrite - accessToken=" + accessToken + ", body=" + body + ", returnType=" + returnType + ", selectedContentType=" + selectedContentType + ", selectedConverterType=" + selectedConverterType);
            if (StringUtil.isNotBlank(accessToken)) {
                String data = GsonUtil.toJson(body.getData());
                body.setData(CryptAESUtil.encrypt(accessToken, data));
                return body;
            } else {
                body.setMessage("授权错误！");
                return body;
            }
        } catch (Exception e) {
            e.printStackTrace();
            body.setMessage("授权错误！");
            return body;
        }
    }

}
