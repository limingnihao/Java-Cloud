package org.limingnihao.samples.client.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhaopin.common3.http.HttpUtils;
import com.zhaopin.common3.http.holder.StringResponseHolder;
import org.limingnihao.interfaces.RPCRegionService;
import org.limingnihao.model.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/region")
public class RegionController {

    public static final Logger logger = LoggerFactory.getLogger(RegionController.class);

    @Reference
    private RPCRegionService regionService;

    @RequestMapping(value = "/info", produces = "application/json")
    public ResultBean<Long> info(String params, String accessToken, String platformCode) {
        logger.info("info - params=" + params + ", accessToken=" + accessToken + ", platformCode=" + platformCode);
        ResultBean<Long> result = new ResultBean<Long>(this.regionService.info());
        return result;
    }

    @RequestMapping(value = "/getHttp", produces = "application/json")
    public String getHttp() {
        StringResponseHolder s = HttpUtils.createGet("http://www.jd.com")
                .queryAsString();

        StringResponseHolder s2 = HttpUtils.createGet("http://www.baidu.com")
                .queryAsString();

        int total = this.regionService.getTotal("1", "2");
        return s.getValue();
    }

    @RequestMapping(value = "/getTotal", produces = "application/json")
    public ResultBean getTotal(String params, String accessToken, String platformCode) {
        logger.info("getList - params=" + params + ", accessToken=" + accessToken + ", platformCode=" + platformCode);
        ResultBean<String> result = new ResultBean<>();
        try {
            int total = this.regionService.getTotal(params, accessToken);
            result.setSuccess(true);
            result.setData(total + "");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("系统繁忙，请稍后再试！");
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping(value = "/getDouble", produces = "application/json")
    public ResultBean getDouble(String params, String accessToken, String platformCode) {
        logger.info("getDouble - params=" + params + ", accessToken=" + accessToken + ", platformCode=" + platformCode);
        ResultBean<String> result = new ResultBean<>();
        try {
            int total = this.regionService.getTotal("", "");
            this.regionService.getHttp();
            result.setSuccess(true);
            result.setData(total + "");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("系统繁忙，请稍后再试！");
            result.setSuccess(false);
        }
        return result;
    }
}
