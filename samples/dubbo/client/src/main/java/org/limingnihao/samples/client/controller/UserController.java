package org.limingnihao.samples.client.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.limingnihao.beans.UserBean;
import org.limingnihao.interfaces.RPCUserService;
import org.limingnihao.model.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Reference
    private RPCUserService userService;

    @RequestMapping(value = "/", produces = "application/json")
    public ResultBean<String> info(String params, String accessToken, String platformCode) {
        logger.info("info - params=" + params + ", accessToken=" + accessToken + ", platformCode=" + platformCode);
        ResultBean<String> result = new ResultBean<String>("info");
        return result;
    }

    @RequestMapping(value = "/create", produces = "application/json")
    public ResultBean create(String params, String accessToken, String platformCode) {
        logger.info("create - params=" + params + ", accessToken=" + accessToken + ", platformCode=" + platformCode);
        ResultBean result = new ResultBean<>();
        try {
            this.userService.create(null);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("系统繁忙，请稍后再试！");
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping(value = "/getList", produces = "application/json")
    public ResultBean getUserList(String params, String accessToken, String platformCode) {
        logger.info("getList - params=" + params + ", accessToken=" + accessToken + ", platformCode=" + platformCode);
        ResultBean<List<UserBean>> result = new ResultBean<>();
        if (params.equals("2")) {
            throw new RuntimeException("这里是http直接的异常");
        }
        try {
            List<UserBean> userList = this.userService.getList(params, accessToken, platformCode);
            result.setSuccess(true);
            result.setData(userList);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("系统繁忙，请稍后再试！");
            result.setSuccess(false);
        }
        return result;
    }
}
