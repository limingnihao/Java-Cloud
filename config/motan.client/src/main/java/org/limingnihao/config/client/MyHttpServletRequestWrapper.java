package org.limingnihao.config.client;

import org.limingnihao.util.CryptAESUtil;
import org.limingnihao.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by lishiming on 2016/10/11.
 */
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public static final Logger logger = LoggerFactory.getLogger(MyHttpServletRequestWrapper.class);

    private String key;

    public MyHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public MyHttpServletRequestWrapper(HttpServletRequest request, String key) {
        super(request);
        this.key = key;
    }

    /**
     * 后面用于解密参数
     *
     * @param name
     * @return
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] strs = super.getParameterValues(name);
        if (name.equals("params") && strs != null && strs.length > 0) {
            String data = strs[0];
            String result = "";
            if (StringUtil.isNotBlank(key) && StringUtil.isNotBlank(data)) {
                try {
                    result = CryptAESUtil.decrypt(key, data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                result = "params or access token is error!";
            }
            logger.info("MyHttpServletRequestWrapper - 解密 - params=" + result + ", key=" + key + ", data=" + data);
            strs[0] = result;
            return strs;
        }
        return strs;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
